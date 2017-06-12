package com.example.vaibhav.Pocket_Expenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Entertainment extends AppCompatActivity {
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 1;
    int year_y,month_y,day_y;
    static final int DIALO_ID = 2;
    EditText editText12;
    EditText editText13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        final Calendar calendarr = Calendar.getInstance();
        year_y = calendarr.get(Calendar.YEAR);
        month_y = calendarr.get(Calendar.MONTH);
        day_y = calendarr.get(Calendar.DAY_OF_MONTH);

        showDialogOnButtonClick();

    }

    public void showDialogOnButtonClick(){
        editText12 = (EditText) findViewById(R.id.editText12);
        editText13 = (EditText) findViewById(R.id.editText13);
        editText12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });

        editText13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALO_ID);
            }
        });

    }


    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dPicker, year_x,month_x,day_x );
        if (id == DIALO_ID)
            return new DatePickerDialog(this, dPick, year_y,month_y,day_y );

        return null;

    }
    private  DatePickerDialog.OnDateSetListener dPicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year_x = i;
            month_x = i1+1;
            day_x = i2;
            editText12.setText(day_x+"/"+month_x+"/"+year_x);
            Toast.makeText(Entertainment.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG).show();
        }
    };
    private DatePickerDialog.OnDateSetListener dPick = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int ii, int ii1, int ii2) {
            year_y = ii;
            month_y = ii1+1;
            day_y = ii2;
            editText13.setText(day_y+"/"+month_y+"/"+year_y);
            Toast.makeText(Entertainment.this,year_y+"/"+month_y+"/"+day_y,Toast.LENGTH_LONG).show();
        }
    };
}
