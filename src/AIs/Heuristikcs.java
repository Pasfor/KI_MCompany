package AIs;

import gamecomponents.Level;

public class Heuristikcs {


    // diff between in hole player one and player two
    public static int calcHeuristikone(Level lvl)
    {
        int one=0;
        int two=0;

        for(int[] row : lvl.getField())
        {
            for(int value : row)
            {
                if(value == 1)
                {
                    one++;
                }
                if(value == 2)
                {
                    two++;
                }
            }
        }
        return one-two;
    }
}
