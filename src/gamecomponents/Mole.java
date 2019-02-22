package gamecomponents;

public class Mole {

    private int[] position;
    private Player player;

    public Mole(Player p, int[] pos) {
        this.player = p;
        this.position = pos;
    }

    public void setPosition(int[] newPos)
    {
        this.position = newPos;
    }
    public int[] getPosition()
    {
        return this.position;
    }
}
