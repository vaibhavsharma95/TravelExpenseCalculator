package com.example.vaibhav.Pocket_Expenses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Menu extends AppCompatActivity
{
    TextView textView1;
    ListView listView1;
    String []screen={"Add New Trip","Trip Details","Add Expense","Budget","Delete/Update","Exit"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.setTitle("Menu");

        textView1= (TextView) findViewById(R.id.textView1);
        listView1= (ListView) findViewById(R.id.listView1);
        Intent intent=getIntent();
        String name=intent.getStringExtra("N");
        SharedPreferences sp=getSharedPreferences("DemoFile",0);
        String msg=sp.getString("STATUS1","Not Initialized");
        if(msg.equals("Not Initialized"))
        {
            textView1.append("\t" + name);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("STATUS1", "Initialized");
            editor.commit();

        } // end of if block

        else
        {
            textView1.append("\t");
        }

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_activated_1,screen)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                return view;
            }
        };

        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch(position)
                {
                    case 0 : Intent intent=new Intent(Menu.this,AddNewTrip.class);
                        startActivity(intent);
                        break;

                    case 1 : Intent intent1=new Intent(Menu.this,TripDetails.class);
                        startActivity(intent1);
                        break;

                    case 2: Intent intent2=new Intent(Menu.this,AddExpenses.class);
                        startActivity(intent2);
                        break;

                    case 3 :Intent intent4=new Intent(Menu.this,BudgetDetails.class);
                        startActivity(intent4);
                        break;

                    case 4 :Intent intent3=new Intent(Menu.this,DeleteUpdate.class);
                        startActivity(intent3);
                        break;

                    case 5: System.exit(0);
                        break;
                } // end of switch

            }

        }); // statement termination

    } // end of onCreate()


}

