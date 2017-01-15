package com.example.android.greenstudy;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SavedCaseDisp extends AppCompatActivity {
    String title, disp, link;
    TextView mtitle, mdisp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_case_disp);
        Intent intent2 = getIntent();
        Bundle b4 = intent2.getExtras();
        title = b4.getString("title");
        disp = b4.getString("disp");
        link = b4.getString("link");
        mtitle = (TextView) findViewById(R.id.activityTitle);
        mdisp = (TextView) findViewById(R.id.disp);

        if (mtitle != null && mdisp != null) {
            mtitle.setText(title);
            mdisp.setText(disp);

        }
    }
    DataBaseHandler dataBaseHandler = new DataBaseHandler(this,null,null,1);
    public void deleteCase(View view){
        dataBaseHandler.deleteCase(title);
        Toast.makeText(SavedCaseDisp.this, "Deleted "+ title,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SavedCaseDisp.this,savedCases.class);
        startActivity(intent);
        finish();
    }
}
