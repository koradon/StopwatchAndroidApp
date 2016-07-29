package com.example.michal.stopwatchandroidapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    private int milliseconds;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        if(savedInstanceState != null){
            milliseconds = savedInstanceState.getInt("milliseconds", milliseconds);
            running = savedInstanceState.getBoolean("running", running);
        }
        runTimmer();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("milliseconds", milliseconds);
        savedInstanceState.putBoolean("running", running);
    }

    public void onClickStart(View view){
        running = true;
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        milliseconds = 0;
    }

    private void runTimmer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int milisec = (milliseconds%1000)/10;
                int seconds = (milliseconds/1000)%60;
                int minutes = (int) ((milliseconds/(1000*60)) % 60);
                int hours   = (int) ((milliseconds/(1000*60*60)) % 24);

                String time = String.format("%d:%02d:%02d:%02d",
                                            hours, minutes, seconds, milisec);

                timeView.setText(time);

                if(running){
                    milliseconds += 10;
                }

                handler.postDelayed(this, 10);
            }
        });
    }
}
