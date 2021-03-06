package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.tools.SceneManager;
import network.ISPServer;

import java.util.HashMap;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        SceneManager.app = this;
        primaryStage.setTitle("Paratroopers");
        primaryStage.setScene(SceneManager.create("main.fxml", 600, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
