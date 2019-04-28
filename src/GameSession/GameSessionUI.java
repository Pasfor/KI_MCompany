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
    private int[] fromTo = new int[]{100, 100};
    private int realPlayerMoveValue;

    public GameSessionUI(Player ai, Controller controller) {
        this.controller = controller;
        this.players = new ArrayList<>();
        this.players.add(new RealPlayer(1, controller, this));
        ai.setEnemy(this.players.get(0));
        this.players.add(ai);

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
            if (players.get(0).getAmountOfMoles() == 10 && players.get(1).getAmountOfMoles() == 10) {
                controller.changeCircleListener();
                drawRealPlayerCard();
                controller.changeCardLabel("" + this.realPlayerMoveValue);
                movePhaseOne();
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
            if (players.get(0).getAmountOfMoles() == 10 && players.get(1).getAmountOfMoles() == 10) {
                controller.changeCircleListener();
                drawRealPlayerCard();
                movePhaseOne();
                return;
            } else {
                players.get(currentPlayerInt - 1).setMole(this.lvls.get(0), 0);
                changePlayer();
                controller.changePlayerLabel("Your turn");
                controller.setPlayer(this.currentPlayerInt);
                controller.drawField(this.lvls.get(0).getField());
            }
        }

    }

    /**
     * decides after move-klick in which lvl the moving phase is
     **/
    public void movePhaseDecider() {
        if (!this.lvls.get(0).levelFinish()) {
            movePhaseOne();
            return;
        }
        if (!this.lvls.get(1).levelFinish()) {
            movePhaseTwo();
            return;
        }
        if (!this.lvls.get(2).levelFinish()) {
            movePhaseThree();
            return;
        }
        if (!this.lvls.get(3).levelFinish()) {
            movePhaseFour();
            return;
        }
    }

    /**
     * ======lvl-one========
     **/
    public void movePhaseOne() {
        controller.drawField(this.lvls.get(0).getField());
        if (this.lvls.get(0).levelFinish()) {
            players.get(0).initMolesToNewLvl(this.lvls.get(1));
            players.get(1).initMolesToNewLvl(this.lvls.get(1));
            if(players.get(0).getMoles().isEmpty())
            {
                controller.changePlayerLabel("Two wins!");
                return;
            }
            if(players.get(1).getMoles().isEmpty())
            {
                controller.changePlayerLabel("One Wins!");
                return;
            }
            movePhaseTwo();
            return;
        }

        //palyingphase
        if (currentPlayerInt == 1) {
            if (players.get(currentPlayerInt - 1).makeMove(this.lvls.get(0), false)) {
                changePlayer();
                controller.changePlayerLabel("AI´s turn");
                controller.setPlayer(this.currentPlayerInt);
                controller.setPlayerMoles(players.get(0).getMoles());
                drawRealPlayerCard();

                controller.drawField(this.lvls.get(0).getField());
                movePhaseOne();

            }
        } else {

            if (players.get(currentPlayerInt - 1).makeMove(this.lvls.get(0), false)) {
                //special field hit
                players.get(currentPlayerInt - 1).makeMove(this.lvls.get(0), true);
            }
            changePlayer();
            controller.changePlayerLabel("Your turn");
            controller.changeCardLabel("" + this.realPlayerMoveValue);
            controller.setPlayer(this.currentPlayerInt);



            controller.drawField(this.lvls.get(0).getField());
            movePhaseOne();
        }
    }

    /**
     * ======lvl-two========
     **/
    private void movePhaseTwo() {
        controller.drawField(this.lvls.get(1).getField());
        if (this.lvls.get(1).levelFinish()) {
            players.get(0).initMolesToNewLvl(this.lvls.get(2));
            players.get(1).initMolesToNewLvl(this.lvls.get(2));
            if(players.get(0).getMoles().isEmpty())
            {
                controller.changePlayerLabel("Two wins!");
                return;
            }
            if(players.get(1).getMoles().isEmpty())
            {
                controller.changePlayerLabel("One Wins!");
                return;
            }
            movePhaseThree();
            return;
        }

        //palyingphase
        if (currentPlayerInt == 1) {
            controller.changePlayerLabel("Your turn");
            controller.changeCardLabel("" + this.realPlayerMoveValue);
            if (players.get(currentPlayerInt - 1).makeMove(this.lvls.get(1), false)) {
                changePlayer();

                controller.setPlayer(this.currentPlayerInt);
                controller.setPlayerMoles(players.get(0).getMoles());
                drawRealPlayerCard();

                controller.drawField(this.lvls.get(1).getField());
                movePhaseTwo();

            }
        } else {
            controller.changePlayerLabel("AI´s turn");

            if (players.get(currentPlayerInt - 1).makeMove(this.lvls.get(1), false)) {
                //special field hit
                players.get(currentPlayerInt - 1).makeMove(this.lvls.get(1), true);
            }
            changePlayer();

            controller.changeCardLabel("" + this.realPlayerMoveValue);
            controller.setPlayer(this.currentPlayerInt);



            controller.drawField(this.lvls.get(1).getField());
            movePhaseTwo();
        }
    }

    /**
     * ======lvl-three========
     **/
    private void movePhaseThree() {
        controller.drawField(this.lvls.get(2).getField());
        if (this.lvls.get(2).levelFinish()) {
            players.get(0).initMolesToNewLvl(this.lvls.get(3));
            players.get(1).initMolesToNewLvl(this.lvls.get(3));
            if(players.get(0).getMoles().isEmpty())
            {
                controller.changePlayerLabel("Two wins!");
                return;
            }
            if(players.get(1).getMoles().isEmpty())
            {
                controller.changePlayerLabel("One Wins!");
                return;
            }

            movePhaseFour();
            return;
        }

        //palyingphase
        if (currentPlayerInt == 1) {
            controller.changePlayerLabel("Your turn");
            controller.changeCardLabel("" + this.realPlayerMoveValue);
            if (players.get(currentPlayerInt - 1).makeMove(this.lvls.get(2), false)) {
                changePlayer();

                controller.setPlayer(this.currentPlayerInt);
                controller.setPlayerMoles(players.get(0).getMoles());
                drawRealPlayerCard();


                controller.drawField(this.lvls.get(2).getField());
                movePhaseThree();

            }
        } else {
            controller.changePlayerLabel("AI´s turn");
            if (players.get(currentPlayerInt - 1).makeMove(this.lvls.get(2), false)) {
                //special field hit
                players.get(currentPlayerInt - 1).makeMove(this.lvls.get(2), true);
            }
            changePlayer();
            controller.setPlayer(this.currentPlayerInt);

            controller.drawField(this.lvls.get(2).getField());
            movePhaseThree();
        }
    }

    /**
     * ======lvl-four========
     **/
    private void movePhaseFour() {
        controller.drawField(this.lvls.get(3).getField());
        if (this.lvls.get(3).levelFinish()) {
           if(this.lvls.get(3).getField()[4][4] == 1)
           {
               controller.changePlayerLabel("One wins!");
           }
            if(this.lvls.get(3).getField()[4][4] == 2)
            {
                controller.changePlayerLabel("Two wins!");
            }
            return;
        }
        //palyingphase
        if (currentPlayerInt == 1) {
            controller.changePlayerLabel("Your turn");
            controller.changeCardLabel("" + this.realPlayerMoveValue);
            if (players.get(currentPlayerInt - 1).makeMove(this.lvls.get(3), false)) {
                changePlayer();

                controller.setPlayer(this.currentPlayerInt);
                controller.setPlayerMoles(players.get(0).getMoles());
                drawRealPlayerCard();
                controller.drawField(this.lvls.get(3).getField());
                movePhaseFour();

            }
        } else {
            controller.changePlayerLabel("AI´s turn");
            if (players.get(currentPlayerInt - 1).makeMove(this.lvls.get(3), false)) {
                //special field hit
                players.get(currentPlayerInt - 1).makeMove(this.lvls.get(3), true);
            }
            changePlayer();
            controller.setPlayer(this.currentPlayerInt);


            controller.drawField(this.lvls.get(3).getField());
            movePhaseFour();
        }
    }

    /**
     * end moving phase
     **/

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

    public void setFromTo(int[] fromTo) {
        this.fromTo = fromTo;
        ((RealPlayer) this.players.get(0)).setFromTo(fromTo);
    }

    public void drawRealPlayerCard() {
        this.realPlayerMoveValue = this.players.get(0).drawMoveCard();
        ((RealPlayer) this.players.get(0)).setMoveValue(this.realPlayerMoveValue);
    }
}
