package com.example.android.uclcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**Tracks score for Real Madrid **/
    int scoreForReal = 0;

    //Tracks shoots for Real Madrid
    int shootsForReal = 0;

    /**Tracks score for Juventus **/
    int scoreForJuve = 0;

    //Tracks shoots for Juventus
    int shootsForJuve = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForJuve(0);
        displayForReal(0);
        displayForJuveShoots(0);
        displayForRealShoots(0);
    }

    /**
     * Displays the given score for Real Madrid
     */
    public void displayForReal(int score) {
        TextView scoreView = (TextView) findViewById(R.id.real_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given shoots for Real Madrid
     */
    public void displayForRealShoots(int shoots) {
        TextView scoreView = (TextView) findViewById(R.id.real_shoots);
        scoreView.setText(String.valueOf(shoots));
    }

    /**
     * Displays the given score for Juventus
     */
    public void displayForJuve(int score) {
        TextView scoreView = (TextView) findViewById(R.id.juve_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given shoots for Juventus
     */
    public void displayForJuveShoots(int shoots) {
        TextView scoreView = (TextView) findViewById(R.id.juve_shoots);
        scoreView.setText(String.valueOf(shoots));
    }

    //Add +1 shoot for Real Madrid
    public void shoot_for_real (View v) {
        shootsForReal = shootsForReal + 1;
        displayForRealShoots(shootsForReal);
    }

    //Add +1 goal AND +1 shoot for Real Madrid
    public void goal_for_real (View v) {
        scoreForReal = scoreForReal + 1;
        shootsForReal = shootsForReal +1;
        displayForReal(scoreForReal);
        displayForRealShoots(shootsForReal);
    }

    //Add +1 shoot for Juventus
    public void shoot_for_juve (View v) {
        shootsForJuve = shootsForJuve + 1;
        displayForJuveShoots(shootsForJuve);
    }

    //Add +1 goal AND +1 shoot for Juventus
    public void goal_for_juve (View v) {
        scoreForJuve = scoreForJuve + 1;
        shootsForJuve = shootsForJuve +1;
        displayForJuve(scoreForJuve);
        displayForJuveShoots(shootsForJuve);
    }



}
