package com.chat.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "friends")
public class AppFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int status;
    private Timestamp add_time;

    @ManyToOne
    @JoinColumn(name = "a_id")
    private AppUser a;

    @ManyToOne
    @JoinColumn(name = "b_id")
    private AppUser b;

    public AppFriend() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public AppUser getA() {
        return a;
    }

    public void setA(AppUser a) {
        this.a = a;
    }

    public AppUser getB() {
        return b;
    }

    public void setB(AppUser b) {
        this.b = b;
    }
}
