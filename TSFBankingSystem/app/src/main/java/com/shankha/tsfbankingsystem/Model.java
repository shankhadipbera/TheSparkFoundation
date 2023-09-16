package com.shankha.tsfbankingsystem;

public class Model {
    int id = -1;
    int ballance = 0;
    String name = "";

    String email = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBallance() {
        return ballance;
    }

    public void setBallance(int ballance) {
        this.ballance = ballance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
