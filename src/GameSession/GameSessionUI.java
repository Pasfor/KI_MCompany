package GameSession;

import GUI.Controller;
import gamecomponents.Level;
import gamecomponents.Player;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class GameSessionUI {

    private final ArrayList<Level> lvls;
    private ArrayList<Player> players;
    private Controller controller;
    private int currentPlayerInt;
    private int realPlayerMove = 1000; // default value zero cause error
    private int lvl;
    private int[] fromTo = new int[]{100,100};
    private int realPlayerMoveValue;

    public GameSessionUI(Player pone, Player ptwo, Controller controller) {
        this.controller = controller;
        this.players = new ArrayList<>();
        this.players.add(pone);
        this.players.add(ptwo);

        //create lvls
        this.lvls = new ArrayList<>();
        this.lvls.add(new Level(1));
        this.lvls.add(new Level(2));
        this.lvls.add(new Level(3));
        this.lvls.add(new Level(4));
        currentPlayerInt = ((int) (Math.random() * ((2 - 1) + 1)) + 1);
    }

    //for the set phase
    public void setPhase() {
        controller.setPlayerMoles(players.get(0).getMoles());
        controller.drawField(this.lvls.get(0).getField());

        if (currentPlayerInt == 1) {
            if (players.get(0).getAmountOfMoles() == 5 && players.get(1).getAmountOfMoles() == 5) {
                controller.changeCircleListener();
                drawRealPlayerCard();
                controller.changeCardLabel(""+this.realPlayerMoveValue);
                movePhase();
                return;
            }
            if (players.get(currentPlayerInt - 1).setMole(this.lvls.get(0), this.realPlayerMove)) {
                changePlayer();
                controller.setPlayer(this.currentPlayerInt);
                controller.drawField(this.lvls.get(0).getField());
                controller.drawField(this.lvls.get(0).getField());
                controller.changePlayerLabel("AI's turn");
                setPhase();

            }
        } else {                                                  //dummi value for AI
            if (players.get(0).getAmountOfMoles() == 5 && players.get(1).getAmountOfMoles() == 5) {
                controller.changeCircleListener();
                drawRealPlayerCard();
                movePhase();
                return;
            }else {
                players.get(currentPlayerInt - 1).setMole(this.lvls.get(0), 0);
                changePlayer();
                controller.changePlayerLabel("Your turn");
                controller.setPlayer(this.currentPlayerInt);
                controller.drawField(this.lvls.get(0).getField());
            }
        }

    }

    public void movePhase()
    {
      if(currentPlayerInt == 1)
      {
         if(players.get(currentPlayerInt-1).makeMove(this.lvls.get(0),false))
         {
             changePlayer();
             controller.changePlayerLabel("AI´s turn");
             controller.setPlayer(this.currentPlayerInt);
             controller.setPlayerMoles(players.get(0).getMoles());
             drawRealPlayerCard();

             System.out.println("you");
             this.lvls.get(0).printLVL();

             controller.drawField(this.lvls.get(0).getField());
             movePhase();

         }
      }else
      {
          System.out.println("try to find move");
          if(players.get(currentPlayerInt-1).makeMove(this.lvls.get(0),false))
          {
              //special field hit
              players.get(currentPlayerInt-1).makeMove(this.lvls.get(0),true);
          }
          changePlayer();
          controller.changePlayerLabel("Your turn");
          controller.changeCardLabel(""+this.realPlayerMoveValue);
          controller.setPlayer(this.currentPlayerInt);

          System.out.println("AI");
          this.lvls.get(0).printLVL();

          controller.drawField(this.lvls.get(0).getField());
          movePhase();
      }
    }

    public void changePlayer() {
        if (currentPlayerInt == 1) {
            currentPlayerInt = 2;
        } else {
            currentPlayerInt = 1;
        }
    }

    public void setRealPlayerMove(int move) {
        this.realPlayerMove = move;
    }

    public void setFromTo(int[] fromTo){
        this.fromTo = fromTo;
        ((RealPlayer) this.players.get(0)).setFromTo(fromTo);
    }
    public void drawRealPlayerCard()
    {
        this.realPlayerMoveValue = this.players.get(0).drawMoveCard();
        ((RealPlayer) this.players.get(0)).setMoveValue(this.realPlayerMoveValue);
    }
}
