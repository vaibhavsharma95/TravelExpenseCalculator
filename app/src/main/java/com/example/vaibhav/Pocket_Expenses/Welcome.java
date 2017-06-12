package com.example.vaibhav.Pocket_Expenses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome extends AppCompatActivity
{
    TextView textView1;
    EditText editText1;
    String name;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.setTitle("Pocket Expenses");

        textView1= (TextView) findViewById(R.id.textView1);
        editText1= (EditText) findViewById(R.id.editText);
        button =(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name=editText1.getText().toString();
                if(TextUtils.isEmpty(name)) {
                    editText1.setError("Name can't be left blank");
                    return;
                }
                else{
                    SharedPreferences sp=getSharedPreferences("DemoFile",0);
                    String msg=sp.getString("Stat", null);
                    editText1.setText(msg);
                    Intent nameIntent=new Intent(Welcome.this,Home.class);
                    nameIntent.putExtra("name",name);
                    startActivity(nameIntent);
                }

            }
        });





    } // end of onCreate

    @Override
    protected void onStop()
    {
        SharedPreferences sp=getSharedPreferences("DemoFile",0);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("Stat",editText1.getText().toString());
        editor.commit();
        super.onStop();
    }

     //end of go()

} // end of Main(Welcome) Activity
