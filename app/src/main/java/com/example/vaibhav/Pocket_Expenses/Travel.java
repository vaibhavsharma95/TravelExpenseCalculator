package com.example.vaibhav.Pocket_Expenses;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

public class Travel extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Button button1;
    Button btn;
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;

    Spinner spinner1;
    String []category={"Travelling","Meal","Lodging","Misc"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
        showDialogOnButtonClick();

        editText1= (EditText) findViewById(R.id.editText);
        editText2= (EditText) findViewById(R.id.editText1);
        editText3= (EditText) findViewById(R.id.editText3);
        editText4= (EditText) findViewById(R.id.editText4);

        button1= (Button) findViewById(R.id.burger);
        spinner1= (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer a = position;
                Toast.makeText(Travel.this, a, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Travel.this, "Nothing Selected", Toast.LENGTH_LONG).show();
            }

        }); // statement termination of OnItemSelectedListener

        // button listener

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Travel.this);
                builder.setTitle("ADD");
                builder.setMessage("Are you Sure You Want To Add Expense");


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        finish();

                    } // end of onClick

                }); // end of positive button

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                }); // end of negative button

                AlertDialog dialog = builder.create();
                dialog.show();

            }  // end of view()

        }); // statement termination of onClickListener(button)
    }
    public void showDialogOnButtonClick(){
        btn = (Button)findViewById(R.id.date);
        btn.setOnClickListener(new View.OnClickListener() {
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
            Integer date = year_x/month_x/day_x;
            Toast.makeText(Travel.this,date,Toast.LENGTH_LONG).show();
        }
    };
}
