package com.chat.model;

public class MessageProcessResult<T> {
    private T data1;
    private T data2;
    private int status;
    private String process_result;

    public MessageProcessResult() {

    }

    public MessageProcessResult(T data1, T data2, int status, String process_result) {
        this.data1 = data1;
        this.data2 = data2;
        this.status = status;
        this.process_result = process_result;
    }

    public String getProcess_result() {
        return process_result;
    }

    public void setProcess_result(String process_result) {
        this.process_result = process_result;
    }

    public T getData1() {
        return data1;
    }

    public void setData1(T data1) {
        this.data1 = data1;
    }

    public T getData2() {
        return data2;
    }

    public void setData2(T data2) {
        this.data2 = data2;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MessageProcessResult{" +
                "data1=" + data1 +
                ", data2=" + data2 +
                ", status=" + status +
                ", process_result='" + process_result + '\'' +
                '}';
    }
}
