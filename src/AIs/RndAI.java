package AIs;

import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RndAI extends Player {

    private int playerNumber;
    private ArrayList<Integer> moveCards;
    private ArrayList<Mole> moles;

    public RndAI(int pN) {
        this.playerNumber = pN;
        this.moveCards = new ArrayList<>();
        this.moles = new ArrayList<>();
        initMoveCards();
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

        int[][] field = lvl.getField();
        int row = (int) (Math.random() * (field.length - 1));
        int col = (int) (Math.random() * (field[row].length - 1));
        while (field[row][col] != 0) {
            row = (int) (Math.random() * (field.length - 1));
            col = (int) (Math.random() * (field[row].length - 1));
        }

        moles.add(new Mole(this.playerNumber, new int[]{row, col}, lvl.getField()[row][col]));
        //setting onto field
        lvl.setMole(row, col, playerNumber);
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
    public void initMolesToNewLvl(Level lvl) {
        //remove Moles not in hole
        for (Iterator<Mole> iterator = this.moles.iterator(); iterator.hasNext(); ) {
            Mole m = iterator.next();
            if (m.getPositionVlaue() != 8) {
                iterator.remove();
            }
        }
        //set Moles on new lvl
        for (Mole m : this.moles) {
            //get new pos-value
            m.setPosition(m.getPosition(), lvl.getField()[m.getPosition()[0]][m.getPosition()[1]]);
            lvl.setMole(m.getPosition()[0], m.getPosition()[1], this.playerNumber);
        }
    }

    public boolean makeMove(Level lvl, boolean specialFieldHit) {
        int steps = drawMoveCard();
       // System.out.println("palyer: " + playerNumber + " steps: " + steps);

        //check if possible without moving out of hole
        List<Mole> moveableMoles = moveableMolesNotInHole(lvl, steps, specialFieldHit);
        if (moveableMoles.isEmpty()) {
            moveableMoles = allMoveableMoles(lvl, steps, specialFieldHit);
            return moveRandom(lvl, steps, moveableMoles, specialFieldHit);
        } else {
            return moveRandom(lvl, steps, moveableMoles, specialFieldHit);
        }
    }

    /**
     * returns all Moles that are moveable and NOT in a Hole
     *
     * @param lvl
     * @param steps
     * @return
     */
    private List<Mole> moveableMolesNotInHole(Level lvl, int steps, boolean specialFieldHit) {
        //copy Moles
        List<Mole> copyMoles = new ArrayList<>(this.moles);
        //deleting all on hole
        copyMoles.removeIf(m -> m.getPositionVlaue() == 8);
        //check if it is possible to move without getting out of hole
        copyMoles.removeIf(m -> lvl.returnValidMoves(m.getPosition(), steps, specialFieldHit,m.getPositionVlaue()).isEmpty());
        return copyMoles;
    }

    /**
     * returns ALL moveable moles
     *
     * @param lvl
     * @param steps
     * @return
     */
    private List<Mole> allMoveableMoles(Level lvl, int steps, boolean specialFieldHit) {
        List<Mole> copyMoles = new ArrayList<>(this.moles);
        //check if possible to move
        copyMoles.removeIf(m -> lvl.returnValidMoves(m.getPosition(), steps, specialFieldHit,m.getPositionVlaue()).isEmpty());
        return copyMoles;
    }

    /**
     * choose random Mole out of moveable moles and make a random move
     *
     * @param lvl
     * @param steps
     * @param copyMoles
     */
    private boolean moveRandom(Level lvl, int steps, List<Mole> copyMoles, boolean specialFieldHit) {
        if (!copyMoles.isEmpty()) {
            Mole moveMole = copyMoles.get((int) (Math.random() * copyMoles.size()));
            //get random possible move for this mole
            ArrayList<int[]> possibleMoves = lvl.returnValidMoves(moveMole.getPosition(), steps, specialFieldHit,moveMole.getPositionVlaue());
                int[] move = possibleMoves.get((int) (Math.random() * possibleMoves.size()));

            //Move this Mole
            lvl.resetValue(moveMole.getPosition(), moveMole.getPositionVlaue());
            moveMole.setPosition(move, lvl.getField()[move[0]][move[1]]);
            lvl.setMole(move[0], move[1], this.playerNumber);

            if (moveMole.getPositionVlaue() == 9) {
                return true;
            }
        }
        return false;
    }
}
