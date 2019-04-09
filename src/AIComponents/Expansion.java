package AIComponents;

import gamecomponents.Level;
import gamecomponents.Mole;

import java.util.ArrayList;
import java.util.Random;

public class Expansion {

    public static ArrayList<GameState> classicExpansion(GameState toExpand, int steps, Level lvl,int depth)
    {
        ArrayList<GameState> childes  = new ArrayList<>();

        if (steps == 0) {
            int moleIndex = 0;
            for (Mole m : toExpand.getPlayerOne().getMoles()) {
                ArrayList<Integer> used = new ArrayList<>();
                for (int stps : toExpand.getPlayerOne().getMoveCards()) {
                    if(used.contains(stps))
                    {
                        continue;
                    }
                    used.add(stps);
                    ArrayList<int[]> moves = lvl.returnValidMoves(m.getPosition(), stps, false, m.getPositionVlaue());
                    for (int[] move : moves) {
                        Level copyLevel = new Level(lvl);
                        SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                        SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                        copyLevel.resetValue(copyPone.getMoles().get(moleIndex).getPosition(), copyPone.getMoles().get(moleIndex).getPositionVlaue());
                        copyPone.getMoles().get(moleIndex).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                        copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                        if (copyPone.getMoles().get(moleIndex).getPositionVlaue() == 9) {
                            childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true,toExpand.getPlayerNumber()));
                        } else {
                            childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0,false,toExpand.getPlayerNumber()));
                        }
                    }
                }
                moleIndex++;
            }
        }else{
            //for root
            for(int i=0;i<toExpand.getPlayerOne().getMoles().size();i++)
            {
                ArrayList<int[]> moves = lvl.returnValidMoves(toExpand.getPlayerOne().getMoles().get(i).getPosition(), steps, false, toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue());
                for (int[] move : moves) {
                    Level copyLevel = new Level(lvl);
                    SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                    SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                    copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                    copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                    copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                    if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                        childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0,true,toExpand.getPlayerNumber()));
                    } else {
                        childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0,false,toExpand.getPlayerNumber()));
                    }
                }
            }
        }
        toExpand.setExpanded();
        return childes;
    }

    //1) not out of hole
    public static ArrayList<GameState> smartExpansionOne(GameState toExpand, int steps, Level lvl, int depth)
    {

        ArrayList<GameState> childes = new ArrayList<>();

        //for root
        if(steps != 0){
        for(int i=0;i<toExpand.getPlayerOne().getMoles().size();i++)
        {
            //exclude moles in hole;
            if(toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue() == 8)
            {
                continue;
            }
            ArrayList<int[]> moves = lvl.returnValidMoves(toExpand.getPlayerOne().getMoles().get(i).getPosition(), steps, false, toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue());
            for (int[] move : moves) {
                Level copyLevel = new Level(lvl);
                SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                    childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, steps,true,toExpand.getPlayerNumber()));
                } else {
                    childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, steps,false,toExpand.getPlayerNumber()));
                }
            }
        }
    }else {
            int moleIndex = 0;
            for (Mole m : toExpand.getPlayerOne().getMoles()) {
                ArrayList<Integer> used = new ArrayList<>();
                for (int stps : toExpand.getPlayerOne().getMoveCards()) {
                    if (used.contains(stps)) {
                        continue;
                    }
                    used.add(stps);
                    //exclude out of hole
                    if(m.getPositionVlaue() == 8)
                    {
                        continue;
                    }
                    ArrayList<int[]> moves = lvl.returnValidMoves(m.getPosition(), stps, false, m.getPositionVlaue());
                    for (int[] move : moves) {
                        Level copyLevel = new Level(lvl);
                        SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                        SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                        copyLevel.resetValue(copyPone.getMoles().get(moleIndex).getPosition(), copyPone.getMoles().get(moleIndex).getPositionVlaue());
                        copyPone.getMoles().get(moleIndex).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                        copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                        if (copyPone.getMoles().get(moleIndex).getPositionVlaue() == 9) {
                            childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true,toExpand.getPlayerNumber()));
                        } else {
                            childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false,toExpand.getPlayerNumber()));
                        }
                    }
                }
                moleIndex++;
            }
        }
        //if have to get out of hole
        if(childes.isEmpty())
        {
            childes = Expansion.classicExpansion(toExpand, steps, lvl, depth);
        }
        toExpand.setExpanded();
        return childes;
    }

    public static ArrayList<GameState> rndExpandOneSmartRoot (GameState toExpand, int steps, Level lvl, int depth){
        ArrayList<GameState> childes = new ArrayList<>();
        //root
        if(steps!=0)
        {
            return smartExpansionOne(toExpand,steps,lvl,depth);
        }
        return childes;
    }
    //1) not out of hole
    public static ArrayList<GameState> smartExpansionOneALLRoot(GameState toExpand, int steps, Level lvl, int depth)
    {
        ArrayList<GameState> childes = new ArrayList<>();

        //for root
        if(steps != 0){
            for(int i=0;i<toExpand.getPlayerOne().getMoles().size();i++)
            {
                ArrayList<int[]> moves = lvl.returnValidMoves(toExpand.getPlayerOne().getMoles().get(i).getPosition(), steps, false, toExpand.getPlayerOne().getMoles().get(i).getPositionVlaue());
                for (int[] move : moves) {
                    Level copyLevel = new Level(lvl);
                    SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                    SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                    copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                    copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                    copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                    if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                        childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, steps,true,toExpand.getPlayerNumber()));
                    } else {
                        childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, steps,false,toExpand.getPlayerNumber()));
                    }
                }
            }
        }else {
            int moleIndex = 0;
            for (Mole m : toExpand.getPlayerOne().getMoles()) {
                ArrayList<Integer> used = new ArrayList<>();
                for (int stps : toExpand.getPlayerOne().getMoveCards()) {
                    if (used.contains(stps)) {
                        continue;
                    }
                    used.add(stps);
                    //exclude out of hole
                    if(m.getPositionVlaue() == 8)
                    {
                        continue;
                    }
                    ArrayList<int[]> moves = lvl.returnValidMoves(m.getPosition(), stps, false, m.getPositionVlaue());
                    for (int[] move : moves) {
                        Level copyLevel = new Level(lvl);
                        SimulatingPlayer copyPone = new SimulatingPlayer(toExpand.getPlayerOne());
                        SimulatingPlayer copyPtwo = new SimulatingPlayer(toExpand.getPlayerTwo());
                        copyLevel.resetValue(copyPone.getMoles().get(moleIndex).getPosition(), copyPone.getMoles().get(moleIndex).getPositionVlaue());
                        copyPone.getMoles().get(moleIndex).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                        copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                        if (copyPone.getMoles().get(moleIndex).getPositionVlaue() == 9) {
                            childes.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, toExpand, 0, true,toExpand.getPlayerNumber()));
                        } else {
                            childes.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, toExpand, 0, false,toExpand.getPlayerNumber()));
                        }
                    }
                }
                moleIndex++;
            }
        }
        //if have to get out of hole
        if(childes.isEmpty())
        {
            childes = Expansion.classicExpansion(toExpand, steps, lvl, depth);
        }
        return childes;
    }

    public static ArrayList<GameState> rndExpandOneAllRoot(GameState toExpand, int steps, Level lvl, int depth){
        ArrayList<GameState> childes = new ArrayList<>();
        //root
        if(steps!=0)
        {
            return smartExpansionOne(toExpand,steps,lvl,depth);
        }
        //start rand Mole and rnd Move
        Random random = new Random();
        Mole rndMole = toExpand.getPlayerOne().getMoles().get(random.nextInt(toExpand.getPlayerOne().getMoles().size()));


        return childes;
    }

    public static ArrayList<GameState> rndExpandrndSmartSmartRoot (GameState toExpand, int steps, Level lvl, int depth) {
        ArrayList<GameState> childes = new ArrayList<>();
        //root
        if (steps != 0) {
            return smartExpansionOne(toExpand, steps, lvl, depth);
        }

        return childes;
    }

    public static ArrayList<GameState> rndExpandrndSmartAllRoot(GameState toExpand, int steps, Level lvl, int depth) {
        ArrayList<GameState> childes = new ArrayList<>();
        //root
        if (steps != 0) {
            return smartExpansionOne(toExpand, steps, lvl, depth);
        }
        return childes;
    }

     private boolean containsChild (int[] move, Level level, int playerNumber){
        if(level.getField()[move[0]][move[1]] == playerNumber)
        {
            return true;
        }else {
            return false;
        }
     }
}
