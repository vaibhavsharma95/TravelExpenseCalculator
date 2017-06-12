package com.example.vaibhav.Pocket_Expenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class Medical extends AppCompatActivity {

    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;
    DatabaseHelper myDb;
    EditText date,itemname,itemprice,editText2;
    Button submit;
    Spinner spinner;
    String []category={"Food","Grocery","Maintenance","Wages","Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);
        this.setTitle("Add Personal Expenses");

        myDb = new DatabaseHelper(this);
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
       // category = (EditText)findViewById(R.id.category);
        editText2 = (EditText) findViewById(R.id.editText2);
        itemname = (EditText)findViewById(R.id.itemname);
        itemprice = (EditText)findViewById(R.id.itemprice);
        submit = (Button)findViewById(R.id.submit);
        spinner= (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        showDialogOnButtonClick();
        addData();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //spinner.setSelection(position);
               // Toast.makeText(Medical.this,position,Toast.LENGTH_LONG).show();
                //editText.setText(category[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Medical.this, "Nothing Selected", Toast.LENGTH_LONG).show();
            }

        });


        }
    public void showDialogOnButtonClick(){
        editText2 = (EditText) findViewById(R.id.editText2);
        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);

            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dPicker, year_x,month_x,day_x );
        return null;

    }
    private  DatePickerDialog.OnDateSetListener dPicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year_x = i;
            month_x = i1+1;
            day_x = i2;
            editText2.setText(day_x+"/"+month_x+"/"+year_x);
            Toast.makeText(Medical.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG).show();
        }
    };
        public void addData(){
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   boolean isInserted = myDb.insertData(spinner.getSelectedItem().toString(),editText2.getText().toString(),itemname.getText().toString(),itemprice.getText().toString());
                    if(isInserted=true)
                    {
                        Toast.makeText(Medical.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        Intent listIntent = new Intent(Medical.this,Personal.class);
                        Medical.this.startActivity(listIntent);
                    }
                    else {
                        Toast.makeText(Medical.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

}
