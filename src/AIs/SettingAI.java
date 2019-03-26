package AIs;

import gamecomponents.Level;

import java.util.ArrayList;

public class SettingAI {

    public static int[] setNextMole(Level lvl) {
        ArrayList<int[]> possibleSettings = new ArrayList<>();
        int rowCounter = 0;
        int colCounter = 0;
        for (int[] row : lvl.getField()) {
            for (int val : row) {
                if (val == 0)
                {
                   possibleSettings.add(new int[]{rowCounter,colCounter, countPossibleMovesInHole(rowCounter,colCounter,lvl)});
                }
                colCounter++;
            }
            colCounter = 0;
            rowCounter++;
        }
        int max = 0;
        int index = 0;
        for(int [] setting : possibleSettings)
        {
            if(setting[2]>max)
            {
                max = setting[2];
                index = possibleSettings.indexOf(setting);
            }
        }
        return possibleSettings.get(index);
    }

    private static int countPossibleMovesInHole(int row, int col, Level lvl) {
        int inHoleCounter = 0;
        //one step
        ArrayList<int[]> toProof = lvl.returnValidMoves(new int[]{row,col},1,false,0);
        inHoleCounter = inHoleCounter + countInHole(toProof)+toProof.size();
        //two steps
        toProof = lvl.returnValidMoves(new int[]{row,col},2,false,0);
        inHoleCounter = inHoleCounter + countInHole(toProof)+toProof.size();
        //three steps
        toProof = lvl.returnValidMoves(new int[]{row,col},3,false,0);
        inHoleCounter = inHoleCounter + countInHole(toProof)+toProof.size();
        //four steps
        toProof = lvl.returnValidMoves(new int[]{row,col},4,false,0);
        inHoleCounter = inHoleCounter + countInHole(toProof)+toProof.size();
        return inHoleCounter;
    }

    private static int countInHole( ArrayList<int[]> toProof)
    {
        int counter = 0;
        for(int[] move : toProof)
        {
            if(move[2] == 8)
            {
                counter++;
            }
        }
        return counter;
    }

}
