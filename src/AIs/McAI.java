package AIs;

import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class McAI extends Player {
    private int playerNumber;
    private ArrayList<Integer> moveCards;
    private ArrayList<Mole> moles;
    private Player enemy;


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
        int steps = drawMoveCard();
        System.out.println(steps);
        GameState root = new GameState(new SimulatingPlayer(this),new SimulatingPlayer(enemy),new Level(lvl),0,null,steps);
        buildTree(root);

        GameState nextMove = chooseNextMoveState(root);

        this.moles = nextMove.getPlayerOne().getMoles();
        lvl.overWrite(nextMove.getLvl());

        lvl.printLVL();
        //Special
        return false;
    }
     public GameState chooseNextMoveState(GameState root)
     {
         double maxWins = -9999999;
         GameState toReturn = root;
             //get with max UCB value
             for(GameState g: root.getChilds())
             {
                 if((g.getWinLoss()[0]-g.getWinLoss()[1])> maxWins)
                 {
                     maxWins = g.getWinLoss()[0]-g.getWinLoss()[1];
                     toReturn = g;
                 }
             }

         return toReturn;
     }

    private void buildTree(GameState root) {
        root.expand();

        for(GameState gs : root.getChilds())
        {
            gs.simulate();
        }
        runMCTS(root);
    }

    private void runMCTS(GameState root) {

        GameState next = chooseNext(root);

        for(int i=0;i<100;i++)
        {
                next.simulate();
                if(next.getDepth()<1) {
                    next.expand();
                    for(GameState s: next.getChilds())
                    {
                        s.simulate();
                    }
                }
                next = chooseNext(root);
        }
    }

    private GameState chooseNext(GameState gs)
    {
        double maxUCB = 0;
        GameState toReturn=gs;
        while(!toReturn.getChilds().isEmpty())
        {
            //get with max UCB value
            for(GameState g: toReturn.getChilds())
            {
                if(Heuristikcs.calcUCB(2,g)>maxUCB)
                {
                    maxUCB = Heuristikcs.calcUCB(2,g);
                    toReturn = g;
                }
            }
            maxUCB = 0;
        }
        return toReturn;
    }
}
