package main;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.tools.BaseController;
import main.tools.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller extends BaseController{
    @FXML
    private Button create_hotspot;
    @FXML
    private Button wifi_connected;
    @FXML
    private TextArea console;

    MainModel mm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).
        mm = new MainModel();
    }

    public void createHotspot(ActionEvent actionEvent){
        // create hotspot...
        println(console,"creating hotspot...");
        create_hotspot.setDisable(true);
        Thread t = new Thread(new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                /**
                 * creating hotspot
                 */
                mm.createHotspot();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ie){
                    ie.printStackTrace();
                }
                Platform.runLater(() -> {
                    // println(console, "already set up");
                    ObservableList<Stage> stages = FXRobotHelper.getStages();
                    stages.get(0).setScene(SceneManager.create("auth/hotspot/authhotspot.fxml"));
                });
                return 0;
            }
        });
        t.start();
    }

    public void register(ActionEvent actionEvent){
        // send message to server
        println(console, "registering to the hotspot server...");
        wifi_connected.setDisable(true);
        Thread t = new Thread(new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                /**
                 * register self to hotspot
                 */
                mm.registerToHotspot();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ie){
                    ie.printStackTrace();
                }
                println(console, "already registered");
                Platform.runLater(()->{
                    ObservableList<Stage> stages = FXRobotHelper.getStages();
                    stages.get(0).setScene(SceneManager.create("auth/client/authclient.fxml"));
                });
                return 0;
            }
        });
        t.start();
    }

}
