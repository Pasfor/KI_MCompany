package AIComponents;

import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;

public class GameState {
    private Level lvl;
    public int depth;
    private SimulatingPlayer Pone;
    private SimulatingPlayer Ptwo;
    private ArrayList<GameState> childes = new ArrayList<>();
    private int[] winLoss;
    private GameState parent;
    private int steps;
    private boolean specialField;

    public GameState(SimulatingPlayer one, SimulatingPlayer two, Level lvl, int depth, GameState parent, int steps, boolean specialField) {
        this.parent = parent;
        winLoss = new int[]{0, 0};
        this.Pone = one;
        this.Ptwo = two;
        this.lvl = lvl;
        this.depth = depth;
        this.steps = steps;
        this.specialField = specialField;
    }
    public Player getPlayer(){
        if(this.specialField)
        {
            return this.Pone;
        }
        return this.Ptwo;
    }
    public Player getPlayerOne()
    {
        return this.Pone;
    }
    public Player getPlayerTwo()
    {
        return this.Ptwo;
    }
    public boolean getSpecialField()
    {
        return this.specialField;
    }
    public Level getLvl()
    {
        return this.lvl;
    }
    public GameState getParent(){
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
        this.winLoss[1]= (this.winLoss[1]) + (toAdd[1]);
    }

    public void propagate(int[] toProp)
    {
        addWinLoss(toProp);
        if(this.parent != null)
        {
            parent.propagate(toProp);
        }
    }

    public void expand() {
        this.childes = Expansion.smartExpansionOne(this,this.steps,this.lvl,this.depth);
    }

    public void simulate() {
        boolean specialF = this.specialField;
        int currentPlayerInt = 1;
        Level copyLevel = new Level(this.lvl);
        ArrayList<SimulatingPlayer> players = new ArrayList<>();
        players.add(new SimulatingPlayer(Pone));
        players.add(new SimulatingPlayer(Ptwo));

        while (!copyLevel.levelFinish()) {
            int inholeCounter = 0;
            for(Mole m : players.get(currentPlayerInt - 1).getMoles()){
                if(m.getPositionVlaue() == 8)
                {
                    inholeCounter++;
                }
                else
                {
                    break;
                }
            }
            if(inholeCounter == players.get(currentPlayerInt - 1).getMoles().size())
            {
                //change player
                if (currentPlayerInt == 1) {
                    currentPlayerInt = 2;
                } else {
                    currentPlayerInt = 1;
                }
            }

            if (players.get(currentPlayerInt - 1).makeMove(copyLevel, specialF))
            {   //special field hit
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
        int value = Heuristics.calcHeuristic(players.get(0),players.get(1));
        if (value >=0) {
            propagate(new int[]{1,0});
        }
        if (value < 0) {
            propagate(new int[]{0,1});
        }
    }

    public int getDepth() {
        return this.depth;
    }
}