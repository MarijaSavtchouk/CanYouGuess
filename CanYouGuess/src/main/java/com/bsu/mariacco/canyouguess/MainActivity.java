package com.bsu.mariacco.canyouguess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private TextView tvAttempts;
    private TextView tvInfo;
    private EditText etInput;
    private Button bControl;
    private Button bNewGameControl;
    private int generatedNumber;
    private int minNumber = 0;
    private int maxNumber = 100;
    private int attemptsCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.infoTextView);
        etInput = (EditText)findViewById(R.id.guessNumberEditText);
        bControl = (Button)findViewById(R.id.submitButton);
        bNewGameControl = (Button)findViewById(R.id.newGameButton);
        tvAttempts = (TextView)findViewById(R.id.attempsTextView);
        tvAttempts.setText(getResources().getString(R.string.attempt)+" "+attemptsCount);
        changeGeneratedNumber();
    }
    private void changeGeneratedNumber(){
        generatedNumber = 5;
    }
    public void onClickNextTrial(View view){
            try {
                tvInfo.setText(getResources().getString(R.string.try_to_guess));
                int guessedNumber = Integer.parseInt(etInput.getText().toString());
                if(guessedNumber<minNumber||guessedNumber>maxNumber) {
                    tvInfo.setText(getResources().getString(R.string.error));
                }
                if (guessedNumber > generatedNumber) {
                    attemptsCount++;
                    tvInfo.setText(getResources().getString(R.string.ahead));
                } else if (guessedNumber == generatedNumber) {
                    tvInfo.setText(getResources().getString(R.string.hit));
                    bControl.setEnabled(false);
                    bControl.setText(getResources().getString(R.string.win));
                    etInput.setEnabled(false);
                    bNewGameControl.setText(getResources().getString(R.string.play_more));
                } else {
                    attemptsCount++;
                    tvInfo.setText(getResources().getString(R.string.behind));
                }
                tvAttempts.setText(getResources().getString(R.string.attempt)+" "+attemptsCount);
            } catch (Exception e) {
                tvInfo.setText(getResources().getString(R.string.error));
            }
    }
    public void onClickNewGame(View view) {
        attemptsCount = 0;
        tvInfo.setText(getResources().getString(R.string.try_to_guess));
        bNewGameControl.setText(getResources().getString(R.string.new_game));
        changeGeneratedNumber();
        bControl.setEnabled(true);
        bControl.setText(getResources().getString(R.string.input_value));
        etInput.setText("");
        etInput.setEnabled(true);
        tvAttempts.setText(getResources().getString(R.string.attempt)+" "+attemptsCount);
    }
}
