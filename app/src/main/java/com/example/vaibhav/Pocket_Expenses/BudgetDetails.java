package com.example.vaibhav.Pocket_Expenses;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BudgetDetails extends AppCompatActivity {
    TableLayout tableLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_details);
        this.setTitle("Budget Details");

        tableLayout1= (TableLayout) findViewById(R.id.tableLayout1);

        SQLiteDatabase db = openOrCreateDatabase("Expenses", MODE_APPEND, null);

        TableRow tableRow1;
        String q = "select * from TripDetails";
        Cursor c =db.rawQuery(q, null);
        String id;
        int Balance;
        int ABudget;

        while(c.moveToNext())
        {
            id = c.getString(0);
            ABudget = Integer.parseInt(c.getString(5));
            Balance = Integer.parseInt(c.getString(6));

            tableRow1 = new TableRow(this);
            TextView tv1 = new TextView(this);
            tv1.setText(id);
            TextView tv2 = new TextView(this);
            tv2.setText(""+ABudget);
            TextView tv3 = new TextView(this);
            tv3.setText(""+Balance);

            tableRow1.addView(tv1);
            tableRow1.addView(tv2);
            tableRow1.addView(tv3);
            tableLayout1.addView(tableRow1);

        } // end of while loop

        db.close();

    } // end of onCreate()
}
