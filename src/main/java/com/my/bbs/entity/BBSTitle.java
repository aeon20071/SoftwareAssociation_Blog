package com.my.bbs.entity;

public class BBSTitle {
    private Integer tid;
    private String title;
    private String turl;

    public BBSTitle(String title, String turl) {
        this.tid = tid;
        this.title = title;
        this.turl = turl;
    }

    public BBSTitle() {
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }

    @Override
    public String toString() {
        return "BBSTitle{" +
                "tid=" + tid +
                ", title='" + title + '\'' +
                ", turl='" + turl + '\'' +
                '}';
    }
}
