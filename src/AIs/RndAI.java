package AIs;

import java.util.ArrayList;

public class RndAI {

    public int[] makeMove(ArrayList<int[]> possibleMoves)
    {
        return possibleMoves.get((int) (Math.random() * ((possibleMoves.size()-2) + 0)));
    }

}
