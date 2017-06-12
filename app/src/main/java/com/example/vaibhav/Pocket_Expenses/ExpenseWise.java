package com.example.vaibhav.Pocket_Expenses;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ExpenseWise extends AppCompatActivity {

    TableLayout tableLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_wise);
        this.setTitle("Expense Wise Budget");
        tableLayout1 = (TableLayout) findViewById(R.id.tableLayout1);

        SQLiteDatabase db = openOrCreateDatabase("Expenses", MODE_APPEND, null);

        TableRow tableRow1 ;
        String q = "select * from ExpenseDetails JOIN TripDetails ON ExpenseDetails.trip_id=TripDetails.trip_id ORDER BY Category ";
        Cursor c = db.rawQuery(q, null);

        String id;
        String fromplace;
        String toplace;
        String category;
        int amount;

        while (c.moveToNext())
        {
            category = c.getString(1);
            amount = Integer.parseInt(c.getString(2));
            id = c.getString(4);
            fromplace = c.getString(6);
            toplace = c.getString(7);

            tableRow1 = new TableRow(this);
            TextView tv1 = new TextView(this);  tv1.setText(id);
            TextView tv2 = new TextView(this);  tv2.setText(fromplace);
            TextView tv3 = new TextView(this);  tv3.setText(toplace);
            TextView tv4 = new TextView(this);  tv4.setText(category);
            TextView tv5 = new TextView(this);  tv5.setText(""+amount);

            tableRow1.addView(tv1);
            tableRow1.addView(tv2);
            tableRow1.addView(tv3);
            tableRow1.addView(tv4);
            tableRow1.addView(tv5);
            tableLayout1.addView(tableRow1);


        } // end of while loop

        db.close();

    } // end of onCreate

} // end of Expense wise
