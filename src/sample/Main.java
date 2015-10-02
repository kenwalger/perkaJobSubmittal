package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.out.println("javafx.runtime.version: " + System.getProperties().get("javafx.runtime.version"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Perka Job Application Submission - by Ken W. Alger");
        primaryStage.setScene(new Scene(root, 750, 675)); // width, height
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
