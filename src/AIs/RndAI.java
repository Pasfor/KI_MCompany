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

    public RndAI (int pN)
    {
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
    public void setMole(Level lvl) {

        int[][] field = lvl.getField();
        int row = (int) (Math.random()*(field.length-1));
        int col = (int) (Math.random()*(field[row].length-1));
        while(field[row][col] != 0)
        {
            row = (int) (Math.random()*(field.length-1));
            col = (int) (Math.random()*(field[row].length-1));
        }

        moles.add(new Mole(this.playerNumber,new int[]{row,col},lvl.getField()[row][col]));
      //setting onto field
        lvl.setMole(row,col,playerNumber);
    }

    @Override
    public int drawMoveCard() {
        if(moveCards.isEmpty())
        {
            initMoveCards();
        }
        int rndIndex = (int) (Math.random() * moveCards.size());
        int moveValue =  moveCards.get(rndIndex);
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

    public void makeMove(Level lvl)
    {
        int steps = drawMoveCard();
        System.out.println("palyer: "+playerNumber+" steps: "+steps);
        //copy Moles
        List<Mole> copyMoles = new ArrayList<>(this.moles);
        //deleting all on hole
        for(Iterator<Mole> iterator = copyMoles.iterator();iterator.hasNext();)
        {
            Mole m = iterator.next();
            if(m.getPositionVlaue() == 8)
            {
                iterator.remove();
            }
        }
        //check if it is possible to move without getting out of hole
        for(Iterator<Mole> iterator = copyMoles.iterator();iterator.hasNext();)
        {
            Mole m = iterator.next();
            if(lvl.returnValidMoves(m.getPosition(),steps).isEmpty())
            {
              iterator.remove();
            }
        }
        //if its not possible use a random Mole of all Moles if not draw one out of copy moles
        if(copyMoles.isEmpty())
        {
            //to speed up remove all Moles not able to move
            List<Mole> copyMolesTwo = new ArrayList<>(this.moles);
            for(Iterator<Mole> iterator = copyMolesTwo.iterator();iterator.hasNext();)
            {
                Mole m = iterator.next();
                if(lvl.returnValidMoves(m.getPosition(),steps).isEmpty())
                {
                    iterator.remove();
                }
            }
            if(!copyMolesTwo.isEmpty()) {
                //get random out of movable Moles
                Mole moveMole = copyMolesTwo.get((int) (Math.random() * copyMolesTwo.size()));
                //get random possible move for this mole
                ArrayList<int[]> possibleMoves = lvl.returnValidMoves(moveMole.getPosition(), steps);
                int[] move = possibleMoves.get((int) (Math.random() * possibleMoves.size()));
                //Move this Mole
                lvl.resetValue(moveMole.getPosition(), moveMole.getPositionVlaue());
                moveMole.setPosition(move, lvl.getField()[move[0]][move[1]]);
                lvl.setMole(move[0], move[1], this.playerNumber);
                lvl.printLVL();
            }
        }
        //if its possible to move without moving out of hole
        else{
            //get random out of movable Moles
            if(!copyMoles.isEmpty()) {
                Mole moveMole = copyMoles.get((int) (Math.random() * copyMoles.size()));
                //get random possible move for this mole
                ArrayList<int[]> possibleMoves = lvl.returnValidMoves(moveMole.getPosition(), steps);
                int[] move = possibleMoves.get((int) (Math.random() * possibleMoves.size()));
                //Move this Mole
                lvl.resetValue(moveMole.getPosition(), moveMole.getPositionVlaue());
                moveMole.setPosition(move, lvl.getField()[move[0]][move[1]]);
                lvl.setMole(move[0], move[1], this.playerNumber);
                lvl.printLVL();
            }
        }
    }
}
