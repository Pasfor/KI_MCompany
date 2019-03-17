package GameSession;

import GUI.Controller;
import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class RealPlayer extends Player {
    private int playerNumber;
    private ArrayList<Integer> moveCards;
    private ArrayList<Mole> moles;
    private Controller controler;
    private int[] fromTo = new int[]{100,100};
    private int moveValue;
    private GameSessionUI gameSession;

    public RealPlayer(int playerNumber, Controller controller, GameSessionUI gameSession) {
        this.playerNumber = playerNumber;
        this.moveCards = new ArrayList<>();
        this.moles = new ArrayList<>();
        initMoveCards();
        this.controler = controller;
        this.gameSession = gameSession;
    }

    public void initMoveCards()
    {
        this.moveCards.add(1);
        this.moveCards.add(2);
        this.moveCards.add(2);
        this.moveCards.add(3);
        this.moveCards.add(3);
        this.moveCards.add(4);
    }

    public int getAmountOfMoles()
    {
       return this.moles.size();
    }

    @Override
    public boolean setMole(Level level, int pos) {
        int index = pos;
        int rowCounter = 0;
        int colCounter = 0;

        for(int[] row : level.getField())
        {
            for (int value : row)
            {
                if(index == 0)
                {
                    if(value == 0)
                    {
                        moles.add(new Mole(this.playerNumber, new int[]{rowCounter, colCounter}, level.getField()[rowCounter][colCounter]));
                        //setting onto field
                        level.setMole(rowCounter, colCounter, playerNumber);
                        return true;
                    }
                    else{
                        return false;
                    }

                }
                colCounter++;
                index--;
            }
            rowCounter++;
            colCounter =0;
        }
        return false;
    }
    @Override
    public boolean makeMove(Level lvl, boolean specialField) {
        return validMove(lvl,fromTo,moveValue,specialField);
    }
    /**
     * proofs if valid
     * @return
     */
    public boolean validMove(Level lvl, int[] fromTo, int moveValue, boolean specialField){

        //transform fromTo into positions
        if(fromTo[0]==100)
        {
            return false;
        }
        int[] from = transformIntoPos(fromTo[0],lvl);
        int[] to = transformIntoPos(fromTo[1],lvl);

        //proof if own Mole
        if(lvl.getField()[from[0]][from[1]] != this.playerNumber)
        {
    //        System.out.println("nicht dein mole");
            return false;
        }
        //get this mole
        Mole moveMole = null;
        for(Mole m: moles)
        {
            if(m.getPosition()[0] == from[0] && m.getPosition()[1]==from[1])
            {
                moveMole = m;
            }
        }
        if(moveMole == null)
        {
     //       System.out.println("moveMole null!");
        }
        //proof for valid move
        //get possible moves
        ArrayList<int[]> possibleMoves = lvl.returnValidMoves(from,moveValue,specialField);
        if(possibleMoves == null)
        {
            return false;
        }
        for(int[] possibleMove : possibleMoves)
        {


            if(possibleMove[0]==to[0] && possibleMove[1]==to[1])
            {
                lvl.resetValue(moveMole.getPosition(), moveMole.getPositionVlaue());
                moveMole.setPosition(to, lvl.getField()[to[0]][to[1]]);
                if(lvl.getField()[to[0]][to[1]] == 9)
                {
                    controler.changePlayerLabel("Special Field!");
                    gameSession.changePlayer();
                }
                lvl.setMole(to[0], to[1], this.playerNumber);
                lvl.printLVL();
                return true;
            }
        }
        return false;
    }

    public int[] transformIntoPos(int i, Level level)
    {
        int index= i;
        int rowCounter = 0;
        int colCounter = 0;
        int[] pos = new int[]{100,100};

        for(int[] row : level.getField())
        {
            for (int value : row)
            {
                if(index == 0)
                {
                 pos[0] = rowCounter;
                 pos[1] = colCounter;
                }
                colCounter++;
                index--;
            }
            rowCounter++;
            colCounter =0;
        }

        return pos;
    }
    public int drawMoveCard()
    {
        if (moveCards.isEmpty()) {
            initMoveCards();
        }
        int rndIndex = (int) (Math.random() * moveCards.size());
        int moveValue = moveCards.get(rndIndex);
        moveCards.remove(moveCards.get(rndIndex));
        return moveValue;
    }

    public ArrayList<Mole> getMoles()
    {
        return this.moles;
    }

    public int getPlayerNumber()
    {
        return this.playerNumber;
    }
    public void setFromTo(int[] ft)
    {
        this.fromTo = ft;
    }
    public void setMoveValue(int moveValue){
        this.moveValue = moveValue;
    }
    @Override
    public void initMolesToNewLvl(Level lvl) {
        for(Iterator<Mole> iterator = this.moles.iterator(); iterator.hasNext();)
        {
            Mole m = iterator.next();
            if(m.getPositionVlaue() != 8)
            {
                iterator.remove();
            }
        }
        //set Moles on new lvl
        for(Mole m: this.moles)
        {
            //get new pos-value
            m.setPosition(m.getPosition(),lvl.getField()[m.getPosition()[0]][m.getPosition()[1]]);
            lvl.setMole(m.getPosition()[0],m.getPosition()[1],this.playerNumber);
        }
    }
}
