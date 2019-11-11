package com.chat.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_info")
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    private String password;
    private Timestamp add_time;
    private int status;

    @OneToMany(mappedBy = "a", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<AppFriend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "b", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    private List<AppFriend> self = new ArrayList<>();

    @OneToMany(mappedBy = "from", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("send_time")
    @Fetch(FetchMode.SUBSELECT)
    private List<AppMessage> fromMessages = new ArrayList<>();                         // send

    @OneToMany(mappedBy = "to", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("send_time")
    @Fetch(FetchMode.SUBSELECT)
    private List<AppMessage> toMessages = new ArrayList<>();                         // receive

    public AppUser() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Timestamp add_time) {
        this.add_time = add_time;
    }

    public List<AppFriend> getFriends() {
        return friends;
    }

    public void setFriends(List<AppFriend> friends) {
        this.friends = friends;
    }

    public List<AppFriend> getSelf() {
        return self;
    }

    public void setSelf(List<AppFriend> self) {
        this.self = self;
    }

    public List<AppMessage> getFromMessages() {
        return fromMessages;
    }

    public void setFromMessages(List<AppMessage> fromMessages) {
        this.fromMessages = fromMessages;
    }

    public List<AppMessage> getToMessages() {
        return toMessages;
    }

    public void setToMessages(List<AppMessage> toMessages) {
        this.toMessages = toMessages;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", add_time=" + add_time +
                ", friends=" + friends +
                ", fromMessages=" + fromMessages +
                ", toMessages=" + toMessages +
                '}';
    }
}
