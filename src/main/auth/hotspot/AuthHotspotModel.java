package main.auth.hotspot;

import javafx.collections.ObservableList;
import main.model.Trooper;

/**
 * Created by wesley on 2017/6/13.
 */
public class AuthHotspotModel {
    ObservableList<Trooper> troopers;
    public AuthHotspotModel(ObservableList<Trooper> ts){
        troopers = ts;
    }

    public void addItem(Trooper t){
        troopers.add(t);
    }

    public int sendVerification(int index, Trooper t){
        t.setState(Trooper.State.AC.getValue());
        troopers.set(index, t);
        // verify a trooper
        return 0;
    }
}
