package UCT_MoveChoice_Tests;

import AIComponents.*;
import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;
import java.util.Random;

public class Det_MCTS_MoveM_UCT1 extends Player{
    private int playerNumber;
    private ArrayList<Integer> moveCards;
    private ArrayList<Mole> moles;
    private Player enemy;
    private boolean moved = true;
    private int steps;
    private boolean specialField = false;
    public double[] timeMS = new double[] {0.0,0.0};


    public Det_MCTS_MoveM_UCT1 (int pN, Player otherPlayer) {
        this.playerNumber = pN;
        this.moveCards = new ArrayList<>();
        this.moles = new ArrayList<>();
        this.enemy = otherPlayer;
        initMoveCards();
    }

    public void setEnemy(Player e)
    {
        this.enemy = e;
    }

    @Override
    public void initMoveCards() {
        this.moveCards = new ArrayList<>();
        this.moveCards.add(1);
        this.moveCards.add(2);
        this.moveCards.add(2);
        this.moveCards.add(3);
        this.moveCards.add(3);
        this.moveCards.add(4);
    }

    @Override
    public int getAmountOfMoles() {
        return moles.size();
    }

    @Override
    public boolean setMole(Level lvl, int dummy) {
        //=========SET_AI=======
        int[] set = SettingAI.setNextMole(lvl);
        //System.out.println("set: "+set[0]+" , "+set[1]);
        moles.add(new Mole(this.playerNumber, set, lvl.getField()[set[0]][set[1]]));
        //setting onto field

        lvl.setMole(set[0], set[1], playerNumber);
        //=====================
        return true;
    }


    @Override
    public int drawMoveCard() {
        if (moveCards.isEmpty()) {
            initMoveCards();
        }
        int rndIndex = (int) (Math.random() * moveCards.size());
        int moveValue = moveCards.get(rndIndex);
        moveCards.remove(moveCards.get(rndIndex));
        return moveValue;
    }

    @Override
    public ArrayList<Mole> getMoles() {
        return this.moles;
    }

    @Override
    public int getPlayerNumber() {
        return this.playerNumber;
    }

    @Override
    public boolean initMolesToNewLvl(Level lvl) {
        //remove Moles not in hole
        this.moles.removeIf(m -> m.getPositionVlaue() != 8);
        if (this.moles.isEmpty()) {
            return false;
        }
        //set Moles on new lvl
        for (Mole m : this.moles) {
            //get new pos-value
            m.setPosition(m.getPosition(), lvl.getField()[m.getPosition()[0]][m.getPosition()[1]]);
            lvl.setMole(m.getPosition()[0], m.getPosition()[1], this.playerNumber);
        }
        return true;
    }
    public ArrayList<Integer> getMoveCards()
    {
        return this.moveCards;
    }

    //==================AI==========
    @Override
    public boolean makeMove(Level lvl, boolean specialFieldHit) {
        if(proofAllinHole())
        {
            return false;
        }
        double cTime =  System.currentTimeMillis();
        steps = drawMoveCard();
        ArrayList<GameState> roots = new ArrayList<>();
        //Start 10 MCTS trees
        for(int i=0; i<10;i++) {
            roots.add(new GameState(new SimulatingPlayer(this), new SimulatingPlayer(enemy), new Level(lvl), 0, null, steps, specialFieldHit, this.playerNumber));
            buildTree(roots.get(i));
        }
        //Merge childes Together.
        for(int i= 0;i<roots.size();i++)
        {
            if(i==0){
                continue;
            }
            for(int j = 0; j<roots.get(i).getChildes().size();j++)
            {
                //add to first roots Childes
                roots.get(0).getChildes().get(j).addWinLoss(roots.get(i).getChildes().get(j).getWinLoss());
            }
        }

        //if stuck
        if(roots.get(0).getChildes().isEmpty())
        {
            this.moved = false;
            System.out.println("==="+this.playerNumber+"===stuck steps : "+steps);
            roots.get(0).getLvl().printLVL();
            return false;
        }
        GameState nextMove = chooseNextMoveState(roots.get(0));
        System.out.println("\ninitial next choosen: "+ roots.get(0).getChildes().indexOf(nextMove)+"|"+(((double)nextMove.getWinLoss()[0]/(nextMove.getWinLoss()[0]+nextMove.getWinLoss()[1]))+((0.01*(double) Heuristics.calcHeuristicAv(nextMove.getPlayerOne(),nextMove.getPlayerTwo(),this.playerNumber)[0]))));
        //get childes with same value as the nextMoveNode
        ArrayList<GameState> moves = new ArrayList<>();

        for(int i=0;i<roots.get(0).getChildes().size();i++)
        {
            if(nextMove.getChoosenValue() == roots.get(0).getChildes().get(i).getChoosenValue()){
                moves.add(roots.get(0).getChildes().get(i));
            }
        }
        //choose random out of same valueNodes
        Random random = new Random();
        if(!moves.isEmpty()) {
            nextMove = moves.get(random.nextInt(moves.size()));
        }

        //lvl.printLVL();
        //check 20
        int checksum = 0;
        for(int[] row: lvl.getField())
        {
            for(int i:row)
            {
                if(i == 1 || i==2)
                {
                    checksum++;
                }
            }
        }
        //=====
        // nextMove.getLvl().printLVL();
        if(nextMove.getPlayer().getPlayerNumber() != this.playerNumber) {
            System.out.println("Moles of Player: " + nextMove.getPlayer().getPlayerNumber());
            System.exit(1);
        }
        this.moles = nextMove.getPlayer().getMoles();
        lvl.overWrite(nextMove.getLvl());
        for(Mole m :this.moles)
        {
            System.out.print(m.getPosition()[0]+","+m.getPosition()[1]+","+m.getPositionVlaue()+"|");
        }
        System.out.println("\nenemy empty or in hole: "+proofEnemyAllinHoleOrEmpty(enemy)+" Molessize: "+enemy.getMoles().size());
        if(checksum > 20)
        {
            lvl.printLVL();
            System.exit(checksum);
        }
        System.out.println();
        for(GameState g : roots.get(0).getChildes())
        {
            System.out.println("Heuristic:"+ Heuristics.calcHeuristicAv(g.getPlayerOne(),g.getPlayerTwo(),this.playerNumber)[0]+"|"+((g.getWinLoss()[0])+(g.getWinLoss()[1]))+"|"+g.getWinLoss()[0]+","+g.getWinLoss()[1]+"||"+(((double)g.getWinLoss()[0])/(g.getWinLoss()[0]+g.getWinLoss()[1]) +(0.01*((double)Heuristics.calcHeuristicAv(g.getPlayerOne(),g.getPlayerTwo(),this.playerNumber)[0]))));

        }
        //Special field !!!
        System.out.println("Choosen next: "+roots.get(0).getChildes().indexOf(nextMove)+" , "+((double)nextMove.getWinLoss()[0]/(nextMove.getWinLoss()[0]+nextMove.getWinLoss()[1]))+((0.01*(double)Heuristics.calcHeuristicAv(nextMove.getPlayerOne(),nextMove.getPlayerTwo(),this.playerNumber)[0])));
        this.specialField = nextMove.getSpecialField();
        timeMS[0] = timeMS[0]+ (System.currentTimeMillis()-cTime);
        timeMS[1] = timeMS[1]+1 ;
        return this.specialField;
    }

