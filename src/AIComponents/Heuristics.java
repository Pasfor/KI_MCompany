package AIComponents;

import GameSession.GameSession;
import gamecomponents.Mole;
import gamecomponents.Player;

public class Heuristics {

    public static int calcHeuristicWin(Player one, Player two,int playerNumber)
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
    public static int[] calcHeuristicAv(Player one, Player two, int playerNumber)
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
        return new int[]{ai,enemy};
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
            return winRate + c * Math.sqrt((lognk / games)) + ((int)(Math.random()*10))*0.0000000000000001+((0.001*(double)Heuristics.calcHeuristicWin(gs.getPlayerOne(),gs.getPlayerTwo(),gs.getPlayerOne().getPlayerNumber())));
        }
        return 0;
    }

    public static double calcUCBAvg(double c, GameState gs) {
        int games = (gs.getWinLoss()[0]) + (gs.getWinLoss()[1]);
        double winRate;

        //gegenspieler der KI war Zug
        if (gs.getDepth() % 2 == 0) {
            winRate = (double) (gs.getMoleSum()[1]) / (games*gs.getPlayerOne().getMoles().size());
        }
        //Ai am zug
        else {
            winRate = (double) (gs.getMoleSum()[0]) / (games*gs.getPlayerOne().getMoles().size());
        }

        if (gs.getParent() != null) {
            double lognk = Math.log(gs.getParent().getWinLoss()[0] + gs.getParent().getWinLoss()[1]);
            return winRate + c * Math.sqrt((lognk / games)) + ((int) (Math.random() * 10)) * 0.0000000000000001 + ((0.001 * (double) Heuristics.calcHeuristicWin(gs.getPlayerOne(), gs.getPlayerTwo(), gs.getPlayerOne().getPlayerNumber())));
        }
        return 0;
    }
}
