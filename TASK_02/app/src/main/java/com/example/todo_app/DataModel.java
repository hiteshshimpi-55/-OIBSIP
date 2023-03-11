package com.example.todo_app;

public class DataModel {
    String name;
    boolean check;

    public DataModel() {
    }
    public DataModel(String name, boolean check) {
        this.name = name;
        this.check = check;
    }

    @Override
    public String toString() {
        return "DataModel{" +
                "name='" + name + '\'' +
                ", check=" + check +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
