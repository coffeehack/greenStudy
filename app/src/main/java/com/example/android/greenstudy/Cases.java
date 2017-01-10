package com.example.android.greenstudy;

public class Cases {
    private int _id;
    private  String caseTitle;


    public  Cases(){

    }
    public  Cases(String caseTitle) {
        if(caseTitle!=null) {
            this.caseTitle=caseTitle;
        }}
    public void setcaseTitle(String mcaseTitle) {
        this.caseTitle = mcaseTitle;
    }



    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public String gettitle() {
        return caseTitle;
    }



}
