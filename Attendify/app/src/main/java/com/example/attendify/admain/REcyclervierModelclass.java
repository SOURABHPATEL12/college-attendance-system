package com.example.attendify.admain;

public class REcyclervierModelclass {

    int img;
    String name ;
    String gmail;
    String depertment;
    String branch;

    public REcyclervierModelclass(int img, String name,String gmail, String depertment,String branch){

        this.img =img;
        this.name=name;
        this.gmail=gmail;
        this.depertment=depertment;
        this.branch=branch;


    }

    public int getImage() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return gmail;
    }

    public String getDepartment() {
        return depertment;
    }

    public String getBranch() {
        return branch;
    }
}
