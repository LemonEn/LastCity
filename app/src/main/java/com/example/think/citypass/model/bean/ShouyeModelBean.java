package com.example.think.citypass.model.bean;

/**
 * Created by 张萌 on 2017/5/19.
 */

public class ShouyeModelBean {
    private String  title;
    private String  count;
    private String  author;
    private String  image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ShouyeModelBean(String title, String count, String author, String image) {

        this.title = title;
        this.count = count;
        this.author = author;
        this.image = image;
    }
}
