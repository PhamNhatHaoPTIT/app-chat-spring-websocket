package com.chat.model;

import java.sql.Timestamp;

public class Friend {

    private int id;
    private int a_id;
    private int b_id;
    private int status;
    private Timestamp add_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Timestamp add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + id +
                ", a_id: " + a_id +
                ", b_id: " + b_id +
                ", status: " + status +
                ", add_time: " + add_time +
                '}';
    }
}
