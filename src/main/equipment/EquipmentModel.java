package main.equipment;

import javafx.collections.ObservableList;
import main.model.Equipment;

/**
 * Created by wesley shi on 2017/6/14.
 */
public class EquipmentModel {
    ObservableList<Equipment> equipments;
    public EquipmentModel(ObservableList<Equipment> equips){
        equipments = equips;
    }

    public void addItem(Equipment e){
        equipments.add(e);
    }

    public void removeItem(Equipment e){
        equipments.remove(e);
    }

    public void openEquipment(Equipment equipment){
        Thread t = new Thread(()->{
            // send message
            try{
                Thread.sleep(2000);
            } catch (InterruptedException ie){
                ie.printStackTrace();
            }
        });
        t.start();
    }
}
