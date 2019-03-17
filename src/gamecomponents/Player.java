package gamecomponents;

import java.util.ArrayList;

public abstract class Player {


    public abstract void initMoveCards();

    public abstract int getAmountOfMoles();

    public abstract boolean setMole(Level lvl, int circle);

    public abstract int drawMoveCard();

    public abstract ArrayList<Mole> getMoles();

    public abstract int getPlayerNumber();

    public abstract boolean makeMove(Level lvl,boolean specialFieldHit) ;

    public abstract void initMolesToNewLvl(Level lvl);
}

