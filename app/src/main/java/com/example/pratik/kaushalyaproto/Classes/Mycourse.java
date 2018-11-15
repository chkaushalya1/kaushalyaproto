package com.example.pratik.kaushalyaproto.Classes;

public class Mycourse {

    String courseid, status;

    public Mycourse(String courseid, String status) {
        this.courseid = courseid;
        this.status = status;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
