package gamecomponents;

import java.util.ArrayList;
import java.util.Arrays;

public class Level {
    /**
     * normal Fields = 0
     * Holes = 8
     * special Field = 9
     * <p>
     * player one = 1
     * player two = 2
     */
    private int lvl;
    private int field[][];
    private ArrayList<int[][]> holeCords = new ArrayList<>();
    private ArrayList<int[][]> specialFieldCords = new ArrayList<>();
    private ArrayList<int[][]> notValidWays = new ArrayList<>();
    private ArrayList<int[]> chosenNotValidWays = new ArrayList<>();

    public Level(int lvl) {
        this.lvl = lvl;
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
        this.initLVL();
        this.rotateLVL();
    }

    /**
     * Copy-constructor
     * @param lvl
     */
    public Level(Level toCopy)
    {
//        private int lvl;
//        private int field[][];
//        private ArrayList<int[][]> holeCords = new ArrayList<>();
//        private ArrayList<int[][]> specialFieldCords = new ArrayList<>();
//        private ArrayList<int[][]> notValidWays = new ArrayList<>();
//        private ArrayList<int[]> chosenNotValidWays = new ArrayList<>();
        this.lvl = toCopy.lvl;
        this.field = new int[][]{
                Arrays.copyOf(toCopy.getField()[0],toCopy.getField()[0].length),
                Arrays.copyOf(toCopy.getField()[1],toCopy.getField()[1].length),
                Arrays.copyOf(toCopy.getField()[2],toCopy.getField()[2].length),
                Arrays.copyOf(toCopy.getField()[3],toCopy.getField()[3].length),
                Arrays.copyOf(toCopy.getField()[4],toCopy.getField()[4].length),
                Arrays.copyOf(toCopy.getField()[5],toCopy.getField()[5].length),
                Arrays.copyOf(toCopy.getField()[6],toCopy.getField()[6].length),
                Arrays.copyOf(toCopy.getField()[7],toCopy.getField()[7].length),
                Arrays.copyOf(toCopy.getField()[8],toCopy.getField()[8].length)
        };
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
                break;
            case 2:
                this.holeCords.add(new int[][]{
                        {0, 4},
                        {1, 1},
                        {1, 3},
                        {3, 6},
                        {5, 1},
                        {6, 5},
                        {7, 2}
                });
                this.specialFieldCords.add(new int[][]{
                        {0, 0},
                        {4, 3},
                        {4, 8},
                        {7, 1},

                });

                this.holeCords.add(new int[][]{
                        {1, 4},
                        {2, 1},
                        {3, 6},
                        {4, 8},
                        {5, 1},
                        {6, 5},
                        {7, 2},
                        {8, 0}

                });

                this.specialFieldCords.add(new int[][]{
                        {0, 4},
                        {3, 3},
                        {4, 1},
                        {8, 4}


                });

                this.holeCords.add(new int[][]{
                        {1, 3},
                        {2, 1},
                        {4, 0},
                        {4, 7},
                        {5, 1},
                        {6, 5},
                        {7, 2},
                        {8, 4}

                });

                this.specialFieldCords.add(new int[][]{
                        {1, 1},
                        {3, 4},
                        {4, 8},
                        {8, 0}


                });

                break;
            case 3:
                this.holeCords.add(new int[][]{
                        {0, 2},
                        {5, 0},
                        {5, 7},
                        {6, 3}


                });

                this.specialFieldCords.add(new int[][]{
                        {2, 0},
                        {3, 3},
                        {3, 7},
                        {8, 2}


                });

                this.holeCords.add(new int[][]{
                        {1, 0},
                        {2, 6},
                        {5, 2},
                        {8, 3}


                });

                this.specialFieldCords.add(new int[][]{
                        {0, 2},
                        {3, 4},
                        {6, 0},
                        {7, 5}


                });

                this.holeCords.add(new int[][]{
                        {0, 3},
                        {3, 2},
                        {6, 6},
                        {7, 0}


                });

                this.specialFieldCords.add(new int[][]{
                        {2, 0},
                        {2, 6},
                        {4, 5},
                        {8, 1}


                });

                break;
            case 4:
                this.holeCords.add(new int[][]{
                        {4, 4}


                });

                this.specialFieldCords.add(new int[][]{
                        {0, 0},
                        {3, 6},
                        {5, 1},
                        {8, 4},


                });

                this.notValidWays.add(new int[][]{
                        {3, 4},
                        {4, 3},
                        {5, 4}
                });

                this.holeCords.add(new int[][]{
                        {4, 4}


                });

                this.specialFieldCords.add(new int[][]{
                        {0, 4},
                        {2, 1},
                        {6, 5},
                        {8, 0},


                });

                this.notValidWays.add(new int[][]{
                        {3, 3},
                        {4, 5},
                        {5, 3}
                });

                this.holeCords.add(new int[][]{
                        {4, 4}


                });

                this.specialFieldCords.add(new int[][]{
                        {1, 3},
                        {4, 0},
                        {4, 8},
                        {7, 2},


                });

                this.notValidWays.add(new int[][]{
                        {3, 4},
                        {4, 3},
                        {5, 4}
                });

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

        //if special fields are on this lvl
        if (!this.specialFieldCords.isEmpty()) {
            int[][] specialFields = this.specialFieldCords.get(rand - 1);
            for (int[] pos : specialFields) {
                this.field[pos[0]][pos[1]] = 9;
            }
        }

        //for last lvl -> not valid ways
        if (!this.notValidWays.isEmpty()) {
            int[][] notValid = this.notValidWays.get(rand - 1);
            for (int[] pos : notValid) {
                this.field[pos[0]][pos[1]] = 6;
            }
        }

        rand = (int) (Math.random() * ((2 - 1) + 1)) + 1;
        if (rand == 2) {
            int[][] turnedField = this.field.clone();

            for (int i = 0; i < turnedField.length; i++) {

                turnedField[i] = this.field[8 - i];
                for (int j = 0; j < turnedField[i].length / 2; j++) {
                    int temp = turnedField[i][j];
                    turnedField[i][j] = turnedField[i][turnedField[i].length - j - 1];
                    turnedField[i][turnedField[i].length - j - 1] = temp;
                }


            }
            this.field = turnedField;
        }
        //for last lvl load no valid moves that are blocked by design
        if (this.lvl == 4) {
            int rowIndex = 0;
            for (int[] row : this.field) {
                int columIndex = 0;
                for (int value : row) {
                    if (value == 6) {
                        this.chosenNotValidWays.add(new int[]{columIndex, rowIndex});
                    }
                    columIndex++;
                }
                rowIndex++;
            }
        }
    }

