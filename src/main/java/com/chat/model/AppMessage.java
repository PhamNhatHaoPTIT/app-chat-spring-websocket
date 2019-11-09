package com.chat.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
public class AppMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @Transient
    private Integer message_type;
    private Timestamp send_time;

    @ManyToOne
    @JoinColumn(name = "from_user_id")
    private AppUser from;

    @ManyToOne
    @JoinColumn(name = "to_user_id")
    private AppUser to;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMessage_type() {
        return message_type;
    }

    public void setMessage_type(Integer message_type) {
        this.message_type = message_type;
    }

    public Timestamp getSend_time() {
        return send_time;
    }

    public void setSend_time(Timestamp send_time) {
        this.send_time = send_time;
    }

    public AppUser getFrom() {
        return from;
    }

    public void setFrom(AppUser from) {
        this.from = from;
    }

    public AppUser getTo() {
        return to;
    }

    public void setTo(AppUser to) {
        this.to = to;
    }
}
