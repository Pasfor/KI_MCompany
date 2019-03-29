import AIs.McAI;
import AIs.RndAI;
import AIs.SimulatingPlayer;
import GUI.Controller;
import GameSession.*;
import gamecomponents.Level;
import gamecomponents.Player;
import sun.util.resources.cldr.vai.LocaleNames_vai_Latn;

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
            RndAI rnd = new RndAI(1);
            gameSessions.add(new GameSession(rnd, new McAI(2,rnd)));
            gameSessions.get(i).run();

        }
//        ArrayList<GameSession> gameSessions = new ArrayList<>();
//        for(int i = 0; i<10000;i++)
//        {
//            RndAI rnd = new RndAI(1);
//            gameSessions.add(new GameSession(rnd, new RndAI(2)));
//            gameSessions.get(i).run();
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
