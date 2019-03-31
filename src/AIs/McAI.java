package AIs;

import AIComponents.GameState;
import AIComponents.Heuristics;
import AIComponents.SettingAI;
import AIComponents.SimulatingPlayer;
import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;

public class McAI extends Player {
    private int playerNumber;
    private ArrayList<Integer> moveCards;
    private ArrayList<Mole> moles;
    private Player enemy;
    private boolean moved = true;
    private int steps;
    private boolean specialField = false;


    public McAI(int pN, Player otherPlayer) {
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
        steps = drawMoveCard();
        GameState root = new GameState(new SimulatingPlayer(this),new SimulatingPlayer(enemy),new Level(lvl),0,null,0,this.specialField);

        buildTree(root);

        //if stuck
        if(root.getChildes().isEmpty())
        {
            this.moved = false;
            lvl.printLVL();
            return false;
        }
        GameState nextMove = chooseNextMoveState(root);
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

        System.out.println("steps: "+steps);
        System.out.println("next move depth: "+nextMove.getDepth());
       // nextMove.getLvl().printLVL();
        if(nextMove.getPlayer().getPlayerNumber() == 1) {
            System.out.println("Moles of Player: " + nextMove.getPlayer().getPlayerNumber());
            System.exit(1);
        }
        this.moles = nextMove.getPlayer().getMoles();
        lvl.overWrite(nextMove.getLvl());
        for(Mole m :this.moles)
        {
            System.out.print(m.getPosition()[0]+","+m.getPosition()[1]+"|");
        }
        if(checksum > 20)
        {
            lvl.printLVL();
            System.exit(checksum);
        }
        System.out.println();
        for(GameState g : root.getChildes())
        {
            System.out.println("Heuristic:"+ Heuristics.calcHeuristic(g.getPlayerOne(),g.getPlayerTwo())+"|"+((g.getWinLoss()[0])+(g.getWinLoss()[1]))+"|"+g.getWinLoss()[0]+","+g.getWinLoss()[1]+"|"+ Heuristics.calcUCB(Math.sqrt(2),g));
        }
        //Special field !!!
        System.out.println("Choosen next: "+Heuristics.calcUCB(Math.sqrt(2),nextMove));
        this.specialField = nextMove.getSpecialField();
        return this.specialField;
    }
     private GameState chooseNextMoveState(GameState root)
     {
         double maxWins = -999999999;
         GameState toReturn = root;
             //get with max UCB value
             for(GameState g: root.getChildes())
             {
                 if(((double)g.getWinLoss()[0]/(g.getWinLoss()[0]+g.getWinLoss()[1]))> maxWins)
                 {
                     maxWins = (double)g.getWinLoss()[0]/(g.getWinLoss()[0]+g.getWinLoss()[1]);
                     toReturn = g;
                 }
             }
         return toReturn;
     }

    private void buildTree(GameState root) {
        System.out.print(" !start build Tree");
        root.expand();
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
        System.out.println("!choose next");
        System.out.println(".....next chosen");
        GameState next = chooseNext(root);
        for(int i=0;i<1000;i++)
        {
            System.out.print("!start simulate , depth: "+next.getDepth());
            next.simulate();
            System.out.print(".......end simulate");
                 if(next.getDepth()<200 ) {
                    next.expand();
                    for(GameState s: next.getChildes())
                    {
                        s.simulate();
                    }
                }
                next = chooseNext(root);
        }
        System.out.print("....mcts end");
    }

    private GameState chooseNext(GameState gs)
    {
        double maxUCB = -999999;
        GameState toReturn=gs;
        while(!(toReturn.getChildes().isEmpty()))
        {
            //get with max UCB value
            for(GameState g: toReturn.getChildes())
            {
                if(Heuristics.calcUCB(Math.sqrt(2),g)>maxUCB)
                {
                    maxUCB = Heuristics.calcUCB(Math.sqrt(2),g);
                    toReturn = g;
                }
            }
            maxUCB = 0;
        }
        return toReturn;
    }
}
