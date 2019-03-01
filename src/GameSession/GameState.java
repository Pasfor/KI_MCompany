package GameSession;

import gamecomponents.Player;

public class GameState {

    private int[][] field;

    private Player pOne;
    private Player pTwo;


    public GameState(int[][] field, Player pOne, Player pTwo)
    {
     this.field = field;

    }

    public int[][] getField()
    {
        return this.field;
    }
}
