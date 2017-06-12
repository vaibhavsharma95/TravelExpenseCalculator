package com.example.vaibhav.Pocket_Expenses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Vaibhav on 02-12-2016.
 */

public class Splash_Screen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(Splash_Screen.this,Welcome.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        super.onPause();
        finish();
    }
}
