package com.example.vaibhav.Pocket_Expenses;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PersonalExpense extends AppCompatActivity {
    TableLayout tableLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_expense);
        this.setTitle("View Personal Expenses");
        tableLayout1= (TableLayout) findViewById(R.id.tableLayout1);
        SQLiteDatabase db = openOrCreateDatabase("personal.db", MODE_APPEND, null);

        TableRow tableRow1;
        String q = "select * from pexpense_table";
        Cursor c =db.rawQuery(q, null);
        String id;
        String category;
        String date;
        String itemName;
        String itemPrice;
        while(c.moveToNext())
        {
            id = c.getString(0);
            category = c.getString(1);
            date=c.getString(2);
            itemName=c.getString(3);
            itemPrice=c.getString(4);
            tableRow1 = new TableRow(this);
            TextView textView1 = new TextView(this);  textView1.setText(id);
            TextView textView2 = new TextView(this);  textView2.setText(category);
            TextView textView3 = new TextView(this);  textView3.setText(date);
            TextView textView4 = new TextView(this);  textView4.setText(itemName);
            TextView textView5 = new TextView(this);  textView5.setText(itemPrice);

            tableRow1.addView(textView1);
            tableRow1.addView(textView2);
            tableRow1.addView(textView3);
            tableRow1.addView(textView4);
            tableRow1.addView(textView5);
            tableLayout1.addView(tableRow1);
        }//end of while loop
        db.close();
    }
}
