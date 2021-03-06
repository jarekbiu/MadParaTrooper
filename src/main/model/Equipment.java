package main.model;

/**
 * Created by wesley shi on 2017/6/13.
 */
public class Equipment {
    String id;
    String description;

    public Equipment(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return id + " " + description;
    }

    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Equipment)){
            return false;
        }
        Equipment e = (Equipment)o;
        return e.id == id;
    }
}
