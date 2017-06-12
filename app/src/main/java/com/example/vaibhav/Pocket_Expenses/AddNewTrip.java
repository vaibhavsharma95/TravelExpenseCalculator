package com.example.vaibhav.Pocket_Expenses;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewTrip extends AppCompatActivity
{
    TextView textView1;
    EditText editText1;
    TextView textView2;
    EditText editText2;
    TextView textView3;
    EditText editText3;
    TextView textView4;
    EditText editText4;
    TextView textView5;
    EditText editText5;
    TextView textView6;
    EditText editText6;
    TextView textView7;
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 1;
    int year_y,month_y,day_y;
    static final int DIALO_ID = 2;

    Button button1;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip);

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        final Calendar calendarr = Calendar.getInstance();
        year_y = calendarr.get(Calendar.YEAR);
        month_y = calendarr.get(Calendar.MONTH);
        day_y = calendarr.get(Calendar.DAY_OF_MONTH);

        textView1= (TextView) findViewById(R.id.textView1);
        editText1= (EditText) findViewById(R.id.editText);
        textView2= (TextView) findViewById(R.id.textView2);
        editText2= (EditText) findViewById(R.id.editText1);
        textView3= (TextView) findViewById(R.id.textView3);
        editText3= (EditText) findViewById(R.id.editText3);
        textView4= (TextView) findViewById(R.id.textView4);
        editText4= (EditText) findViewById(R.id.editText4);
        textView5= (TextView) findViewById(R.id.textView5);
        editText5= (EditText) findViewById(R.id.editText5);
        textView6= (TextView) findViewById(R.id.textView6);
        editText6=(EditText) findViewById(R.id.editText6);
        textView7= (TextView) findViewById(R.id.textView7);
        button1= (Button) findViewById(R.id.burger);
        button1.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(AddNewTrip.this);
                builder.setTitle("ADD");
                builder.setMessage("Are you Sure You Want To Add Trip");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        int ABudget=Integer.valueOf(editText6.getText().toString());
                        int BBudget=ABudget;

                        SQLiteDatabase database=openOrCreateDatabase("Expenses",MODE_APPEND,null);
                        database.execSQL("CREATE TABLE IF NOT EXISTS TripDetails (trip_id VARCHAR PRIMARY KEY ," +
                                "fromplace VARCHAR,toplace VARCHAR,StartDate DATE,EndDate DATE,ABudget INTEGER,BBudget INTEGER) ");

                        database.execSQL("INSERT INTO TripDetails VALUES( '"+editText1.getText().toString()+"' ,'"+editText2.getText().toString()+"'," +
                                "'"+editText3.getText().toString()+"','"+editText4.getText().toString()+"','"+editText5.getText().toString()+"',"+ABudget+","+BBudget+")");



                        editText1.setText(" ");
                        editText2.setText(" ");
                        editText3.setText(" ");
                        editText4.setText(" ");
                        editText5.setText(" ");
                        editText6.setText(" ");

                        Toast.makeText(AddNewTrip.this,"Trip Saved",Toast.LENGTH_SHORT).show();
                        database.close();
                        finish();

                    }

                }); // end of positive button

                builder.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                    }

                }); // end of negative button

                AlertDialog dialog=builder.create();
                dialog.show();

            } // end of view()

        }); // statement termination

        showDialogOnButtonClick();
    } // end of onCreate()
    public void showDialogOnButtonClick(){
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);
        editText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DIALOG_ID);
            }
        });

        editText5.setOnClickListener(new View.OnClickListener() {
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
            editText4.setText(day_x+"/"+month_x+"/"+year_x);
            Toast.makeText(AddNewTrip.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG).show();
        }
    };
    private DatePickerDialog.OnDateSetListener dPick = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int ii, int ii1, int ii2) {
            year_y = ii;
            month_y = ii1+1;
            day_y = ii2;
            editText5.setText(day_y+"/"+month_y+"/"+year_y);
            Toast.makeText(AddNewTrip.this,year_y+"/"+month_y+"/"+day_y,Toast.LENGTH_LONG).show();
        }
    };
} // end of main(add trip) activity
