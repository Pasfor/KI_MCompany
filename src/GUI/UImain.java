package GUI;

import GameSession.GameSessionUI;
import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class UImain extends Application {
    public  ObservableList<Node> fields;
    public  Stage primaryStage;
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();

        //loading Game components to chan

        //Loading circles
        Pane gp = (Pane) pane.getChildren().get(0);
        this.fields = gp.getChildren();


        // Show the scene containing the root layout.
        loader.setLocation(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.show();

        this.controller = new Controller(pane);
        controller.initStage();
    }



    public static void main(String[] args) {
        launch(args);

    }
}
