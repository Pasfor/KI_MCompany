package gamecomponents;

import GameSession.Player;

public class Mole {

    private int[] position;
    private Player player;
    private boolean set;

    public Mole(Player p, int[] pos) {
        this.player = p;
        this.position = pos;
    }

    public void setPosition(int[] newPos)
    {
        this.position = newPos;
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
}
