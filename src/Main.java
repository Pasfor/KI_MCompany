import AIs.McAI;
import AIs.RndAI;
import GameSession.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        //RealPlayer p = new RealPlayer(1,null,null);
//        for(int i = 0;i<10;i++) {
//            for (int j = 0; j < 6; j++) {
//                System.out.println(p.drawMoveCard());
//            }
//            System.out.println("======================================");
//        }
        ArrayList<GameSession> gameSessions = new ArrayList<>();

        for(int i = 0; i<1;i++)
        {
            RndAI rndAI = new RndAI(1);
            McAI mcAitwo = new McAI(2,rndAI);

            gameSessions.add(new GameSession(rndAI, mcAitwo));
            gameSessions.get(i).run();
        }
//        ArrayList<GameSession> gameSessions = new ArrayList<>();
//
//        for(int i = 0; i<1000;i++)
//        {
//            RndAI rnd = new RndAI(1);
//            gameSessions.add(new GameSession(rnd, new RndAI(2)));
//            gameSessions.get(i).run();
//
//        }

//        Level one = new Level(1);
//        one.printLVL();
//
//        one.setMole(6,2,1);
//        one.printLVL();
//        ArrayList<int[]> validMoves = one.returnValidMoves(new int[]{6,2},1,false);
//
//        System.out.println(validMoves.size());
//        for(int[] move : validMoves)
//        {
//            System.out.println(move[0]+", "+move[1]);
//        }

//
//        one.printLVL();
//


        // Level LVLtwo = new Level(2);
//        LVLtwo.printLVL();
//
//        Level LVLthree = new Level(3);
//        LVLthree.printLVL();
//
//        Level LVLfour = new Level(4);
//        LVLfour.printLVL();



        //test ui

        //startUI

//        Application.launch(UImain.class,args);
//        UImain.changeFieldColor(0,0, Color.BLUE);

    }
}
