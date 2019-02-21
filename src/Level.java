import java.util.ArrayList;

public class Level {
    private int lvl;
    private int field[][];
    private ArrayList<int[][]> holeCords = new ArrayList<>();

    public Level(int lvl) {
        this.lvl = lvl;
        this.initLVL();
    }

    private void initLVL() {
        switch (this.lvl) {
            case 1:
                this.holeCords.add(new int[][]{
                        {0, 1},
                        {1, 5},
                        {2, 3},
                        {3, 0},
                        {3, 2},
                        {3, 7},
                        {4, 4},
                        {5, 0},
                        {5, 5},
                        {6, 2},
                        {6, 3},
                        {6, 6},
                        {8, 2}
                });
                this.holeCords.add(new int[][]{
                        {0, 1},
                        {1, 0},
                        {1, 5},
                        {2, 3},
                        {3, 5},
                        {4, 2},
                        {4, 4},
                        {5, 2},
                        {6, 0},
                        {6, 3},
                        {7, 5},
                        {8, 2}

                });
                this.holeCords.add(new int[][]{
                        {0, 3},
                        {1, 5},
                        {2, 0},
                        {2, 2},
                        {3, 2},
                        {3, 5},
                        {4, 4},
                        {5, 2},
                        {5, 5},
                        {5, 7},
                        {6, 0},
                        {8, 1},
                        {8, 3},

                });
                this.field = new int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                };
                rotateLVL();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
        }
    }

    private void rotateLVL() {
        //random first three opportunities
        int rand = (int) (Math.random() * ((3 - 1) + 1)) + 1;
        int[][] holes = this.holeCords.get(rand - 1);

        for (int[] pos : holes) {
            this.field[pos[0]][pos[1]] = 8;
        }

        rand = (int) (Math.random() * ((2 - 1) + 1)) + 1;
        System.out.println(rand+"\n \n ");
        //turn 180 if rand is 2
        this.printLVL();
        if(rand == 2)
        {
            int[][] turnedField = this.field.clone();

            for(int i=0; i<turnedField.length; i++)
            {
                
                turnedField[i] = this.field[8-i];
                for(int j=0; j<turnedField[i].length/2; j++)
                { 
                    int temp = turnedField[i][j];
                    turnedField[i][j] = turnedField[i][turnedField[i].length -j -1];
                    turnedField[i][turnedField[i].length -j -1] = temp;
                }


            }
            this.field = turnedField;
        }
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
                toReturn.add(new int[]{isPosition[0] + steps, isPosition[1] - steps + aboveHalfIndex, this.field[isPosition[0] + steps][isPosition[1] - steps + aboveHalfIndex]});
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
        if (isPosition[0] > 4) {
            try {
                toReturn.add(new int[]{isPosition[0] - steps, isPosition[1] - (steps - underHalfIndex), this.field[isPosition[0] - steps][isPosition[1] - (steps - underHalfIndex)]});
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
