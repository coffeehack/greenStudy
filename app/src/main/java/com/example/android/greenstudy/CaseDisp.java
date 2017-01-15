package com.example.android.greenstudy;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.onClick;

public class CaseDisp extends AppCompatActivity {
    String title, disp, link;
    TextView mtitle, mdisp,mlink;
    DataBaseHandler dataBaseHandler = new DataBaseHandler(this,null,null,1);

    public View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initListener();

        setContentView(R.layout.activity_case_disp);
        Intent intent2 = getIntent();
        Bundle b4 = intent2.getExtras();
        title = b4.getString("title");
        disp = b4.getString("disp");
        link = b4.getString("link");
        mtitle = (TextView) findViewById(R.id.activityTitle);
        mdisp = (TextView) findViewById(R.id.disp);
        //   mlink = (TextView) findViewById(R.id.link);
        if (mtitle != null && mdisp != null) {
            mtitle.setText(title);
            mdisp.setText(disp);
            //     mlink.setText(link);
        }
        final Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse(link);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browserIntent);

            }


        });


    }
    public void addDatatoDataBase(View view){
        Cases cases = new Cases();
        cases.setCaseDiscription(disp);
        cases.setcaseTitle(title);
        cases.setCaseLink(link);
        dataBaseHandler.addCase(cases);
        Snackbar.make(view, "Saved check your saved list", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
    }

}
