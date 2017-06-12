package com.example.vaibhav.Pocket_Expenses;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalViewUpdate extends AppCompatActivity {
    TextView textView1;  EditText editText1;
    TextView textView2;  EditText editText;
    TextView textView3;  EditText editText3;
    TextView textView4;  EditText editText4;
    TextView textView5;  EditText editText5;
    Button burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Update");
        final DatabaseHelper myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_personal_view_update);
        textView1= (TextView) findViewById(R.id.textView1);
        editText= (EditText) findViewById(R.id.editText);
        textView2= (TextView) findViewById(R.id.textView2);
        editText1= (EditText) findViewById(R.id.editText1);
        textView3= (TextView) findViewById(R.id.textView3);
        editText3= (EditText) findViewById(R.id.editText3);
        textView4= (TextView) findViewById(R.id.textView4);
        editText4= (EditText) findViewById(R.id.editText4);
        textView5= (TextView) findViewById(R.id.textView5);
        editText5= (EditText) findViewById(R.id.editText5);
        burger= (Button) findViewById(R.id.burger);

        Intent intent=getIntent();
        final String id=intent.getStringExtra("S");

        SQLiteDatabase database=openOrCreateDatabase("personal.db",MODE_APPEND,null);
        String q="Select * from pexpense_table Where id='"+id+"'";
        Cursor c=database.rawQuery(q,null);
        while(c.moveToNext())
        {
            editText.setText(c.getString(0));
            editText1.setText(c.getString(1));
            editText3.setText(c.getString(2));
            editText4.setText(c.getString(3));
            editText5.setText(c.getString(4));

        } // end of while loop

      // database.execSQL("delete from pexpense_table where id='"+id+"'");
        database.close();



        burger.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean isUpdate = myDb.upgradeData(editText.getText().toString(),editText1.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),editText5.getText().toString());
                if(isUpdate==true)
                {
                    Toast.makeText(PersonalViewUpdate.this,"updated",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(PersonalViewUpdate.this,"not updated",Toast.LENGTH_LONG).show();
                }
            }

        }); // end of button(onClickListener)

    }
}
