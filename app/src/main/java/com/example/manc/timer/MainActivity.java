package com.example.manc.timer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerBar;
    TextView timeDisplay;
    Button btnController;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    public void resetTimer() {

        timeDisplay.setText("0:30");
        timerBar.setProgress(30);
        countDownTimer.cancel();
        btnController.setText("START");
        timerBar.setEnabled(true);
        counterIsActive = false;

    }

    public void updateTimer (int secondsLeft) {

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {

            secondString = "0" + secondString;

        }

        timeDisplay.setText(Integer.toString(minutes) + ":" + secondString);

    }

    public void btnController (View view) {

        if (counterIsActive == false) {

            counterIsActive = true;
            timerBar.setEnabled(false);
            btnController.setText("RESET");

            countDownTimer = new CountDownTimer(timerBar.getProgress() * 1000 + 100, 1000) {

                public void onTick(long millisecondsUntilDone) {

                    // Countdown is counting down (every second)

                    updateTimer((int) millisecondsUntilDone / 1000);
                    Log.i("Seconds left", String.valueOf(millisecondsUntilDone / 1000));

                }

                public void onFinish() {

                    timeDisplay.setText("0:00");
                    MediaPlayer airHorn = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    airHorn.start();
                    resetTimer();

                }

            }.start();

        } else {

            resetTimer();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerBar = (SeekBar) findViewById(R.id.timerBar);
        timeDisplay  = (TextView) findViewById(R.id.timeDisplay);
        btnController = (Button) findViewById(R.id.btnController);

        timerBar.setMax(600);
        timerBar.setProgress(30);

        timerBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }



    }

/*        final Handler handler = new Handler();
        final Runnable run = new Runnable() {
            @Override
            public void run() {

//                Insert code to be run every second

                Log.i("Run", "A second must have passed!");
                handler.postDelayed(this, 1000);

            }
        };

        handler.post(run);*/

