package com.example.android.greenstudy;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FeedFetcher extends AsyncTask<Void,Void,Void> {
String rssUrl ="http://www.natureworldnews.com/rss/sections/environment.xml";
    URL url;
    Context context;
    ProgressDialog progressDialog;
    String Title,disp,link;
    ArrayList<Cases> cases= new ArrayList<Cases>();


    public FeedFetcher(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("loading...");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
       ProcessXml(getdata());
        return null;
    }

    private void ProcessXml(Document data) {
    if(data!=null){
        Element rss = data.getDocumentElement();
        Node channel = rss.getChildNodes().item(1);
        NodeList itemlist =channel.getChildNodes();
        for (int i=0 ;i<itemlist.getLength();i++){
            Node currentchild = itemlist.item(i);
            if(currentchild.getNodeName().equalsIgnoreCase("item")){
                NodeList itemContents  = currentchild.getChildNodes();
                for (int j=0 ;j<itemContents.getLength();j++){
                    Node current = itemContents.item(j);
                    if(current.getNodeName().equalsIgnoreCase("title")){
                        Title=current.getAttributes().toString();
                    }
                    if(current.getNodeName().equalsIgnoreCase("media:text")){
                        disp=current.getAttributes().toString();
                    }if(current.getNodeName().equalsIgnoreCase("link")){
                        link=current.getAttributes().toString();
                    }
                       cases.add(new Cases(Title,disp,link));
                }
            }
        }

    }
    }
    public ArrayList<Cases> getdata1(){
        return cases;
    }

    public Document getdata(){
        try {
            url = new URL(rssUrl);
            HttpURLConnection connection =(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document rssxml= builder.parse(inputStream);
            return rssxml;

        } catch (Exception e) {
            e.printStackTrace();
            Document rss =null;
         return  rss;
        }
    }
}