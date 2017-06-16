package main;

import main.model.Trooper;
import main.model.User;
import network.PeerDetector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by wesley on 2017/6/14.
 */
public class MainModel {

    User user;

    public User load(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("user.txt"));
            int id = Integer.parseInt(br.readLine());
            int level = Integer.parseInt(br.readLine());
            String name = br.readLine();
            String description = br.readLine();
            user = new User(id, level, name, description);
            return user;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String getUser(){
        if (user == null) return "";
        return user.toString();
    }

    public int connectWifi(){
        PeerDetector peerDetector = new PeerDetector();
        try{
            return peerDetector.Initialize(user.getId());
        } catch (Exception e){
            e.printStackTrace();
        }
        return -2;
    }
}
