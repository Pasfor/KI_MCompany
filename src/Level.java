import java.util.ArrayList;

public class Level {
    private int lvl;
    private int field[][];

    public Level(int lvl) {
        this.lvl = lvl;
        this.initLVL();
    }

    private void initLVL() {
        switch (this.lvl) {
            case 1:
                System.out.println("one");
                this.field = new int[][]{
                        {0, 8, 0, 0, 0},
                        {0, 0, 0, 0, 0, 8},
                        {0, 0, 0, 8, 0, 0, 0},
                        {8, 0, 8, 0, 0, 0, 0, 8},
                        {0, 0, 0, 0, 8, 0, 0, 0, 0},
                        {8, 0, 0, 0, 0, 8, 0, 0},
                        {0, 0, 8, 8, 0, 0, 8},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 8, 0, 0}
                };
                break;
            case 2:
                System.out.println("two");
                break;
            case 3:
                System.out.println("three");
                break;
            case 4:
                System.out.println("four");
                break;
        }
    }

    public void rotateLVL() {
        int[][] rotatedField = this.field.clone();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rotatedField[4 - i][i] = this.field[i][j];
            }
        }
        this.field = rotatedField;
    }

    // returns all valid moves
    public ArrayList<int[]> returnValidMoves(int[] isPosition, int[] toPosition, int steps) {
        ArrayList<int[]> toReturn = new ArrayList<>();
        //right
        try {
            int fieldValue = this.field[isPosition[0]][isPosition[1] + steps];
            if (fieldValue == 0) {
                toReturn.add(new int[]{isPosition[0], isPosition[1] + steps, 0});
            }
            if (fieldValue == 8) {
                toReturn.add(new int[]{isPosition[0], isPosition[1] + steps, 8});
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("No right movement possible");
        }
       //left
        try {
            int fieldValue = this.field[isPosition[0]][isPosition[1] - steps];
            if (fieldValue == 0) {
                toReturn.add(new int[]{isPosition[0], isPosition[1] - steps, 0});
            }
            if (fieldValue == 8) {
                toReturn.add(new int[]{isPosition[0], isPosition[1] - steps, 8});
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("No left movement possible");
        }
        //right down
        if(isPosition[0]+steps <=4) {
            try {
            int fieldValue = this.field[isPosition[0] + steps][isPosition[1]];
            if (fieldValue == 0) {
                toReturn.add(new int[]{isPosition[0] + steps, isPosition[1], 0});
            }
            if (fieldValue == 8) {
                toReturn.add(new int[]{isPosition[0] + steps, isPosition[1], 8});
            }
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("No right-down movement possible");
        }
    }
        if(isPosition[0]+steps >=4)
        {
            int aboveHalfindex=0;
            if(isPosition[0]<4)
            {
                aboveHalfindex = 5-isPosition[0];
            }

            try {
                int fieldValue = this.field[isPosition[0] + steps][isPosition[1]+aboveHalfindex];
                if (fieldValue == 0) {
                    toReturn.add(new int[]{isPosition[0] + steps, isPosition[1]+aboveHalfindex, 0});
                }
                if (fieldValue == 8) {
                    toReturn.add(new int[]{isPosition[0] + steps, isPosition[1]+aboveHalfindex, 8});
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No right-down movement possible");
            }
        }
        //right up
        //left down
        //left up

        return toReturn;
    }

    //____Log functions___
    public void printLVL() {
        System.out.println();
        for (int[] line : this.field) {
            System.out.println();
            for (int i = 0; i < (9 - line.length); i++) {
                System.out.print(" ");
            }
            for (int field : line) {
                System.out.print(" " + field);
            }
        }
    }
}