    // returns all valid moves

    /**
     * @// TODO: 22.02.2019
     * proof if collision with other Mole up and down
     * validate and test methods : checkCollision(),validateMovesWithoutCollision()
     */
    public ArrayList<int[]> returnValidMoves(int[] isPosition, int steps, boolean specialFieldMove,int molePosValue) {

        int aboveHalfIndex = 0;
        if (isPosition[0] < 4) {
            aboveHalfIndex = 4 - isPosition[0];
        }

        int underHalfIndex = 0;
        if (isPosition[0] > 4) {
            underHalfIndex = isPosition[0] - 4;
        }

        ArrayList<int[]> toCollisionProof = new ArrayList<>();
        //right
        try {
            toCollisionProof.add(new int[]{isPosition[0], isPosition[1] + steps, this.field[isPosition[0]][isPosition[1] + steps]});
        } catch (ArrayIndexOutOfBoundsException exception) {

        }
        //left
        try {
            toCollisionProof.add(new int[]{isPosition[0], isPosition[1] - steps, this.field[isPosition[0]][isPosition[1] - steps]});
        } catch (ArrayIndexOutOfBoundsException exception) {

        }
        //right down

        //if first half of field
        if ((isPosition[0] + steps) <= 4) {
            try {
                toCollisionProof.add(new int[]{isPosition[0] + steps, isPosition[1]+steps, this.field[isPosition[0] + steps][isPosition[1]+steps]});
            } catch (ArrayIndexOutOfBoundsException exception) {
            }
        }
        //if second half of field or cross the first half
        if ((isPosition[0] + steps) > 4) {
            try {
                toCollisionProof.add(new int[]{isPosition[0] + steps, isPosition[1] + aboveHalfIndex, this.field[isPosition[0] + steps][isPosition[1] + aboveHalfIndex]});
            } catch (ArrayIndexOutOfBoundsException exception) {
            }
        }
        //right up
        //current position in first half
        if (isPosition[0] <= 4) {
            try {
                toCollisionProof.add(new int[]{isPosition[0] - steps, isPosition[1], this.field[isPosition[0] - steps][isPosition[1]]});
            } catch (ArrayIndexOutOfBoundsException exception) {

            }
        }
        //second half
        if (isPosition[0] > 4) {
            if (steps > underHalfIndex) {
                try {
                    toCollisionProof.add(new int[]{isPosition[0] - steps, isPosition[1] + underHalfIndex, this.field[isPosition[0] - steps][isPosition[1] + underHalfIndex]});
                } catch (ArrayIndexOutOfBoundsException exception) {

                }
            } else {
                try {
                    toCollisionProof.add(new int[]{isPosition[0] - steps, isPosition[1] + steps, this.field[isPosition[0] - steps][isPosition[1] + steps]});
                } catch (ArrayIndexOutOfBoundsException exception) {
                }
            }
        }
        //left down
        //first  half
        if (isPosition[0] + steps <= 4) {
            try {
                toCollisionProof.add(new int[]{isPosition[0] + steps, isPosition[1], this.field[isPosition[0] + steps][isPosition[1]]});
            } catch (ArrayIndexOutOfBoundsException exception) {

            }
        }
        //second half
        if (isPosition[0] + steps > 4) {
            try {
                toCollisionProof.add(new int[]{isPosition[0] + steps, isPosition[1] - steps + aboveHalfIndex, this.field[isPosition[0] + steps][isPosition[1] - steps + aboveHalfIndex]});
            } catch (ArrayIndexOutOfBoundsException exception) {

            }
        }
        //left up
        //first half
        if (isPosition[0] <= 4) {
            try {
                toCollisionProof.add(new int[]{isPosition[0] - steps, isPosition[1] - steps, this.field[isPosition[0] - steps][isPosition[1] - steps]});
            } catch (ArrayIndexOutOfBoundsException exception) {

            }
        }
        //second half
        if (isPosition[0] > 4) {
            if(steps>underHalfIndex) {
                try {
                    toCollisionProof.add(new int[]{isPosition[0] - steps, isPosition[1] - (steps - underHalfIndex), this.field[isPosition[0] - steps][isPosition[1] - (steps - underHalfIndex)]});
                } catch (ArrayIndexOutOfBoundsException exception) {

                }
            }else{
                try {
                    toCollisionProof.add(new int[]{isPosition[0] - steps, isPosition[1], this.field[isPosition[0] - steps][isPosition[1]]});
                } catch (ArrayIndexOutOfBoundsException exception) {

                }
            }
        }
        ArrayList<int[]> toReturn = validateMovesWithoutCollision(toCollisionProof, isPosition);

        if (specialFieldMove) {
            toReturn.removeIf(m -> m[2] == 9);
        }
        if(molePosValue == 6)
        {
            toReturn.removeIf(m -> m[2] == 8);
        }
        return toReturn;
    }

