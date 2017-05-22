package com.example.think.citypass.model.bean;

/**
 * Created by 张萌 on 2017/5/16.
 */

public class ModelOneBean {
    private String  title;
    private String  count;
    private String  author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ModelOneBean(String title, String count, String author) {

        this.title = title;
        this.count = count;
        this.author = author;
    }
}
