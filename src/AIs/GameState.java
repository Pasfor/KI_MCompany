package AIs;

import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameState {
    private Level lvl;
    public int depth;
    private SimulatingPlayer Pone;
    private SimulatingPlayer Ptwo;
    ArrayList<GameState> childs = new ArrayList<>();
    private int[] winLoss;
    private GameState parent;
    private double ucb;
    private int steps;

    public GameState(SimulatingPlayer one, SimulatingPlayer two, Level lvl, int depth, GameState parent,int steps) {
        this.parent = parent;
        winLoss = new int[]{0, 0};
        this.Pone = one;
        this.Ptwo = two;
        this.lvl = lvl;
        this.depth = depth;
        this.steps = steps;
    }
    public Player getPlayerOne(){
        return this.Pone;
    }

    public Level getLvl()
    {
        return this.lvl;
    }
    public GameState getParent(){
        return this.parent;
    }

    public ArrayList<GameState> getChilds() {
        return this.childs;
    }

    public int[] getWinLoss() {
        return this.winLoss;
    }

    public void addWinLoss(int[] toAdd) {
        this.winLoss[0] = this.winLoss[0] + toAdd[0];
        this.winLoss[1]= this.winLoss[1] + toAdd[1];
    }

    public void propagate(int[] toProp)
    {
        addWinLoss(toProp);
        if(this.parent != null)
        {
            parent.propagate(toProp);
        }

        this.ucb = Heuristikcs.calcUCB(2,this);
    }

    public void expand() {
        if (this.steps == 0) {
            int moleIndex = 0;
            for (Mole m : Pone.getMoles()) {
                ArrayList<Integer> used = new ArrayList<>();
                for (int steps : Pone.getMoveCards()) {
                    if(used.contains(steps))
                    {
                        continue;
                    }
                    used.add(steps);
                    ArrayList<int[]> moves = this.lvl.returnValidMoves(m.getPosition(), steps, false, m.getPositionVlaue());
                    for (int[] move : moves) {
                        Level copyLevel = new Level(this.lvl);
                        SimulatingPlayer copyPtwo = new SimulatingPlayer(Ptwo);
                        SimulatingPlayer copyPone = new SimulatingPlayer(Pone);
                        copyLevel.resetValue(copyPone.getMoles().get(moleIndex).getPosition(), copyPone.getMoles().get(moleIndex).getPositionVlaue());
                        copyPone.getMoles().get(moleIndex).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                        copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                        if (copyPone.getMoles().get(moleIndex).getPositionVlaue() == 9) {
                            this.childs.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, this, 0));
                        } else {
                            this.childs.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, this, 0));
                        }
                    }
                }
                moleIndex++;
            }
        }else{
            //for root
            for(int i=0;i<Pone.getMoles().size();i++)
            {
                ArrayList<int[]> moves = this.lvl.returnValidMoves(Pone.getMoles().get(i).getPosition(), steps, false, Pone.getMoles().get(i).getPositionVlaue());
                for (int[] move : moves) {
                    Level copyLevel = new Level(this.lvl);
                    SimulatingPlayer copyPtwo = new SimulatingPlayer(Ptwo);
                    SimulatingPlayer copyPone = new SimulatingPlayer(Pone);
                    copyLevel.resetValue(copyPone.getMoles().get(i).getPosition(), copyPone.getMoles().get(i).getPositionVlaue());
                    copyPone.getMoles().get(i).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                    copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                    if (copyPone.getMoles().get(i).getPositionVlaue() == 9) {
                        this.childs.add(new GameState(copyPone, copyPtwo, copyLevel, depth + 1, this, 0));
                    } else {
                        this.childs.add(new GameState(copyPtwo, copyPone, copyLevel, depth + 1, this, 0));
                    }
                }
            }
        }
    }

    public void simulate() {
        int currentPlayerInt = 1;
        Level copyLevel = new Level(this.lvl);
        ArrayList<SimulatingPlayer> players = new ArrayList<>();
        players.add(new SimulatingPlayer(Pone));
        players.add(new SimulatingPlayer(Ptwo));

        while (!copyLevel.levelFinish()) {
            if (players.get(currentPlayerInt - 1).makeMove(copyLevel, false)) ;
            {   //special field hit
                players.get(currentPlayerInt - 1).makeMove(copyLevel, true);
            }
            //change player
            if (currentPlayerInt == 1) {
                currentPlayerInt = 2;
            } else {
                currentPlayerInt = 1;
            }
        }
        int value = Heuristikcs.calcHeuristic(players.get(0),players.get(1));
        if (value > 0) {
            propagate(new int[]{1,0});
        }
        if (value <= 0) {
            propagate(new int[]{0,1});
        }
    }

    public int getDepth() {
        return this.depth;
    }
}
