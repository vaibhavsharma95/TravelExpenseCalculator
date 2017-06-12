package com.example.vaibhav.Pocket_Expenses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.vaibhav.Pocket_Expenses.R.id.text;

public class DeleteUpdate extends AppCompatActivity{
    ListView listView1;
    ArrayList screens;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);
        this.setTitle("Update OR Delete");
        SQLiteDatabase database=openOrCreateDatabase("Expenses",MODE_APPEND,null);

        listView1= (ListView) findViewById(R.id.listView1);

        String str="Select * from TripDetails";
        Cursor c=database.rawQuery(str,null);

        String id;
        screens=new ArrayList();

        while(c.moveToNext())
        {
            id=c.getString(0);
            screens.add(id);
        } // end of while
        database.close();
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_checked,screens);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                final String str= (String) screens.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteUpdate.this);
                builder.setTitle("Choose Your Option");
                builder.setMessage("Select");

                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // SQLiteDatabase database = openOrCreateDatabase("Expenses", MODE_APPEND, null);
                        // String q = "select * from TripDetails where trip_id='" + str + "'";
                        // Cursor c = database.rawQuery(q, null);
                        Intent intent = new Intent(DeleteUpdate.this, Update.class);
                        intent.putExtra("S",str);

                        /*while (c.moveToNext()) {
                            String id = c.getString(0);
                            String fromplace = c.getString(1);
                            String toplace = c.getString(2);
                            String sdate = c.getString(3);
                            String edate = c.getString(4);
                            int abudget = Integer.parseInt(c.getString(5));
                            int Balance = Integer.parseInt(c.getString(6));

                            intent.putExtra("ID", id);
                            intent.putExtra("FROMPLACE", fromplace);
                            intent.putExtra("TOPLACE", toplace);
                            intent.putExtra("SDate", sdate);
                            intent.putExtra("EDate", edate);
                            intent.putExtra("ABudget", String.valueOf(abudget));
                            intent.putExtra("Bal", String.valueOf(Balance));

                        } // end of while loop */

                        //  database.execSQL("DELETE FROM TripDetails WHERE trip_id='" + str + "'");
                        startActivity(intent);

                    }

                }); // end of update button

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener()

                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase database = openOrCreateDatabase("Expenses", MODE_APPEND, null);
                                database.execSQL("DELETE FROM TripDetails WHERE trip_id='" + str + "'");
                                Toast.makeText(DeleteUpdate.this, "Trip Deleted", Toast.LENGTH_SHORT).show();
                                database.close();
                                finish();
                            }

                        }

                );  // end of delete button

                AlertDialog dialog=builder.create();
                dialog.show();

            }

        }); // statement termination




    } // end of onCreate()

} // end of main(View details) Activity
