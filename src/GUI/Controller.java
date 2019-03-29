package GUI;


import AIs.McAI;
import AIs.RndAI;
import GameSession.GameSessionUI;
import gamecomponents.Mole;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import GameSession.RealPlayer;

import java.util.ArrayList;

public class Controller {
    FXMLLoader loader = null;
    Stage secondaryStage = new Stage();
    AnchorPane rootLayout;

    ArrayList<Circle> circles = new ArrayList<>();
    ArrayList<Label> labels = new ArrayList<>();
    ArrayList<Button> buttons = new ArrayList<>();

    GameSessionUI gameSession;
    //for moves
    private int clickCounter = 0;
    private int[] fromTo;

    private Color playerColor;
    private int firstCircleIndex = 100;
    private Color colorOne = Color.CRIMSON;
    private ArrayList<Mole> playerMoles;

    public Controller(AnchorPane pane) {
        rootLayout = pane;
    }

    public void initStage() {
        //get Circles
        for (Node n : ((Pane) (rootLayout.getChildren().get(0))).getChildren()) {
            try {
                Circle circle = (Circle) n;
                circle.setFill(Color.BLUE);
                circles.add(circle);
            } catch (Exception e) {
            }
        }
        //get Label
        for (Node n : ((Pane) (rootLayout.getChildren().get(0))).getChildren()) {
            try {
                labels.add((Label) n);
            } catch (Exception e) {
            }
        }
        //get Button
        for (Node n : ((Pane) (rootLayout.getChildren().get(0))).getChildren()) {
            try {
                buttons.add((Button) n);
            } catch (Exception e) {
            }
        }
        addButtonListener();
        rootLayout.getChildren().removeAll();
    }

    //add SetMole actionListener to circles
    private void addActionListener() {
        fromTo = new int[]{100, 100};
        for (Circle c : circles) {
            c.setOnMouseClicked(e -> {
                Circle circle = (Circle) e.getSource();
                gameSession.setRealPlayerMove(circles.indexOf(circle));
                gameSession.setPhase();
            });
        }
    }

    //add button actionListener
    private void addButtonListener() {
        for (Button b : buttons) {
            b.setOnAction(e -> {
                switch (b.getId()) {
                    case "Weak":
                        this.gameSession = new GameSessionUI( new RndAI(2), this);
                        gameSession.setPhase();
                        disableButtons();
                        addActionListener();
                        break;

                    case"Strong" :
                        this.gameSession = new GameSessionUI( new McAI(2,null), this);
                        gameSession.setPhase();
                        disableButtons();
                        addActionListener();
                        break;

                    case "next":
                        this.gameSession.changePlayer();
                        this.gameSession.movePhaseDecider();
                }
            });
        }
    }

    private void disableButtons() {
        for (Button b : buttons) {
            if(b.getId().equals("next"))
            {
                continue;
            }
            b.setDisable(true);
        }
    }

    //for play phase
    public void changeCircleListener() {
        for (Circle c : circles) {
            c.setOnMouseClicked(e -> {
                Circle circle = (Circle) e.getSource();
                labels.get(2).setText("Field: "+ circles.indexOf(circle));
                if (clickCounter == 1) {
                    fromTo[1] = circles.indexOf(circle);
                    gameSession.setFromTo(fromTo);
                    gameSession.movePhaseDecider();
                    //reset values
                    clickCounter = 0;
                    labels.get(2).setText("");
                    fromTo[0] = 100;
                    fromTo[1] = 100;
                    return;
                }
                colorOne = (Color) circle.getFill();
                firstCircleIndex = circles.indexOf(circle);
                fromTo[0] = circles.indexOf(circle);
                clickCounter++;
            });
        }
    }

    public void setPlayer(int playerNumber) {
        if (playerNumber == 1) {
            this.playerColor = Color.BLUE;
        } else {
            this.playerColor = Color.RED;
        }
    }

    public void changeCardLabel(String s) {
        this.labels.get(1).setText(s);
    }

    public void changePlayerLabel(String s) {
        this.labels.get(0).setText(s);
    }

    public void setPlayerMoles(ArrayList<Mole> moles) {
        this.playerMoles = moles;
    }

    public void drawField(int[][] field) {
        int circleIndex = 0;
        for (int[] row : field) {
            for (int value : row) {
                switch (value) {
                    case 0:
                        this.circles.get(circleIndex).setFill(Color.GREY);
                        break;
                    case 1:
                        this.circles.get(circleIndex).setFill(Color.CYAN);
                        break;
                    case 2:
                        this.circles.get(circleIndex).setFill(Color.RED);
                        break;
                    case 6:
                        this.circles.get(circleIndex).setFill(Color.ORANGE);
                        break;
                    case 8:
                        this.circles.get(circleIndex).setFill(Color.BLACK);
                        break;
                    case 9:
                        this.circles.get(circleIndex).setFill(Color.WHITE);
                        break;
                }
                circleIndex++;
            }
        }
        //proof if mole in hole
        for (Mole m : this.playerMoles) {
            if (m.getPositionVlaue() == 8) {
                int rowCounter = m.getPosition()[0];
                int colCounter = m.getPosition()[1];
                int index = 0;
                for (int[] row : field) {
                    for (int value : row) {
                        //if col index is already 0 -> to remove -8 bug
                        if(m.getPosition()[1] == 0)
                        {
                            if(rowCounter == 0)
                            {
                                break;
                            }
                            index++;
                        }else {
                            index++;
                            if (rowCounter == 0) {
                                colCounter--;
                                if (colCounter == 0) {
                                    break;
                                }
                            }
                        }

                    }
                    if (rowCounter == 0) {
                        break;
                    }
                    rowCounter--;
                }
                circles.get(index).setFill(Color.DARKBLUE);
            }
        }
    }
}

