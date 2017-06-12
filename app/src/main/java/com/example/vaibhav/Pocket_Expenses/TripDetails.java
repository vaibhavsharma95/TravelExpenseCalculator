package com.example.vaibhav.Pocket_Expenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TripDetails extends AppCompatActivity {
    TextView textView1;
    ListView listView1;
    String []screen={"Trip Wise Details","Expense Wise Details","Day Wise Details"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        this.setTitle("Trip Details");

        textView1= (TextView) findViewById(R.id.textView1);
        listView1= (ListView) findViewById(R.id.listView1);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,screen)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                return view;
            }
        };

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(TripDetails.this, TripWise.class);
                        startActivity(intent);
                        break;

                    case 1:
                        Intent intent2 = new Intent(TripDetails.this, ExpenseWise.class);
                        startActivity(intent2);
                        break;

                    case 2:
                        Intent intent3 = new Intent(TripDetails.this, DayWise.class);
                        startActivity(intent3);
                        break;
                } // end of switch

            } // end of onItemClickListener

        }); // statement termination

    } // end of onCreate()

} // end of Fifth(View Details)Activity
