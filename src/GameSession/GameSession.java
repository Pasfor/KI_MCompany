package GameSession;

import AIComponents.Heuristics;
import gamecomponents.Level;
import gamecomponents.Player;
import output.Output;

import java.util.ArrayList;

public class GameSession  {

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
       // System.out.println("=====start=Game======");
        //run first lvl
        while(!this.lvls.get(0).levelFinish())
        {
            if(!proofMoleLeft())
            {
                System.exit(123123);
                returnWinner();
                break;
            }
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

//        lvls.get(1).printLVL();
//        Output.getInstance().writeToFile(" player 2 - player 1 : "+(players.get(0).getMoles().size()-players.get(1).getMoles().size()) );


        //======!!!!!!!!! dont forget the"!" if playthrough!!!===========
        while(!this.lvls.get(1).levelFinish())
        {
            if(!proofMoleLeft())
            {
                returnWinner();
                return;
            }
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
            if(!proofMoleLeft())
            {
                returnWinner();
                return;
            }
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

            if(!proofMoleLeft())
            {
                returnWinner();
                return;
            }
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
        if(this.lvls.get(3).getField()[4][4]==1)
        {
            Output.getInstance().writeToFile("player one wins");
        }
        if(this.lvls.get(3).getField()[4][4]==2)
        {
           Output.getInstance().writeToFile("player two wins");
        }
        System.out.println("========Level===FOUR===Finish=======");
        lvls.get(3).printLVL();
    }

    private void returnWinner() {
        if(players.get(0).getMoles().isEmpty())
        {
            Output.getInstance().writeToFile("player two wins");
        }
        if(players.get(1).getMoles().isEmpty())
        {
            Output.getInstance().writeToFile("player one wins");
        }
    }

    public boolean proofMoleLeft()
    {
        if(players.get(0).getMoles().isEmpty() || players.get(0).getMoles().isEmpty())
        {
            System.out.println("no Moles left");
            return false;
        }
        return true;
    }
}
