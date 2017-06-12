package com.example.vaibhav.Pocket_Expenses;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddExpenses extends AppCompatActivity {
    TextView textView1;
    EditText editText1;
    TextView textView2;
    EditText editText2;
    TextView textView3;
    EditText editText3;
    TextView textView5;
    EditText editText4;
    TextView textView6;
    TextView textView7;
    Button button1;
    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;

    Spinner spinner1;
    String []category={"Travelling","Meal","Lodging","Misc"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        this.setTitle("Add Expenses");

        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);

        textView1= (TextView) findViewById(R.id.textView1);
        textView2= (TextView) findViewById(R.id.textView2);
        editText1= (EditText) findViewById(R.id.editText1);
        textView3= (TextView) findViewById(R.id.textView3);
        editText2= (EditText) findViewById(R.id.editText2);
        textView5= (TextView) findViewById(R.id.textView5);
        editText3= (EditText) findViewById(R.id.editText3);
        textView6= (TextView) findViewById(R.id.textView6);
        editText4= (EditText) findViewById(R.id.editText4);
        textView7= (TextView) findViewById(R.id.textView7);

        button1= (Button) findViewById(R.id.button1);
        spinner1= (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editText1.setText(category[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(AddExpenses.this, "Nothing Selected", Toast.LENGTH_LONG).show();
            }

        }); // statement termination of OnItemSelectedListener

        // button listener

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddExpenses.this);
                builder.setTitle("ADD");
                builder.setMessage("Are you Sure You Want To Add Expense");


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int amount = Integer.parseInt(String.valueOf(editText2.getText()));
                        SQLiteDatabase database = openOrCreateDatabase("Expenses", MODE_APPEND, null);
                        database.execSQL("CREATE TABLE IF NOT EXISTS ExpenseDetails (expense_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "Category VARCHAR ,Amount INTEGER,Date DATE,trip_id VARCHAR) ");

                        database.execSQL("INSERT INTO ExpenseDetails(Category,Amount,Date,trip_id) VALUES( '" + editText1.getText().toString() + "' ," + amount + "," +
                                "'" + editText3.getText().toString() + "','" + editText4.getText().toString() + "')");

                        // database.execSQL("UPDATE TripDetails   set BBudget=BBudget-"+amount+" ");

                        database.execSQL("UPDATE TripDetails SET BBudget=BBudget-"+amount+" WHERE trip_id IN" +
                                " (SELECT trip_id FROM ExpenseDetails WHERE trip_id = '"+editText4.getText().toString()+"')");


                        editText2.setText(" ");
                        editText3.setText(" ");
                        editText4.setText(" ");
                        Toast.makeText(AddExpenses.this, "Expense Saved and Updated", Toast.LENGTH_SHORT).show();

                        database.close();

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

        showDialogOnButtonClick();

    } // end of onCreate()


    public void showDialogOnButtonClick(){
        editText3= (EditText) findViewById(R.id.editText3);
        editText3.setOnClickListener(new View.OnClickListener() {
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
            editText3.setText(day_x+"/"+month_x+"/"+year_x);
            Toast.makeText(AddExpenses.this,year_x+"/"+month_x+"/"+day_x,Toast.LENGTH_LONG).show();
        }
    };

} // end of Third(Add Expenses)Activity
