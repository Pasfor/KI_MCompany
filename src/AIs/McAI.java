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

//        int[][] field = lvl.getField();
//        int row = (int) (Math.random() * (field.length - 1));
//        int col = (int) (Math.random() * (field[row].length - 1));
//        while (field[row][col] != 0) {
//            row = (int) (Math.random() * (field.length - 1));
//            col = (int) (Math.random() * (field[row].length - 1));
//        }
//
//        moles.add(new Mole(this.playerNumber, new int[]{row, col}, lvl.getField()[row][col]));
//        //setting onto field
//        lvl.setMole(row, col, playerNumber);
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
        for (Iterator<Mole> iterator = this.moles.iterator(); iterator.hasNext(); ) {
            Mole m = iterator.next();
            if (m.getPositionVlaue() != 8) {
                iterator.remove();
            }
        }
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

        GameState root = new GameState(new SimulatingPlayer(this),new SimulatingPlayer(enemy),new Level(lvl),1000);
        buildTree(root);

        return true;
    }

    private void buildTree(GameState root) {
        root.expand();
        for(GameState gs : root.getChilds())
        {
            gs.simulate();
            root.addWinLoss(gs.getWinLoss());
        }
    }
}
