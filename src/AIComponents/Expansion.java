package AIComponents;

import gamecomponents.Level;
import gamecomponents.Mole;

import java.util.ArrayList;
import java.util.Random;

public class Expansion {

    public static ArrayList<GameState> classicExpansion(GameState toExpand, int steps, Level lvl, int depth) {
        ArrayList<GameState> childes = new ArrayList<>();
        if (steps == 0) {
            int moleIndex = 0;
            for (Mole m : toExpand.getPlayerOne().getMoles()) {
                ArrayList<Integer> used = new ArrayList<>();
                for (int stps : toExpand.getPlayerOne().getMoveCards()) {
                    if (used.contains(stps)) {
                        continue;
                    }
                    used.add(stps);
                    ArrayList<int[]> moves = lvl.returnValidMoves(m.getPosition(), stps, false, m.getPositionVlaue());
                    for (int[] move : moves) {
                        Level copyLevel = new Level(lvl);
                        SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                        SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                        //remove  move Card
                        copyPone.getMoveCards().remove(new Integer(stps));
                        //if zero refresh cards
                        if (copyPone.getMoveCards().size() == 0) {
                            copyPone.initMoveCards();
                        }
                        copyLevel.resetValue(copyPone.getMoles().get(moleIndex).getPosition(), copyPone.getMoles().get(moleIndex).getPositionVlaue());
                        copyPone.getMoles().get(moleIndex).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                        copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                        if (copyPone.getMoles().get(moleIndex).getPositionVlaue() == 9) {
                            childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true, toExpand.getPlayerNumber()));
                        } else {
                            childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false, toExpand.getPlayerNumber()));
                        }
                    }
                }
                moleIndex++;
            }
        } else {
            //for root
            for (int i = 0; i < toExpand.getPlayerOne().getMoles().size(); i++) {
                ArrayList<int[]> moves = lvl.returnValidMoves(toExpand.getPlayerOne().getMoles().get(i).getPosition(), steps, false, toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue());
                for (int[] move : moves) {
                    Level copyLevel = new Level(lvl);
                    SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                    SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                    copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                    copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                    //remove  move Card
                    copyPone.getMoveCards().remove(new Integer(steps));
                    //if zero refresh cards
                    if (copyPone.getMoveCards().size() == 0) {
                        copyPone.initMoveCards();
                    }
                    copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                    if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                        childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true, toExpand.getPlayerNumber()));
                    } else {
                        childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false, toExpand.getPlayerNumber()));
                    }
                }
            }
        }
        toExpand.setExpanded();
        return childes;
    }

    //1) not out of hole smartRoot
    public static ArrayList<GameState> smartExpansionSmartRoot(GameState toExpand, int steps, Level lvl, int depth) {

        ArrayList<GameState> childes = new ArrayList<>();

        //for root
        if (steps != 0) {
            for (int i = 0; i < toExpand.getPlayerOne().getMoles().size(); i++) {
                //exclude moles in hole;
                if (toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue() == 8) {
                    continue;
                }
                ArrayList<int[]> moves = lvl.returnValidMoves(toExpand.getPlayerOne().getMoles().get(i).getPosition(), steps, false, toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue());
                for (int[] move : moves) {
                    Level copyLevel = new Level(lvl);
                    SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                    SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                    //remove  move Card
                    copyPone.getMoveCards().remove(new Integer(steps));
                    //if zero refresh cards
                    if (copyPone.getMoveCards().size() == 0) {
                        copyPone.initMoveCards();
                    }
                    copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                    copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                    copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                    if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                        childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true, toExpand.getPlayerNumber()));
                    } else {
                        childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false, toExpand.getPlayerNumber()));
                    }
                }
            }
        } else {
            int moleIndex = 0;
            for (Mole m : toExpand.getPlayerOne().getMoles()) {

                ArrayList<Integer> used = new ArrayList<>();
                for (int stps : toExpand.getPlayerOne().getMoveCards()) {
//                    if (used.contains(stps)) {
//                        continue;
//                    }
//                    used.add(stps);
                    //exclude out of hole
                    if (m.getPositionVlaue() == 8) {
                        continue;
                    }
                    ArrayList<int[]> moves = lvl.returnValidMoves(m.getPosition(), stps, false, m.getPositionVlaue());
                    for (int[] move : moves) {
                        Level copyLevel = new Level(lvl);
                        SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                        SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                        copyLevel.resetValue(copyPone.getMoles().get(moleIndex).getPosition(), copyPone.getMoles().get(moleIndex).getPositionVlaue());
                        copyPone.getMoles().get(moleIndex).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
//remove  move Card
                        copyPone.getMoveCards().remove(new Integer(stps));
                        //if zero refresh cards
                        if (copyPone.getMoveCards().size() == 0) {
                            copyPone.initMoveCards();
                        }
                        copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                        if (copyPone.getMoles().get(moleIndex).getPositionVlaue() == 9) {
                            childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true, toExpand.getPlayerNumber()));
                        } else {
                            childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false, toExpand.getPlayerNumber()));
                        }
                    }
                }
                moleIndex++;
            }
        }
        //if have to get out of hole
        if (childes.isEmpty()) {
            childes = Expansion.classicExpansion(toExpand, steps, lvl, depth);
        }
        toExpand.setExpanded();
        return childes;
    }

    //1) not out of hole allRoot
    public static ArrayList<GameState> smartExpansionAllRoot(GameState toExpand, int steps, Level lvl, int depth) {

        ArrayList<GameState> childes = new ArrayList<>();
//        if(toExpand.getDepth()%2 != 0 && toExpand.getDepth() != 0)
//        {
//            System.out.println("\nPlayer :"+toExpand.getPlayerOne().getPlayerNumber());
//            System.out.println("Cards: "+toExpand.getPlayerOne().getMoveCards().size());
//            System.out.println("Depth:"+toExpand.getDepth());
//        }
        //for root
        if (steps != 0) {
            for (int i = 0; i < toExpand.getPlayerOne().getMoles().size(); i++) {
                ArrayList<int[]> moves = lvl.returnValidMoves(toExpand.getPlayerOne().getMoles().get(i).getPosition(), steps, false, toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue());
                for (int[] move : moves) {
                    Level copyLevel = new Level(lvl);
                    SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                    SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                    copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                    copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                    //remove  move Card
                    copyPone.getMoveCards().remove(new Integer(steps));
                    //if zero refresh cards
                    if (copyPone.getMoveCards().size() == 0) {
                        copyPone.initMoveCards();
                    }
                    copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                    if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                        childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true, toExpand.getPlayerNumber()));
                    } else {
                        childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false, toExpand.getPlayerNumber()));
                    }
                }
            }
        } else {
            int moleIndex = 0;
            for (Mole m : toExpand.getPlayerOne().getMoles()) {
                ArrayList<Integer> used = new ArrayList<>();
                for (int stps : toExpand.getPlayerOne().getMoveCards()) {
//                    if (used.contains(stps)) {
//                        continue;
//                    }
//                    used.add(stps);
                    // exclude out of hole
                    if (m.getPositionVlaue() == 8) {
                        continue;
                    }

                    ArrayList<int[]> moves = lvl.returnValidMoves(m.getPosition(), stps, false, m.getPositionVlaue());
                    for (int[] move : moves) {
                        Level copyLevel = new Level(lvl);
                        SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                        SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                        copyLevel.resetValue(copyPone.getMoles().get(moleIndex).getPosition(), copyPone.getMoles().get(moleIndex).getPositionVlaue());
                        copyPone.getMoles().get(moleIndex).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                        //remove  move Card
                        copyPone.getMoveCards().remove(new Integer(stps));
                        //if zero refresh cards
                        if (copyPone.getMoveCards().size() == 0) {
                            copyPone.initMoveCards();
                        }
                        copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                        if (copyPone.getMoles().get(moleIndex).getPositionVlaue() == 9) {
                            childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true, toExpand.getPlayerNumber()));
                        } else {
                            childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false, toExpand.getPlayerNumber()));
                        }
                    }
                }
                moleIndex++;
            }
        }
        //if have to get out of hole
        if (childes.isEmpty()) {
            childes = Expansion.classicExpansion(toExpand, steps, lvl, depth);
        }
        toExpand.setExpanded();
        return childes;
    }

    //rnd smartRoot
    public static ArrayList<GameState> rndExpandOneSmartRoot(GameState toExpand, int steps, Level lvl, int depth) {
        ArrayList<GameState> childes = new ArrayList<>();
        //root
        if (steps != 0) {
            return smartExpansionSmartRoot(toExpand, steps, lvl, depth);
        }
        Random random = new Random();
        //start rand Mole and rnd Move
        ArrayList<GameState> possibleChildes = classicExpansion(toExpand, steps, lvl, depth);
        //if no moves possible
        if (possibleChildes.size() == 0) {
            toExpand.setExpanded();
            return childes;
        }
        int rand = random.nextInt(possibleChildes.size());
        GameState newChild = possibleChildes.get(rand);
        while (!possibleChildes.isEmpty()) {
            if (containsChild(newChild, toExpand)) {
                possibleChildes.remove(rand);
                rand = random.nextInt(possibleChildes.size());
                newChild = possibleChildes.get(rand);
                continue;
            }

            newChild.simulate();
            toExpand.getChildes().add(newChild);
            break;
        }
        //if last possible child was added -> set Node (toExpand) on expanded
        if (possibleChildes.isEmpty()) {
            toExpand.setExpanded();
        }

        return toExpand.getChildes();
    }
    //rnd allRoot
    public static ArrayList<GameState> rndExpandOneAllRootAllExpand(GameState toExpand, int steps, Level lvl, int depth) {
        ArrayList<GameState> childes = new ArrayList<>();
        //root
        if (steps != 0) {
            return classicExpansion(toExpand, steps, lvl, depth);
        }
        Random random = new Random();
        //start rand Mole and rnd Move
        ArrayList<GameState> possibleChildes = classicExpansion(toExpand, steps, lvl, depth);
        //if no moves possible
        if (possibleChildes.size() == 0) {
            toExpand.setExpanded();
            return childes;
        }
        int rand = random.nextInt(possibleChildes.size());
        GameState newChild = possibleChildes.get(rand);
        while (!possibleChildes.isEmpty()) {
            if (containsChild(newChild, toExpand)) {
                possibleChildes.remove(rand);
                rand = random.nextInt(possibleChildes.size());
                newChild = possibleChildes.get(rand);
                System.out.println("adadsasdsad");
                continue;
            }

            newChild.simulate();
            toExpand.getChildes().add(newChild);
            break;
        }
        //if last possible child was added -> set Node (toExpand) on expanded
        if (possibleChildes.isEmpty()) {
            toExpand.setExpanded();
        }
        return toExpand.getChildes();
    }
    //Deter
    public static  ArrayList<GameState> determinedAllRootAll(GameState toExpand, int steps, Level lvl, int depth){
        ArrayList<GameState> childes = new ArrayList<>();

        if (steps != 0) {
            for (int i = 0; i < toExpand.getPlayerOne().getMoles().size(); i++) {
                ArrayList<int[]> moves = lvl.returnValidMoves(toExpand.getPlayerOne().getMoles().get(i).getPosition(), steps, false, toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue());
                for (int[] move : moves) {
                    Level copyLevel = new Level(lvl);
                    SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                    SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                    copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                    copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                    //remove  move Card
                    copyPone.getMoveCards().remove(new Integer(steps));
                    //if zero refresh cards
                    if (copyPone.getMoveCards().size() == 0) {
                        copyPone.initMoveCards();
                    }
                    copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                    if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                        childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true, toExpand.getPlayerNumber()));
                    } else {
                        childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false, toExpand.getPlayerNumber()));
                    }
                }
            }
        }//Determin here
        else
        {
          int stps = toExpand.getPlayerOne().drawMoveCard();
            for (int i = 0; i < toExpand.getPlayerOne().getMoles().size(); i++) {
                ArrayList<int[]> moves = lvl.returnValidMoves(toExpand.getPlayerOne().getMoles().get(i).getPosition(), stps, false, toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue());
                for (int[] move : moves) {
                    Level copyLevel = new Level(lvl);
                    SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                    SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                    copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                    copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                    copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                    if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                        childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true, toExpand.getPlayerNumber()));
                    } else {
                        childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false, toExpand.getPlayerNumber()));
                    }
                }
            }
        }
        toExpand.setExpanded();
        return childes;
    }
    //TODO
    public static ArrayList<GameState> rndExpandrndOneSmartRoot(GameState toExpand, int steps, Level lvl, int depth) {
        ArrayList<GameState> childes = new ArrayList<>();
        //root
        if (steps != 0) {
            return classicExpansion(toExpand, steps, lvl, depth);
        }
        Random random = new Random();
        //start rand Mole and rnd Move
        ArrayList<GameState> possibleChildes = classicExpansion(toExpand, steps, lvl, depth);
        //if no moves possible
        if (possibleChildes.size() == 0) {
            toExpand.setExpanded();
            return childes;
        }
        int rand = random.nextInt(possibleChildes.size());
        GameState newChild = possibleChildes.get(rand);
        while (!possibleChildes.isEmpty()) {
            if (containsChild(newChild, toExpand)) {
                possibleChildes.remove(rand);
                rand = random.nextInt(possibleChildes.size());
                newChild = possibleChildes.get(rand);
                System.out.println("adadsasdsad");
                continue;
            }

            newChild.simulate();
            toExpand.getChildes().add(newChild);
            break;
        }
        //if last possible child was added -> set Node (toExpand) on expanded
        if (possibleChildes.isEmpty()) {
            toExpand.setExpanded();
        }
        return toExpand.getChildes();
    }
    //TODO
    public static ArrayList<GameState> rndExpandrndSmartAllRoot(GameState toExpand, int steps, Level lvl, int depth) {
        ArrayList<GameState> childes = new ArrayList<>();
        //root
        if (steps != 0) {
            return classicExpansion(toExpand, steps, lvl, depth);
        }
        Random random = new Random();
        //start rand Mole and rnd Move
        ArrayList<GameState> possibleChildes = classicExpansion(toExpand, steps, lvl, depth);
        //if no moves possible
        if (possibleChildes.size() == 0) {
            toExpand.setExpanded();
            return childes;
        }
        int rand = random.nextInt(possibleChildes.size());
        GameState newChild = possibleChildes.get(rand);
        while (!possibleChildes.isEmpty()) {
            if (containsChild(newChild, toExpand)) {
                possibleChildes.remove(rand);
                rand = random.nextInt(possibleChildes.size());
                newChild = possibleChildes.get(rand);
                System.out.println("adadsasdsad");
                continue;
            }

            newChild.simulate();
            toExpand.getChildes().add(newChild);
            break;
        }
        //if last possible child was added -> set Node (toExpand) on expanded
        if (possibleChildes.isEmpty()) {
            toExpand.setExpanded();
        }
        return toExpand.getChildes();
    }

    private static boolean containsChild(GameState newChild, GameState toExpand) {

        for (GameState child : toExpand.getChildes()) {
            if (java.util.Arrays.equals(child.getLvl().getField(), newChild.getLvl().getField())) {
                return true;
            }
        }
        return false;
    }
}
