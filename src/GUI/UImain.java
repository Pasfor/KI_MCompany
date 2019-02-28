package GUI;

import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.application.Application;
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
    public static ObservableList<Node> fields;

    @Override
    public void start(Stage primaryStage) throws Exception {
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
    }

    public static void changeFieldColor(int row, int col, Color color) {
        Circle c = (Circle) fields.get(0);
        c.setFill(javafx.scene.paint.Color.RED);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
