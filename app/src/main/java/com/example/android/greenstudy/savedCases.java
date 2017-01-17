package com.example.android.greenstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class savedCases extends AppCompatActivity {
    ArrayList<Cases> cases= new ArrayList<Cases>();
    javaAdapter adapter;
    ListView listView;
    TextView emptyView;
    DataBaseHandler dataBaseHandler =  new DataBaseHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_cases);
       cases= dataBaseHandler.dataToCaseList();
       adapter = new javaAdapter(this,cases);
        listView =(ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setEmptyView(findViewById(R.id.emptysavedcases));
        listView.setOnItemClickListener(new  AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "List View row Clicked at"+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(savedCases.this,SavedCaseDisp.class);

                Bundle b3 = new Bundle();
                b3.putString("title",cases.get(position).gettitle());
                b3.putString("disp",cases.get(position).getCaseDiscription());
                b3.putString("link",cases.get(position).getCaseLink());
                intent.putExtras(b3);
                startActivity(intent);
                finish();
            }
        });
    }
}
