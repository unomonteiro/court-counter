package io.monteirodev.courtcounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String PREFS_FILE = "io.monteirodev.courtcounter.preferences";
    public static final String KEY_SCORE_TEAM_A = "score_team_a";
    public static final String KEY_SCORE_TEAM_B = "score_team_b";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private int scoreTeamA;
    private int scoreTeamB;

//    // Keeps scores on rotation, but looses onDestroy()
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        outState.putInt(KEY_SCORE_TEAM_A, scoreTeamA);
//        outState.putInt(KEY_SCORE_TEAM_B, scoreTeamB);
//
//        Log.d(TAG, "onSaveInstanceState A: " + scoreTeamA);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        scoreTeamA = savedInstanceState.getInt(KEY_SCORE_TEAM_A);
//        scoreTeamB = savedInstanceState.getInt(KEY_SCORE_TEAM_B);
//        Log.d(TAG, "onRestoreInstanceState A: " + scoreTeamA);
//        displayForTeamA(scoreTeamA);
//        displayForTeamB(scoreTeamB);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        scoreTeamA = mSharedPreferences.getInt(KEY_SCORE_TEAM_A, 0);
        scoreTeamB = mSharedPreferences.getInt(KEY_SCORE_TEAM_B, 0);
        Log.d(TAG, "onCreate A: " + scoreTeamA);

        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mEditor.putInt(KEY_SCORE_TEAM_A, scoreTeamA);
        mEditor.putInt(KEY_SCORE_TEAM_B, scoreTeamB);
        mEditor.apply();
        Log.d(TAG, "onPause A: " + scoreTeamA);
    }

    public void addThreeForTeamA(View view) {
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    public void addTwoForTeamA(View view) {
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    public void addOneForTeamA(View view) {
        scoreTeamA += 1;
        displayForTeamA(scoreTeamA);
    }

    public void addThreeForTeamB(View view) {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    public void addTwoForTeamB(View view) {
        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    public void addOneForTeamB(View view) {
        scoreTeamB += 1;
        displayForTeamB(scoreTeamB);
    }

    public void resetScore(View view) {
//        mEditor.clear();
//        mEditor.apply();
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }
}
