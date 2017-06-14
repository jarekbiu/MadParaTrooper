package main.equipment;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.model.Equipment;
import main.tools.BaseController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by wesley on 2017/6/13.
 */
public class EquipmentController extends BaseController{

    @FXML
    TextArea console;
    @FXML
    ListView<Equipment> requests;

    EquipmentModel em;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO (don't really need to do anything here).
        ObservableList<Equipment> oe = FXCollections.observableArrayList();
        em = new EquipmentModel(oe);
        // mock
        em.addItem(new Equipment("10", "abfabfefaefwfwee3"));
        em.addItem(new Equipment("11", "ababffefaefwfw223"));
        em.addItem(new Equipment("12", "ababfabfefwfw221"));
        em.addItem(new Equipment("13", "ababfabfefaef12"));

        requests.setItems(oe);
        requests.setCellFactory(list->{
            ListCell<Equipment> le = new ListCell<Equipment>(){
                @Override
                public void updateItem(Equipment item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null||empty) {
                        setText(null);
                        setGraphic(null);
                        return;
                    }
                    setText(item.toString());
                }
            };
            le.setOnMouseClicked((event)->{
                if (event.getClickCount()==2 && !le.isEmpty()){
                    Equipment e = le.getItem();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("confirm");
                    alert.setHeaderText("agree to open the equipment"+ e.getId() + "?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get()==ButtonType.OK){
                        em.openEquipment(e);
                        Platform.runLater(()->{
                            console.appendText("equipment"+ e.getId()+" is agreed!\n");
                        });
                    }
                }
            });
            return le;
        });
    }
}
