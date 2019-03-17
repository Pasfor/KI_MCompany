package GameSession;

import gamecomponents.Level;
import gamecomponents.Player;

import java.util.ArrayList;

public class GameSession extends Thread {

    private ArrayList<Player> players;
    private ArrayList<Level> lvls;
    private int currentPlayerInt;

    public GameSession(Player Pone, Player Ptwo) {
        this.players = new ArrayList<>();
        this.players.add(Pone);
        this.players.add(Ptwo);

        //create lvls
        this.lvls = new ArrayList<>();
        this.lvls.add(new Level(1));
        this.lvls.add(new Level(2));
        this.lvls.add(new Level(3));
        this.lvls.add(new Level(4));
        currentPlayerInt = ((int) (Math.random() * ((2 - 1) + 1)) + 1);
    }


    public void run() {
        //setting Moles

        for (int i = 0; i < 20; i++) {

            players.get(currentPlayerInt-1).setMole(this.lvls.get(0),0);
            //change player
            if (currentPlayerInt == 1) {
                currentPlayerInt = 2;
            } else {
                currentPlayerInt = 1;
            }
        }
        System.out.println("=====start=Game======");
        //run first lvl
        while(!this.lvls.get(0).levelFinish())
        {
            if(players.get(currentPlayerInt-1).makeMove(this.lvls.get(0),false))
            {
                //special field hit
                players.get(currentPlayerInt-1).makeMove(this.lvls.get(0),true);
            }
            //change player
            if (currentPlayerInt == 1) {
                currentPlayerInt = 2;
            } else {
                currentPlayerInt = 1;
            }
        }
        System.out.println("========Level===ONE===Finish=======");
        //run second lvl
        //init Moles
        players.get(0).initMolesToNewLvl(this.lvls.get(1));
        players.get(1).initMolesToNewLvl(this.lvls.get(1));

        while(!this.lvls.get(1).levelFinish())
        {
            if(players.get(currentPlayerInt-1).makeMove(this.lvls.get(1),false))
            {
                //special field hit
                players.get(currentPlayerInt-1).makeMove(this.lvls.get(1),true);
            }
            //change player
            if (currentPlayerInt == 1) {
                currentPlayerInt = 2;
            } else {
                currentPlayerInt = 1;
            }
        }
        System.out.println("========Level===TWO===Finish=======");

        players.get(0).initMolesToNewLvl(this.lvls.get(2));
        players.get(1).initMolesToNewLvl(this.lvls.get(2));

        while(!this.lvls.get(2).levelFinish())
        {
            if(players.get(currentPlayerInt-1).makeMove(this.lvls.get(2),false))
            {
                //special field hit
                players.get(currentPlayerInt-1).makeMove(this.lvls.get(2),true);
            }
            //change player
            if (currentPlayerInt == 1) {
                currentPlayerInt = 2;
            } else {
                currentPlayerInt = 1;
            }
        }
        System.out.println("========Level===THREE===Finish=======");

        players.get(0).initMolesToNewLvl(this.lvls.get(3));
        players.get(1).initMolesToNewLvl(this.lvls.get(3));

        while(!this.lvls.get(3).levelFinish())
        {
            if(players.get(currentPlayerInt-1).makeMove(this.lvls.get(3),false))
            {
                //special field hit
                players.get(currentPlayerInt-1).makeMove(this.lvls.get(3),true);
            }
            //change player
            if (currentPlayerInt == 1) {
                currentPlayerInt = 2;
            } else {
                currentPlayerInt = 1;
            }
        }
        this.lvls.get(3).printLVL();
        System.out.println("========Level===FOUR===Finish=======");
    }
}
