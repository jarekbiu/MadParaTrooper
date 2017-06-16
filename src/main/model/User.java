package main.model;

/**
 * Created by wesley shi on 2017/6/16.
 */
public class User {
    private int id;
    private int level;
    private String name;
    private String description;

    public User(int id, int level, String name, String description) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
