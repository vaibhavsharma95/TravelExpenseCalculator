package com.example.vaibhav.Pocket_Expenses;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Food extends AppCompatActivity {
    Button btn;
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        EditText editText8 = (EditText)findViewById(R.id.editText8);
        EditText editText9 = (EditText)findViewById(R.id.editText9);
        Button date = (Button)findViewById(R.id.date);
        Button submit = (Button)findViewById(R.id.submit);




        showDialogOnButtonClick();
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
            Toast.makeText(Food.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG).show();
        }
    };

}
