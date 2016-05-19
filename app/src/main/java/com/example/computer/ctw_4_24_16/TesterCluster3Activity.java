package com.example.computer.ctw_4_24_16;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Computer on 5/8/2016.
 */
public class TesterCluster3Activity extends Activity {

    private static final String TAG = "TesterCluster3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils_debug_awaheed.log_awaheed(TAG, "---- Run 1 ----");

        ArrayList<Integer> remaining_levels = new ArrayList<Integer>(1);
        remaining_levels.add(GameConstants.THREE_CLUSTER_LEVEL_ID);

        final GameState gameState = LevelCoordinator.createGameState(remaining_levels);

        Utils_debug_awaheed.log_awaheed(TAG, "gamestate: " + gameState.toString());

        setContentView(R.layout.tester_game3);
        setUpThreeClusterScene(gameState);

        final TextView tv_time_left = (TextView) findViewById(R.id.time_left_tv);
        new CountDownTimer(59000, 1000) {
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished / 1000 >= 10)    tv_time_left.setText("00:" + millisUntilFinished / 1000);
                else    tv_time_left.setText("00:0" + millisUntilFinished / 1000);

            }

            public void onFinish() {
                tv_time_left.setText("done!");
            }
        }.start();

        TextView tv_words_left = (TextView) findViewById(R.id.words_left_tv);
        tv_words_left.setText(Integer.toString(GameConstants.WORDS_NEEDED - gameState.getNum_dummy_words()));

        Button b_to_main = (Button) findViewById(R.id.to_main_b);
        b_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity();
            }
        });

    }

    public void setUpThreeClusterScene(GameState gameState){

        LinearLayout ll_c1 = (LinearLayout) findViewById(R.id.c1_ll);
        LinearLayout ll_c2 = (LinearLayout) findViewById(R.id.c2_ll);
        LinearLayout ll_c3 = (LinearLayout) findViewById(R.id.c3_ll);
        LinearLayout wordBank = (LinearLayout) findViewById(R.id.wordBank);

        InitSceneFuncs.display_buttons(TesterCluster3Activity.this, gameState, wordBank);
        InitSceneFuncs.embedDrawableBackgrounds(TesterCluster3Activity.this, gameState, ll_c1, ll_c2, ll_c3);

        Listener listener = new Listener(TesterCluster3Activity.this, gameState);

        wordBank.setOnDragListener(listener.createDrag());
        ll_c1.setOnDragListener(listener.createDrag());
        ll_c2.setOnDragListener(listener.createDrag());
        ll_c3.setOnDragListener(listener.createDrag());
    }

    public void startMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
