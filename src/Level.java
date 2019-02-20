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
    public ArrayList<int[]> returnValidMoves(int[] isPosition, int steps) {

        int aboveHalfIndex = 0;
        if (isPosition[0] < 4) {
            aboveHalfIndex = 4 - isPosition[0];
        }

        int underHalfIndex = 0;
        if (isPosition[0] > 4) {
            underHalfIndex = isPosition[0] - 4;
        }

        ArrayList<int[]> toReturn = new ArrayList<>();
        //right
        try {
            toReturn.add(new int[]{isPosition[0], isPosition[1] + steps, this.field[isPosition[0]][isPosition[1] + steps]});
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("No right movement possible");
        }
        //left
        try {
            toReturn.add(new int[]{isPosition[0], isPosition[1] - steps, this.field[isPosition[0]][isPosition[1] - steps]});
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("No left movement possible");
        }
        //right down

        //if first half of field
        if (isPosition[0] + steps <= 4) {
            try {
                toReturn.add(new int[]{isPosition[0] + steps, isPosition[1], this.field[isPosition[0] + steps][isPosition[1]]});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No right-down movement possible");
            }
        }
        //if second half of field or cross the first half
        if (isPosition[0] + steps > 4) {
            try {
                toReturn.add(new int[]{isPosition[0] + steps, isPosition[1] + aboveHalfIndex, this.field[isPosition[0] + steps][isPosition[1] + aboveHalfIndex]});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No right-down movement possible");
            }
        }
        //right up
        //current position in first half
        if (isPosition[0] <= 4) {
            try {
                toReturn.add(new int[]{isPosition[0] - steps, isPosition[1], this.field[isPosition[0] - steps][isPosition[1]]});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No right-up movement possible");
            }
        }
        //second half
        if (isPosition[0] > 4) {
            try {
                toReturn.add(new int[]{isPosition[0] - steps, isPosition[1] + underHalfIndex, this.field[isPosition[0] - steps][isPosition[1] + underHalfIndex]});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No right-up movement possible");
            }
        }
        //left down
        //first  half
        if (isPosition[0] + steps <= 4) {
            try {
                toReturn.add(new int[]{isPosition[0] + steps, isPosition[1], this.field[isPosition[0] + steps][isPosition[1]]});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No left-down movement possible");
            }
        }
        //second half
        if (isPosition[0] + steps > 4) {
            try {
                toReturn.add(new int[]{isPosition[0] + steps, isPosition[1] - steps+aboveHalfIndex, this.field[isPosition[0] + steps][isPosition[1] - steps+aboveHalfIndex]});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No left-down movement possible");
            }
        }
        //left up
        //first half
        if (isPosition[0] <= 4) {
            try {
                toReturn.add(new int[]{isPosition[0] - steps, isPosition[1] - steps, this.field[isPosition[0] - steps][isPosition[1] - steps]});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No left-up movement possible");
            }
        }
        //second half
        if(isPosition[0]>4)
        {
            try {
                toReturn.add(new int[]{isPosition[0] - steps, isPosition[1] - (steps- underHalfIndex), this.field[isPosition[0] - steps][isPosition[1] - (steps- underHalfIndex)]});
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("No left-up movement possible");
            }
        }

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