    /**
     * validates that no collision with moles happen
     */
    private ArrayList<int[]> validateMovesWithoutCollision(ArrayList<int[]> toProof, int[] isPosition) {
        ArrayList<int[]> validMoves = new ArrayList<>();
        for (int[] moveToProof : toProof) {
            if (checkCollision(isPosition, moveToProof)) {
                validMoves.add(moveToProof);
            }
        }
        return validMoves;
    }

    /**
     * checks for collision in one move
     */
    @SuppressWarnings("Duplicates")
    private boolean checkCollision(int[] pos, int[] move) {
        //check if right or left(same row)
        if (pos[0] == move[0]) {
            //right proofed
            if (pos[1] - move[1] < 0) {
                for (int i = 0; i < Math.abs(pos[1] - move[1]); i++) {
                    //test if Mole on this position
                    int positionValue = this.field[move[0]][pos[1] + Math.abs(pos[1] - move[1]) - i];
                    if (positionValue == 1 || positionValue == 2) {
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }
            //left proofed
            if (pos[1] - move[1] > 0) {
                for (int i = 0; i < Math.abs(pos[1] - move[1]); i++) {
                    //test if Mole on this position
                    int positionValue = this.field[move[0]][pos[1] - Math.abs(pos[1] - move[1]) + i];
                    if (positionValue == 1 || positionValue == 2) {
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }

        }

        //check if down
        if (pos[0] - move[0] < 0 && pos[0] != move[0]) {


            //above mid
            if (pos[1] < move[1] && move[0] <= 4) {
           //   System.out.println("down right not crossing mid");
                for (int i = 0; i < Math.abs(pos[0] - move[0]); i++) {
                    int positionValue = this.field[move[0] - i][move[1] - i];
                    if (positionValue == 1 || positionValue == 2) {
                  //   System.out.println("colision (" + (move[0] - i) + ", " + (move[1] - i) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }
            //under the half
            if (pos[1] == move[1] && move[0] >= 4) {
             //  System.out.println("down right not crossing mid");
                for (int i = 0; i < Math.abs(pos[0] - move[0]); i++) {
                    int positionValue = this.field[move[0] - i][move[1]];
                    if (positionValue == 1 || positionValue == 2) {
                  //   System.out.println("colision (" + (move[0] - i) + ", " + +(move[1]) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }

            if (pos[0] < 4 && move[0] > 4 && pos[1] < move[1]) {
            //  System.out.println("right down crossing mid");
                int crossMidIndex = move[0] - 4;
                int aboveMidIndex = 4 - pos[0];

                for (int i = 1; i <= aboveMidIndex; i++) {
                    int positionValue = this.field[pos[0] + i][pos[1] + i];
                    if (positionValue == 1 || positionValue == 2) {
                    //    System.out.println("colision (" + (pos[0] + i) + ", " + (pos[1] + i) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
                for (int i = 0; i < crossMidIndex; i++) {
                    int positionValue = this.field[move[0] - i][move[1]];
                    if (positionValue == 1 || positionValue == 2) {
                    //    System.out.println("colision (" + (move[0] - i) + ", " + +move[1] + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }


            //above half
            if (pos[1] == move[1] && move[0] <= 4) {
              //  System.out.println("left down not crossing mid");
                for (int i = 1; i <= Math.abs(pos[0] - move[0]); i++) {
                    int positionValue = this.field[pos[0] + i][pos[1]];
                    if (positionValue == 1 || positionValue == 2) {
                    //    System.out.println("colision (" + (pos[0] + i) + ", " + pos[1] + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }
            //under Half
            if (pos[1] > move[1] && move[0] > 4 && pos[0] >= 4) {
               // System.out.println("left down not crossing mid");
                for (int i = 1; i <= Math.abs(pos[0] - move[0]); i++) {
                    int positionValue = this.field[pos[0] + i][pos[1] - i];
                    if (positionValue == 1 || positionValue == 2) {
               //       System.out.println("colision (" + (pos[0] + i) + ", " + (pos[1] - i) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }

            //crossing mid
            if (pos[0] < 4 && move[0] > 4 && pos[1] > move[1]) {
             //  System.out.println("left down crossing mid");
                int crossMidIndex = move[0] - 4;
                int aboveMidIndex = 4 - pos[0];
                for (int i = 1; i < aboveMidIndex; i++) {
                    int positionValue = this.field[pos[0] + i][pos[1]];
                    if (positionValue == 1 || positionValue == 2) {
                  //    System.out.println("colision (" + (pos[0] + i) + ", " + +pos[1] + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
                for (int i = 0; i <= crossMidIndex; i++) {
                    int positionValue = this.field[move[0] - i][move[1] + i];
                    if (positionValue == 1 || positionValue == 2) {
//                       System.out.println("colision (" + (move[0] - i) + ", " + +(move[1] + i) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }
        }

        //check if up
        if (pos[0] - move[0] > 0 && pos[0] != move[0]) {
            //up right

            //not crossing mid

            //above half
            if (pos[0] <= 4 && move[0] < 4 && pos[1] == move[1]) {
          //  System.out.println("right up not crossing mid");
                for (int i = 1; i <= Math.abs(pos[0] - move[0]); i++) {
                    int positionValue = this.field[pos[0] - i][pos[1]];
                    if (positionValue == 1 || positionValue == 2) {
                  //  System.out.println("colision (" + (pos[0] - i) + ", " + pos[1] + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }
            //under half
            if (pos[0] > 4 && move[0] >= 4 && pos[1] < move[1]) {
         //   System.out.println("right up not crossing mid");
                for (int i = 1; i <= Math.abs(pos[0] - move[0]); i++) {
                    int positionValue = this.field[pos[0] - i][pos[1] + i];
                    if (positionValue == 1 || positionValue == 2) {
                //    System.out.println("colision (" + (pos[0] - i) + ", " + (pos[1] + i) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }
            //crossing mid
            if (pos[0] > 4 && move[0] < 4 && pos[1] < move[1]) {
           // System.out.println("right up crossing mid");
                int underMidIndex = pos[0] - 4;
                int aboveMidIndex = 4 - move[0];

                for (int i = 0; i < aboveMidIndex; i++) {
                    int positionValue = this.field[move[0] + i][move[1]];
                    if (positionValue == 1 || positionValue == 2) {
               //    System.out.println("colision (" + (move[0] + i) + ", " + move[1] + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
                for (int i = 1; i <= underMidIndex; i++) {
                    int positionValue = this.field[pos[0] - i][pos[1] + i];
                    if (positionValue == 1 || positionValue == 2) {
                //   System.out.println("colision (" + (pos[0] - i) + ", " + (pos[1] + i) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }

            //up left

            //not crossing mid

            //above half
            if (pos[0] <= 4 && move[0] < 4 && pos[1] > move[1]) {
       //    System.out.println("left up not crossing mid");
                for (int i = 1; i <= Math.abs(pos[0] - move[0]); i++) {
                    int positionValue = this.field[pos[0] - i][pos[1] - i];
                    if (positionValue == 1 || positionValue == 2) {
                //    System.out.println("colision (" + (pos[0] - i) + ", " + (pos[1]-i) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }
                    }
                }
            }
            //under half
            if (pos[0] > 4 && move[0] >= 4 && pos[1] == move[1]) {
       //   System.out.println("left up not crossing mid");
                for (int i = 1; i <= Math.abs(pos[0] - move[0]); i++) {
                    int positionValue = this.field[pos[0] - i][pos[1]];
                    if (positionValue == 1 || positionValue == 2) {
            //      System.out.println("colision (" + (pos[0] - i) + ", " + (pos[1]) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }
            //crossing mid
            if (pos[0] > 4 && move[0] < 4 && pos[1] > move[1]) {
        //   System.out.println("left up crossing mid");
                int underMidIndex = pos[0] - 4;
                int aboveMidIndex = 4 - move[0];

                for (int i = 0; i < aboveMidIndex; i++) {
                    int positionValue = this.field[move[0] + i][move[1] + i];
                    if (positionValue == 1 || positionValue == 2) {
                   // System.out.println("colision (" + (move[0] + i) + ", " + (move[1]+i) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }
                    }
                }
                for (int i = 1; i <= underMidIndex; i++) {
                    int positionValue = this.field[pos[0] - i][pos[1]];
                    if (positionValue == 1 || positionValue == 2) {
             //System.out.println("colision (" + (pos[0] - i) + ", " + (pos[1]) + ") " + positionValue);
                        return false;
                    }
                    //for last lvl
                    if (this.lvl == 4 && move[2] == 8) {
                        if (!proofValidLastMove(pos, positionValue)) {
                            return false;
                        }

                    }
                }
            }
        }
        return true;
    }

    /**
     * proofs if last move on lvl 4 is valid
     *
     * @param pos
     * @param positionValue
     * @return
     */
    private boolean proofValidLastMove(int[] pos, int positionValue) {
   //     System.out.println("to check:" + pos[0] + "," + pos[1]);
     //   System.out.println(positionValue);
        if (positionValue == 6) {
            return false;
        }
        //if mole is on field with value 6
        for (int[] notValidWay : this.chosenNotValidWays) {
            if (notValidWay[0] == pos[0] && notValidWay[1] == pos[1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * setting mole on a valid position
     * @param row
     * @param col
     * @param playerNumber
     * @return
     */
    public void setMole(int row, int col, int playerNumber) {
        this.field[row][col] = playerNumber;
    }

    public int[][] getField() {
        return this.field;
    }

    public boolean levelFinish() {
        for (int[] row : this.field) {
            for (int value : row) {
                if (value == 8) {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetValue(int[] pos, int value) {
        this.field[pos[0]][pos[1]] = value;
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
        System.out.println();
    }
}
