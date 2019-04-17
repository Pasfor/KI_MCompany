package AIComponents;

import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;

public class GameState {
    private int playerNumber;
    private Level lvl;
    public int depth;
    private SimulatingPlayer Pone;
    private SimulatingPlayer Ptwo;
    public ArrayList<GameState> childes = new ArrayList<>();
    private int[] winLoss;
    private GameState parent;
    public int steps;
    private boolean specialField;
    private boolean expanded;
    private double choosenValue;
    private int[] moleSum;

    public GameState(SimulatingPlayer one, SimulatingPlayer two, Level lvl, int depth, GameState parent, int steps, boolean specialField, int playerNumber) {
        this.parent = parent;
        winLoss = new int[]{0, 0};
        moleSum = new int[]{0,0};
        this.Pone = one;
        this.Ptwo = two;
        this.lvl = lvl;
        this.depth = depth;
        this.steps = steps;
        this.specialField = specialField;
        this.playerNumber = playerNumber;
        this.expanded = false;
        this.choosenValue = -1.0;
    }
    public double getChoosenValue()
    {
     return choosenValue;
    }
    public void setChoosemValue(double v)
    {
     this.choosenValue =v;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public void setExpanded() {
        this.expanded = true;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public Player getPlayer() {
        if (this.specialField) {
            return this.Pone;
        }
        return this.Ptwo;
    }

    public int[] getMoleSum()
    {
        return this.moleSum;
    }
    public Player getPlayerOne() {
        return this.Pone;
    }

    public Player getPlayerTwo() {
        return this.Ptwo;
    }

    public boolean getSpecialField() {
        return this.specialField;
    }

    public Level getLvl() {
        return this.lvl;
    }

    public GameState getParent() {
        return this.parent;
    }

    public ArrayList<GameState> getChildes() {
        return this.childes;
    }

    public int[] getWinLoss() {
        return this.winLoss;
    }

    public void addWinLoss(int[] toAdd) {
        this.winLoss[0] = (this.winLoss[0]) + (toAdd[0]);
        this.winLoss[1] = (this.winLoss[1]) + (toAdd[1]);
    }

    private void propagate(int[] toProp) {
        addWinLoss(toProp);
        if (this.parent != null) {
            parent.propagate(toProp);
        }
    }
    private void addMoles(int[] toAdd)
    {
        this.moleSum[0] = (this.moleSum[0])+ (toAdd[0]);
        this.moleSum[1] = (this.moleSum[1])+ (toAdd[1]);
    }
    private void propagateAV(int[] toProp) {
        addMoles(toProp);
        if (this.parent != null) {
            parent.propagateAV(toProp);
        }
    }
    public void simulate() {
        if(lvl.levelFinish())
        {
            int[] moles= Heuristics.calcHeuristicAv(getPlayerOne(), getPlayerTwo(), this.playerNumber);
            int  value = moles[0] - moles[1];
            if (value >= 0) {
                propagate(new int[]{1, 0});
            }
            if (value < 0) {
                propagate(new int[]{0, 1});
            }
        }
        boolean specialF = this.specialField;
        int currentPlayerInt = 1;
        Level copyLevel = new Level(this.lvl);
        ArrayList<SimulatingPlayer> players = new ArrayList<>();
        players.add(new SimulatingPlayer(Pone));
        players.add(new SimulatingPlayer(Ptwo));
//        ArrayList<Level> lvlToPlay = new ArrayList<>();
//        for(int i=copyLevel.getValue();i<4;i++)
//        {
//            lvlToPlay.add(new Level(i+1));
//        }
//        System.out.println("LVLVLLVLLLvVLLVLVLVLLVLVLVLVLLVLLVLV: "+lvlToPlay.size());
        while (!copyLevel.levelFinish()) {
            int inholeCounter = 0;
            for (Mole m : players.get(currentPlayerInt - 1).getMoles()) {
                if (m.getPositionVlaue() == 8) {
                    inholeCounter++;
                } else {
                    break;
                }
            }
            if (inholeCounter == players.get(currentPlayerInt - 1).getMoles().size()) {
                //change player
                if (currentPlayerInt == 1) {
                    currentPlayerInt = 2;
                } else {
                    currentPlayerInt = 1;
                }
            }

            if (players.get(currentPlayerInt - 1).makeMove(copyLevel, specialF)) {   //special field hit
                players.get(currentPlayerInt - 1).makeMove(copyLevel, true);
            }
            specialF = false;
            //change player
            if (currentPlayerInt == 1) {
                currentPlayerInt = 2;
            } else {
                currentPlayerInt = 1;
            }
        }
        int[] moles= Heuristics.calcHeuristicAv(getPlayerOne(), getPlayerTwo(), this.playerNumber);
        int  value = moles[0] - moles[1];
        if (value >= 0) {
            propagate(new int[]{1, 0});
        }
        if (value < 0) {
            propagate(new int[]{0, 1});
        }
        propagateAV(moles);
    }

    public int getDepth() {
        return this.depth;
    }
}
