package com.example.android.greenstudy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<Cases> cases= new ArrayList<Cases>();
    javaAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        FeedFetcher feedFetcher = new FeedFetcher(this);
        feedFetcher.execute();
        feedFetcher.progressDialog.show();


//
//

//
//
//        cases =(ArrayList<Cases>) feedFetcher.getdata1();
//        cases.add(new Cases("Scientists Have Finally Figured Out the Mysterious 'Missing Element' in Earth's Core","The team was able to reach their findings by actually creating virtual Earth models in laboratories and exposing these to real-life conditions including heat and pressure.","http://www.natureworldnews.com/articles/35000/20170112/scientists-finally-figured-out-mysterious-missing-element-earths-core.htm"));
//        cases.add(new Cases("Plants May Be Able to See, Scientists Discover","The team was able to reach their findings by actually creating virtual Earth models in laboratories and exposing these to real-life conditions including heat and pressure.","http://www.natureworldnews.com/articles/35000/20170112/scientists-finally-figured-out-mysterious-missing-element-earths-core.htm"));

//       final ArrayList<Cases> cases1= cases;
//
          adapter = new javaAdapter(this, cases);


        listView= (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
//                Toast.makeText(MainActivity.this, "List View row Clicked at"+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,CaseDisp.class);

                Bundle b3 = new Bundle();
                b3.putString("title",cases.get(position).gettitle());
                b3.putString("disp",cases.get(position).getCaseDiscription());
                b3.putString("link",cases.get(position).getCaseLink());
                intent.putExtras(b3);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.help) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.save) {
            // Handle the camera action
        } else if (id == R.id.night_mode) {

        } else if (id == R.id.help) {

        } else if (id == R.id.visit_us) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class FeedFetcher extends AsyncTask<Void,Void,Void> {
        String rssUrl ="http://www.natureworldnews.com/rss/sections/environment.xml";
        URL url;
        Context context;
        ProgressDialog progressDialog;
        String Title,disp,link;



        public FeedFetcher(Context context) {
            this.context = context;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("loading...");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Log.d("Root","hi");
            adapter.notifyDataSetChanged();

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

                    if(currentchild.getNodeName().equals("item")){

                        NodeList itemContents  = currentchild.getChildNodes();
                        for (int j=0 ;j<itemContents.getLength();j++){
                            Node current = itemContents.item(j);


                            if(current.getNodeName().equals("title")){
                                Title=current.getTextContent();

                            }
                            if(current.getNodeName().equals("media:text")){
                                disp=current.getTextContent();
                            }
                            if(current.getNodeName().equals("link")){
                                link=current.getTextContent();

                            }

                        }
                        cases.add(new Cases(Title,disp,link));
                    }
                }

            }}

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


}
