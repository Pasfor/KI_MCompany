package GameSession;

import gamecomponents.Player;
import gamecomponents.Level;
import gamecomponents.Mole;

import java.util.ArrayList;
import java.util.Iterator;

public class RealPlayer extends Player {
    private int playerNumber;
    private ArrayList<Integer> moveCards;
    private ArrayList<Mole> moles;

    public RealPlayer(int playerNumber) {
        this.playerNumber = playerNumber;
        this.moveCards = new ArrayList<>();
        this.moles = new ArrayList<>();
        initMoveCards();
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
    public void setMole(Level level) {

    }


    public int drawMoveCard()
    {
        if(moveCards.isEmpty())
        {
            initMoveCards();
        }
        int rndIndex = (int) (Math.random() * (moveCards.size()-1));
        int moveValue =  moveCards.get(rndIndex);
        moveCards.remove(moveValue);
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

    @Override
    public void makeMove(Level lvl) {

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
