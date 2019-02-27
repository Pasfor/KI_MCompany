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

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        AnchorPane pane =(AnchorPane) loader.load();
        Pane gp = (Pane) pane.getChildren().get(0);

        ObservableList<Node> c = gp.getChildren();

        Node node = c.get(0);
        Circle cir = (Circle) node;

        cir.setFill(javafx.scene.paint.Color.RED);



        loader.setLocation(getClass().getResource("sample.fxml"));


        // Show the scene containing the root layout.
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[]args){launch(args);}
}
