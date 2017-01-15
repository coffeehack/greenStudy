package com.example.android.greenstudy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by aniket sharma on 15-01-2017.
 */

public class DataBaseHandler extends SQLiteOpenHelper {
        private static final  int  DATABASE_VERSION = 1;
        private static final String DATABASE_NAME ="cases.db";
        private static final String TABLE_NAME ="cases";
        private static final String COLOUMN_ID ="_id";
        private static final String COLOUMN_CASE_TITLE="_case";
        private static final String COLOUMN_CASE_DISP="disp";
        private static final String COLOUMN_CASE_LINK="link";
        //first
        public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        }
        //second

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String Query = "CREATE TABLE "+TABLE_NAME+"("+

                    COLOUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLOUMN_CASE_TITLE +" TEXT, " +
                    COLOUMN_CASE_DISP+" TEXT,"+
                    COLOUMN_CASE_LINK+" TEXT "+
                    ");";
            //executes the sql query
            sqLiteDatabase.execSQL(Query);

        }
        //third
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXITS "+ TABLE_NAME);
            onCreate(sqLiteDatabase);

        }
        //adding the data to database
        public void addCase(Cases cases){
            ContentValues values = new ContentValues();
            values.put(COLOUMN_CASE_TITLE,cases.gettitle());
            values.put(COLOUMN_CASE_DISP,cases.getCaseDiscription());
            values.put(COLOUMN_CASE_LINK,cases.getCaseLink());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_NAME,null,values);
            db.close();
        }
        //delete task from the database
        public  void deleteCase(String casename){
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COLOUMN_CASE_TITLE+"=\""+casename+"\";");
        }
        //returns the list from database
        public ArrayList<Cases> dataToCaseList(){
            ArrayList<Cases> cases= new ArrayList<Cases>();String query = "SELECT * FROM "+TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(query,null);
            if (cursor.moveToFirst()){
                do {
                    if(cursor.getString(cursor.getColumnIndex("_case"))!=null){
                        cases.add(new Cases(cursor.getString(cursor.getColumnIndex("_case")),cursor.getString(cursor.getColumnIndex("disp")),cursor.getString(cursor.getColumnIndex("link"))));
                    }
                } while (cursor.moveToNext());

            }
            cursor.close();
            return cases;
        }

    }


