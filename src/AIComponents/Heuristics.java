package AIComponents;

import gamecomponents.Mole;
import gamecomponents.Player;

public class Heuristics {


    // diff between in hole player one and player two
    public static int calcHeuristic(Player one, Player two)
    {
        int ai = 0;
        int player = 0;
        for(Mole m: one.getMoles())
        {
            if(m.getPositionVlaue()==8)
            {
                if(one.getPlayerNumber() == 1)
                {
                    player++;
                }
                else{ai++;}
            }
        }
        for(Mole m: two.getMoles())
        {
            if(m.getPositionVlaue()==8)
            {
                if(two.getPlayerNumber() == 1)
                {
                    player++;
                }else{ai++;}
            }
        }
        return ai-player;
    }

    public static double calcUCB(double c, GameState gs)
    {
        int games = (gs.getWinLoss()[0]) + (gs.getWinLoss()[1]);
        double winRate;

        //gegenspieler der KI am Zug
        if(gs.getDepth()%2 == 0)
        {
            winRate =(double) (gs.getWinLoss()[1])/games;
        }
        //Ai am zug
        else{
            winRate =(double) (gs.getWinLoss()[0])/games;
        }

        if(gs.getParent() != null) {
            double lognk = Math.log(gs.getParent().getWinLoss()[0] + gs.getParent().getWinLoss()[1]);
            return winRate + c * Math.sqrt((lognk / games));
        }
        return 0;
    }
}
