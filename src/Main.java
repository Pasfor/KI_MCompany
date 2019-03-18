import AIs.RndAI;
import GameSession.GameSession;
import gamecomponents.Level;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<GameSession> gameSessions = new ArrayList<>();

        for(int i = 0; i<25091996;i++)
        {
            gameSessions.add(new GameSession(new RndAI(1), new RndAI(2)));
            gameSessions.get(i).run();
            System.out.println("index: " + i);

        }


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
