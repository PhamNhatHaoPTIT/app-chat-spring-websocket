package com.chat.model;

import java.io.Serializable;
import java.util.List;

public class Pager<T> implements Serializable {
    private static final long serialVersionUID = -135979093899383732L;
    private int current_page;
    private int total_record;
    private int total_page;
    private int page_size;
    private List<T> dataList;

    public Pager(int current_page, int total_record, int total_page, int page_size, List<T> dataList) {
        super();
        this.current_page = current_page;
        this.total_record = total_record;
        this.total_page = total_page;
        this.page_size = page_size;
        this.dataList = dataList;
    }

    public Pager() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_record() {
        return total_record;
    }

    public void setTotal_record(int total_record) {
        this.total_record = total_record;
    }

    public int getTotal_page() {
        return total_page;
    }

    public void setTotal_page(int total_page) {
        this.total_page = total_page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "{" +
                "current_page:" + current_page +
                ", total_record:" + total_record +
                ", total_page:" + total_page +
                ", page_size:" + page_size +
                ", dataList:" + dataList +
                "}";
    }
}