    private boolean proofAllinHole()
    {
        for(Mole m: moles)
        {
            if(m.getPositionVlaue() != 8)
            {
                return false;
            }
        }
        return true;
    }

    private GameState chooseNextMoveState(GameState root)
    {
        double maxWins = -999999999.0;
        GameState toReturn = root.getChildes().get(0);
        for(GameState g: root.getChildes())
        {
            double molesInHole = (double)g.getMoleSum()[0];
            double games = (double)(g.getWinLoss()[0]+g.getWinLoss()[1]);
            double molesNowInHole = (double)(0.01*((double)Heuristics.calcHeuristicAv(g.getPlayerOne(),g.getPlayerTwo(),this.playerNumber)[0]));

            if((molesInHole/games)+molesNowInHole > maxWins ){
                maxWins = (molesInHole/games)+molesNowInHole;
                toReturn = g;
                g.setChoosemValue((molesInHole/games)+molesNowInHole);
            }
        }
        return toReturn;
    }

    private GameState chooseNextMoveStateGreedy(GameState root)
    {
        double maxValue = -99999;
        GameState toReturn = root;
        for(GameState g: root.getChildes())
        {
            if(((double)Heuristics.calcHeuristicAv(g.getPlayerOne(),g.getPlayerTwo(),this.playerNumber)[0])>maxValue){
                toReturn = g;
                maxValue = ((double)Heuristics.calcHeuristicAv(g.getPlayerOne(),g.getPlayerTwo(),this.playerNumber)[0]);
            }
        }
        return toReturn;
    }

    private void buildTree(GameState root) {
        System.out.print(" !start build Tree");
        expand(root);
        //if stuck
        if(root.getChildes().isEmpty())
        {
            return;
        }
        System.out.print("!start simulate rootchilds ");
        for(GameState gs : root.getChildes())
        {
            gs.simulate();
        }
        System.out.print("!stop simulate rootchilds ");
        runMCTS(root);
        System.out.print("....end build Tree");
    }

    private void runMCTS(GameState root) {
        System.out.print(" !mcts start");
        GameState next = chooseNextNode(root);
        for(int i=0;i<100;i++)
        {
            System.out.print(".....next depth: "+next.depth);

            expand(next);
            //if no childes after expand -> no moves possible from this node -> make simulation in this node not in the Childes
            if(next.getChildes().isEmpty())
            {
                next.simulate();
            }
            next = chooseNextNode(root);
        }
        System.out.print("....mcts end");
    }

    private GameState chooseNextNode(GameState gs)
    {
        double maxUCB = -999999;
        GameState toReturn=gs;
        //!toReturn.getChildes().isEmpty()
        while(toReturn.isExpanded())
        {
            //if childes are empty -> no moves possible
            if(toReturn.getChildes().isEmpty())
            {
                return toReturn;
            }
            //get with max UCB value
            for(GameState g: toReturn.getChildes())
            {
                if(Heuristics.calcUCB((1/Math.sqrt(2)),g)>maxUCB)
                {
                    maxUCB = Heuristics.calcUCB((1/Math.sqrt(2)),g);
                    toReturn = g;
                }
            }
            maxUCB = 0;
        }
        return toReturn;
    }
    public void expand(GameState toExpand) {
        //childes simulated in expansion function
        toExpand.childes = Expansion.determinedSmartSmartRoot(toExpand, toExpand.steps, toExpand.getLvl() , toExpand.depth);
        //for all expansionkK
        System.out.print("!start simulating childes, depth: " + (toExpand.getDepth() + 1));
        for (GameState s : toExpand.childes) {

            s.simulate();
        }
        System.out.print(".......end simulate");
    }

    private boolean proofEnemyAllinHoleOrEmpty(Player enemy){

        if(enemy.getMoles().isEmpty())
        {
            System.out.println("EMPTYYYYYY Moles");
            return true;
        }
        for(Mole m : enemy.getMoles())
        {
            if(m.getPositionVlaue() != 8)
            {
                return false;
            }
        }
        return true;
    }
}
