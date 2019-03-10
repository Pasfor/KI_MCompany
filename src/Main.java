import AIs.RndAI;
import GameSession.GameSession;

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
//        one.setMole(5,4,1);
//
//        one.setMole(6,1,2);
//
//        one.setMole(0,3,2);
//        one.setMole(4,5,2);
//        one.setMole(2,2,2);
//
//        one.printLVL();
//
//        one.returnValidMoves(new int[]{5,4},2);

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
