package AIs;

import gamecomponents.Level;
import gamecomponents.Mole;
import gamecomponents.Player;

import java.util.ArrayList;

public class GameState {
   private Level lvl;
   public int depth ;
   private SimulatingPlayer Pone;
  private SimulatingPlayer Ptwo;
   ArrayList<GameState> childs = new ArrayList<>();
   private int won,loss;
    public GameState(SimulatingPlayer one, SimulatingPlayer two, Level lvl, int depth)
    {
        this.won = 0;
        this.loss = 0;
        this.Pone = one;
        this.Ptwo = two;
        this.lvl = lvl;
        this.depth = depth;
    }

    public ArrayList<GameState> getChilds()
    {
        return this.childs;
    }
    public int[] getWinLoss()
    {
        return new int[]{this.won,this.loss};
    }

    public void expand()
    {
        int moleIndex =0 ;
       for(Mole m: Pone.getMoles())
      {
          for(int steps : Pone.getMoveCards())
          {
             ArrayList<int[]> moves = this.lvl.returnValidMoves(m.getPosition(),steps,false,m.getPositionVlaue());
             for(int[] move : moves)
             {
                 Level copyLevel = new Level(this.lvl);
                 SimulatingPlayer copyPtwo  = new SimulatingPlayer(Ptwo);
                 SimulatingPlayer copyPone = new SimulatingPlayer(Pone);
                 copyLevel.resetValue(copyPone.getMoles().get(moleIndex).getPosition(), copyPone.getMoles().get(moleIndex).getPositionVlaue());
                 copyPone.getMoles().get(moleIndex).setPosition(move, copyLevel.getField()[move[0]][move[1]]);
                 copyLevel.setMole(move[0], move[1], copyPone.getPlayerNumber());

                 if (copyPone.getMoles().get(moleIndex).getPositionVlaue() == 9) {
                     this.childs.add(new GameState(copyPone,copyPtwo,copyLevel,depth-1));
                 }
                 else {
                     this.childs.add(new GameState(copyPtwo,copyPone,copyLevel,depth-1));
                 }
             }
          }
          moleIndex ++;
      }
    }
    public void simulate()
    {
        int currentPlayerInt = 1;
        Level copyLevel = new Level(this.lvl);
        ArrayList<SimulatingPlayer> players = new ArrayList<>();
        players.add(new SimulatingPlayer(Pone));
        players.add(new SimulatingPlayer(Ptwo));

       while(!copyLevel.levelFinish())
       {
           System.out.println(currentPlayerInt);
           if(players.get(currentPlayerInt-1).makeMove(copyLevel,false));
           {   //special field hit
               players.get(currentPlayerInt-1).makeMove(copyLevel,true);
           }
           //change player
           if (currentPlayerInt == 1) {
               currentPlayerInt = 2;
           } else {
               currentPlayerInt = 1;
           }
       }
       int value = Heuristikcs.calcHeuristikone(copyLevel);
       if(value>0)
       {
           this.won++;
       }
       if(value<0)
       {
           loss++;
       }
    }

}
