package com.example.vaibhav.Pocket_Expenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.setTitle("Home");

        TextView textView9 = (TextView) findViewById(R.id.textView9);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Intent nameIntent = getIntent();
        final  String name = nameIntent.getStringExtra("name");
        textView9.setText(name);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent businessIntent= new Intent(Home.this, Personal.class);
                businessIntent.putExtra("name", name);
                Home.this.startActivity(businessIntent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent businessIntent= new Intent(Home.this, Menu.class);
                businessIntent.putExtra("name", name);
                Home.this.startActivity(businessIntent);
            }
        });
    }
}
