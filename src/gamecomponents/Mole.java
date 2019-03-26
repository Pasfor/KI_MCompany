package gamecomponents;

import GameSession.RealPlayer;

public class Mole {

    private int[] position;
    private int positionValue;
    private int playerNumber;
    private boolean set;

    public Mole(int p, int[] pos, int positionValue) {
        this.playerNumber = p;
        this.position = pos;
        this.positionValue = positionValue;
        this.set = true;
    }

    public Mole (Mole toCopy)
    {
       this.playerNumber = toCopy.playerNumber;
       this.position = toCopy.position.clone();
       this.positionValue = toCopy.getPositionVlaue();
    }
    public void setPosition(int[] newPos, int newPositionValue)
    {
        this.position = newPos;
        this.positionValue = newPositionValue;
        this.set = true;
    }
    public int[] getPosition()
    {
        return this.position;
    }

    public boolean isSet()
    {
        return this.set;
    }

    public int getPlayerNumber()
    {
        return this.playerNumber;
    }

    public int getPositionVlaue()
    {
        return this.positionValue;
    }
}
