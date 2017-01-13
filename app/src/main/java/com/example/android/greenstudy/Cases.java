package com.example.android.greenstudy;

public class Cases {

    private  String caseTitle;

    private String caseDiscription;
    private String caseLink;



    public  Cases(){

    }
    public  Cases(String caseTitle,String casediscription,String caselink) {
        if(caseTitle!=null) {
            this.caseTitle=caseTitle;
            this.caseDiscription=casediscription;
            this.caseLink=caselink;
        }}
    public void setcaseTitle(String mcaseTitle) {
        this.caseTitle = mcaseTitle;
    }

    public String getCaseLink() {
        return caseLink;
    }

    public String getCaseDiscription() {
        return caseDiscription;
    }

    public void setCaseDiscription(String caseDiscription) {
        this.caseDiscription = caseDiscription;
    }

    public void setCaseLink(String caseLink) {
        this.caseLink = caseLink;
    }





    public String gettitle() {
        return caseTitle;
    }



}
