package com.example.testrun;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tv_displaytime;

    private int seconds=0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("OnCreate has Began","OnCreate!");


        tv_displaytime = (TextView) findViewById(R.id.tv_displaytime);



        runTime();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        if(savedInstanceState!=null)
    {
        seconds = savedInstanceState.getInt("seconds");
        running = savedInstanceState.getBoolean("running");
    }

    }

    public void onClickResetButton(View view) {
        Log.i("Runnable has Reset","Reset!");
        running=false;
        seconds=0;
    }

    public void onClickStartButton(View view) {
        Log.i("Runnable has Start","Start!");
        running=true;
    }

    public void onClickStopBotton(View view) {
        Log.i("Runnable has Stopped","Stop!");
        running=false;
    }

    private void runTime()
    {
        final Handler handler = new Handler();

        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                Log.i("Runnable has run","Second has passed!");

                String time = String.format(Locale.getDefault(),"%d : %02d : %02d",hours, minutes, secs);
                tv_displaytime.setText(time);

                if(running)
                {
                    seconds++;
                }

                handler.postDelayed(this,1000);
            }
        });
    }


}
