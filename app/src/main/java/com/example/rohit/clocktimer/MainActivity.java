package com.example.rohit.clocktimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    Button button;
    CountDownTimer countDownTimer;
//    int count=0;

    public void buttonClicked(View view){
        if(button.getText().equals("Stop")){
            countDownTimer.cancel();
            button.setText("GO!");
            seekBar.setEnabled(true);
        }else {
            countDownTimer=new CountDownTimer(seekBar.getProgress()*1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int)millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.rooster);
                    mediaPlayer.start();
                    button.setText("GO!");
                    seekBar.setEnabled(true);
                }
            };
            countDownTimer.start();
            button.setText("Stop");
            seekBar.setEnabled(false);
        }
    }

    public void updateTimer(int progress){
        int minutes=progress/60;
        int seconds=progress-(minutes*60);
        String secondsString=String.valueOf(seconds);
        if(seconds<10){
            secondsString="0"+secondsString;
        }
        textView.setText(String.valueOf(minutes)+":"+secondsString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBarTimer);
        textView=findViewById(R.id.textViewTimer);
        button=findViewById(R.id.buttonTimer);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
//                count=progress;
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
