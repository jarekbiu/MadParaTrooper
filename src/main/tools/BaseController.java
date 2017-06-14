package main.tools;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by wesley shi on 2017/6/13.
 */
public class BaseController implements Initializable{

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO (don't really need to do anything here).
    }


    protected void print(TextArea console, String p){
        console.appendText(p);
    }

    protected void println(TextArea console, String p){
        console.appendText(p+'\n');
    }
}
