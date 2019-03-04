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

        int steps =  drawMoveCard();
        Mole moveMole = this.moles.get(((int) Math.random()*getAmountOfMoles()));

        System.out.println("steps :"+steps);
        System.out.println("player: "+this.playerNumber);


            ArrayList<int[]> validMoves = lvl.returnValidMoves(moveMole.getPosition(),steps);

            while(validMoves.isEmpty())
            {
                int rnd = ((int) Math.random()*getAmountOfMoles());
                moveMole = this.moles.get(rnd);
                System.out.println("rnd mole 1: "+rnd);
                while(moveMole.getPositionVlaue() == 8)
                {
                    rnd = (int) (Math.random()*getAmountOfMoles());
                    moveMole = this.moles.get(rnd);
                    System.out.println("rnd mole: "+rnd);
                }
                validMoves = lvl.returnValidMoves(moveMole.getPosition(),steps);
            }
            int moveAmount = validMoves.size();
            int[] move = validMoves.get(((int) Math.random()*moveAmount));


        System.out.println(moveMole.getPosition()[0]+","+moveMole.getPosition()[1]+" to "+move[0]+","+move[1]);

            lvl.resetValue(moveMole.getPosition(),moveMole.getPositionVlaue());
            moveMole.setPosition(move,lvl.getField()[move[0]][move[1]]);
            lvl.setMole(move[0],move[1],this.playerNumber);
            lvl.printLVL();

    }
}
