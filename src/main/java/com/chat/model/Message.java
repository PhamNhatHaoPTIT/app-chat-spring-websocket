package com.chat.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable, Comparable<Message> {
    private long id;
    private int from_user_id;
    private int to_user_id;
    private String content;
    private int message_type;
    private Timestamp send_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(int from_user_id) {
        this.from_user_id = from_user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public Timestamp getSend_time() {
        return send_time;
    }

    public void setSend_time(Timestamp send_time) {
        this.send_time = send_time;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"from_user_id\":" + from_user_id +
                ", \"to_user_id\":" + to_user_id +
                ", \"content\":\"" + content + "\"" +
                ", \"message_type\":" + message_type +
                ", \"send_time\":" + send_time.getTime() +
                '}';
    }


    @Override
    public int compareTo(Message message) {
        return (int) (this.getId() - message.getId());
    }
}
