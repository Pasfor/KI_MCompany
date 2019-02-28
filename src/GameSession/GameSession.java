package GameSession;

import gamecomponents.Level;

import java.util.ArrayList;

public class GameSession extends Thread {

    private ArrayList<Player> players;
    private ArrayList<Level> lvls;

    int currentPlayerInt;

    public GameSession(Player Pone, Player Ptwo, Level one, Level two, Level three, Level four) {
        this.players.add(Pone);
        this.players.add(Ptwo);

        //lvls
        this.lvls = new ArrayList<>();
        this.lvls.add(one);
        this.lvls.add(two);
        this.lvls.add(three);
        this.lvls.add(four);

        currentPlayerInt = ((int) (Math.random() * ((2 - 1) + 1)) + 1) - 1;
    }


    public void run() {
        while (players.get(currentPlayerInt).hasMolesToSet()) {

            //players.get(currentPlayerInt).setMole();

            //change player
            if(currentPlayerInt == 1)
            {
                currentPlayerInt = 2;
            }else{
                currentPlayerInt = 1;
            }
        }
    }
}
