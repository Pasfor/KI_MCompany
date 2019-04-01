package AIComponents;

import gamecomponents.Mole;
import gamecomponents.Player;

public class Heuristics {

    public static int calcHeuristicAsTwo(Player one, Player two,int playerNumber)
    {
        int ai = 0;
        int enemy = 0;
        for(Mole m: one.getMoles())
        {
            if(m.getPositionVlaue()==8)
            {
                if(one.getPlayerNumber() != playerNumber)
                {
                    enemy++;
                }
                else{ai++;}
            }
        }
        for(Mole m: two.getMoles())
        {
            if(m.getPositionVlaue()==8)
            {
                if(two.getPlayerNumber() != playerNumber)
                {
                    enemy++;
                }else{ai++;}
            }
        }
        return ai-enemy;
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
