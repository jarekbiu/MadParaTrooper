package main.auth.client;

import javafx.collections.ObservableList;
import main.model.Trooper;

import java.util.List;

/**
 * Created by wesley shi on 2017/6/14.
 */
public class AuthClientModel {
    ObservableList<Trooper> troopers;

    public AuthClientModel(ObservableList<Trooper> ts) {
        troopers = ts;
    }

    public void addAll(List<Trooper> ts){
        troopers.addAll(ts);
    }

    public int sendVerification(int index, Trooper t){
        // verify a trooper
        t.setState(Trooper.State.AC.getValue());
        troopers.set(index, t);
        return 0;
    }
}
