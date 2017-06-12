package com.example.vaibhav.Pocket_Expenses;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TripWise extends AppCompatActivity {
    TableLayout tableLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_wise);
        this.setTitle("Trip Wise Details");
        tableLayout1= (TableLayout) findViewById(R.id.tableLayout1);
        SQLiteDatabase db = openOrCreateDatabase("Expenses", MODE_APPEND, null);

        TableRow tableRow1;
        String q = "select * from TripDetails";
        Cursor c =db.rawQuery(q, null);
        String id;
        String fromplace;
        String toplace;
        String sdate;
        String edate;
        int Balance;
        int total;
        int abudget;

        while(c.moveToNext())
        {
            id = c.getString(0);
            fromplace = c.getString(1);
            toplace=c.getString(2);
            sdate=c.getString(3);
            edate=c.getString(4);
            abudget=Integer.parseInt(c.getString(5));
            Balance=Integer.parseInt(c.getString(6));
            total=(abudget-Balance);
            tableRow1 = new TableRow(this);
            TextView textView1 = new TextView(this);  textView1.setText(id);
            TextView textView2 = new TextView(this);  textView2.setText(fromplace);
            TextView textView3 = new TextView(this);  textView3.setText(toplace);
            TextView textView4 = new TextView(this);  textView4.setText(sdate);
            TextView textView5 = new TextView(this);  textView5.setText(edate);
            TextView textView6 = new TextView(this);  textView6.setText(""+total);
            TextView textView7 = new TextView(this);  textView7.setText(""+Balance);

            tableRow1.addView(textView1);
            tableRow1.addView(textView2);
            tableRow1.addView(textView3);
            tableRow1.addView(textView4);
            tableRow1.addView(textView5);
            tableRow1.addView(textView6);
            tableRow1.addView(textView7);
            tableLayout1.addView(tableRow1);
        }//end of while loop
        db.close();
    } // end of onCreate()
} // end of Trip Wise Details Activity

