package com.example.vaibhav.Pocket_Expenses;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Personal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        this.setTitle("Personal Expenses");

        // final Button burger =(Button)findViewById(R.id.burger);
        // final Button travel =(Button)findViewById(R.id.travel);
        final Button medical = (Button) findViewById(R.id.medical);
        // final Button entertainment =(Button)findViewById(R.id.entertainment);
        //final Button other =(Button)findViewById(R.id.other);
        final Button list = (Button) findViewById(R.id.list);
        final Button button4 = (Button) findViewById(R.id.button4);
        //final Button button5 = (Button) findViewById(R.id.button5);

       /* burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent burgerIntent = new Intent(Personal.this,Food.class);
                Personal.this.startActivity(burgerIntent);
            }
        });
        travel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent travelIntent = new Intent(Personal.this,Travel.class);
                Personal.this.startActivity(travelIntent);
            }
        });*/
        medical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent medicalIntent = new Intent(Personal.this, Medical.class);
                Personal.this.startActivity(medicalIntent);
            }
        });
       /* entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent entertainmentIntent = new Intent(Personal.this,Entertainment.class);
                Personal.this.startActivity(entertainmentIntent);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent otherIntent = new Intent(Personal.this,Other.class);
                Personal.this.startActivity(otherIntent);
            }
        });*/
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(Personal.this, PersonalExpense.class);
                Personal.this.startActivity(listIntent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(Personal.this, PersonalView.class);
                Personal.this.startActivity(listIntent);
            }
        });

    }
}
