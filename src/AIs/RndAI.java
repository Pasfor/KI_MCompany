package AIs;

import gamecomponents.Player;
import gamecomponents.Level;
import gamecomponents.Mole;

import java.util.ArrayList;

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
        int rndIndex = (int) (Math.random() * (moveCards.size()-1));
        int moveValue =  moveCards.get(rndIndex);
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
        try{
            Mole moveMole = this.moles.get(((int) Math.random()*getAmountOfMoles()));
            int steps =  drawMoveCard();
            ArrayList<int[]> validMoves = lvl.returnValidMoves(moveMole.getPosition(),steps);
            int moveAmount = validMoves.size();
            moveCards.remove(steps);
            int[] move = validMoves.get((int) Math.random()*moveAmount);
            lvl.getField()[moveMole.getPosition()[0]][moveMole.getPosition()[1]] = moveMole.getPositionVlaue();
            moveMole.setPosition(move,lvl.getField()[move[0]][move[1]]);
        }catch(IndexOutOfBoundsException e )
        {
            System.out.println("Illegal move");
            return;
        }
    }
}
