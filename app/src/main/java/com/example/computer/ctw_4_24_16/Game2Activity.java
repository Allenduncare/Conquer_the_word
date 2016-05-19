package com.example.computer.ctw_4_24_16;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Computer on 4/24/2016.
 */
public class Game2Activity extends Activity {

    private static final String TAG = "Game2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils_debug_awaheed.log_awaheed(TAG, "---- Run 5 ----");

        ArrayList<Integer> remaining_levels;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                remaining_levels = createLevels();
                //remaining_levels = new ArrayList<Integer>(2);
                //remaining_levels.add(6);
                //remaining_levels.add(2);
                Utils_debug_awaheed.log_awaheed(TAG, "first level: " + remaining_levels.toString());
            }
            else {
                remaining_levels = extras.getIntegerArrayList(GameConstants.EXTRA_ARRAY_REMAINING_LEVELS);
                Utils_debug_awaheed.log_awaheed(TAG, "in getting extras: " + remaining_levels.toString());

            }
        }
        else {
            remaining_levels = (ArrayList<Integer>) savedInstanceState.getSerializable(GameConstants.EXTRA_ARRAY_REMAINING_LEVELS);
            Utils_debug_awaheed.log_awaheed(TAG, "in has saved instance: " + remaining_levels.toString());

        }


        final GameState gameState = LevelCoordinator.createGameState(remaining_levels);
        gameState.setPlayGame(true);

        Utils_debug_awaheed.log_awaheed(TAG, "here1");
        Utils_debug_awaheed.log_awaheed(TAG, "gamestate: " + gameState.toString());

        if( remaining_levels.get(0) == GameConstants.ONE_CLUSTER_LEVEL_ID) {
            setContentView(R.layout.game1);
            setUpOneClusterScene(gameState);
        }
        else if( remaining_levels.get(0) == GameConstants.THREE_CLUSTER_LEVEL_ID) {
            setContentView(R.layout.game3);
            setUpThreeClusterScene(gameState);
        }
        else {
            setContentView(R.layout.game2);
            setUpTwoClusterScene(gameState);
        }

        Utils_debug_awaheed.log_awaheed(TAG, "here2");

        final TextView tv_time_left = (TextView) findViewById(R.id.time_left_tv);
        new CountDownTimer(59000, 1000) {
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished / 1000 >= 10)    tv_time_left.setText("00:" + millisUntilFinished / 1000);
                else    tv_time_left.setText("00:0" + millisUntilFinished / 1000);

            }

            public void onFinish() {
                //tv_time_left.setText("done!");
                if(gameState.getPlayGame()){
                    Intent i = new Intent(Game2Activity.this, IntermediateDisplayActivity.class);
                    i.putExtra(GameConstants.EXTRA_ARRAY_REMAINING_LEVELS, gameState.getRemaining_levels());
                    i.putExtra(GameConstants.EXTRA_LEVEL_WON_BOOL, false);
                    i.putExtra(GameConstants.EXTRA_LEVEL_LOST_BECAUSE_TIME_BOOL, true);
                    Utils_debug_awaheed.log_awaheed(TAG, "map sent: " + gameState.getWords_to_scores().toString());
                    i.putExtra(GameConstants.EXTRA_SCORE_MAP_PARCELABLE, gameState.getWords_to_scores());
                    startActivity(i);
                    gameState.setPlayGame(false);
                }
            }
        }.start();


        Utils_debug_awaheed.log_awaheed(TAG, "here3");

        TextView tv_words_left = (TextView) findViewById(R.id.words_left_tv);
        tv_words_left.setText(Integer.toString(GameConstants.WORDS_NEEDED - gameState.getNum_dummy_words()));


        Utils_debug_awaheed.log_awaheed(TAG, "here4");

    }

    public ArrayList<Integer> createLevels(){
        ArrayList<Integer> result = new ArrayList<Integer>(Utils_ctw_awaheed.TOTAL_WORKING_LEVELS);
        for (int i = 1; i < Utils_ctw_awaheed.TOTAL_WORKING_LEVELS + 1; i++) {
            result.add(i);
        }
        Collections.shuffle(result);
        return result;
    }

    public void setUpTwoClusterScene(GameState gameState){

        LinearLayout ll_c1 = (LinearLayout) findViewById(R.id.c1_ll);
        LinearLayout ll_c2 = (LinearLayout) findViewById(R.id.c2_ll);
        LinearLayout wordBank = (LinearLayout) findViewById(R.id.wordBank);
        LinearLayout ll_trash = (LinearLayout) findViewById(R.id.trash_ll);

        InitSceneFuncs.display_buttons(Game2Activity.this, gameState, wordBank);
        InitSceneFuncs.embedDrawableBackgrounds(Game2Activity.this, gameState, ll_c1, ll_c2);

        Listener listener = new Listener(Game2Activity.this, gameState);

        wordBank.setOnDragListener(listener.createDrag());
        ll_c1.setOnDragListener(listener.createDrag());
        ll_c2.setOnDragListener(listener.createDrag());
        ll_trash.setOnDragListener(listener.createDrag());

    }

    public void setUpThreeClusterScene(GameState gameState){

        LinearLayout ll_c1 = (LinearLayout) findViewById(R.id.c1_ll);
        LinearLayout ll_c2 = (LinearLayout) findViewById(R.id.c2_ll);
        LinearLayout ll_c3 = (LinearLayout) findViewById(R.id.c3_ll);
        LinearLayout wordBank = (LinearLayout) findViewById(R.id.wordBank);
        LinearLayout ll_trash = (LinearLayout) findViewById(R.id.trash_ll);


        InitSceneFuncs.display_buttons(Game2Activity.this, gameState, wordBank);
        InitSceneFuncs.embedDrawableBackgrounds(Game2Activity.this, gameState, ll_c1, ll_c2, ll_c3);

        Listener listener = new Listener(Game2Activity.this, gameState);

        wordBank.setOnDragListener(listener.createDrag());
        ll_c1.setOnDragListener(listener.createDrag());
        ll_c2.setOnDragListener(listener.createDrag());
        ll_c3.setOnDragListener(listener.createDrag());
        ll_trash.setOnDragListener(listener.createDrag());

    }

    public void setUpOneClusterScene(GameState gameState){

        LinearLayout ll_c1 = (LinearLayout) findViewById(R.id.c1_ll);
        LinearLayout wordBank = (LinearLayout) findViewById(R.id.wordBank);
        LinearLayout ll_trash = (LinearLayout) findViewById(R.id.trash_ll);

        InitSceneFuncs.display_buttons(Game2Activity.this, gameState, wordBank);
        InitSceneFuncs.embedDrawableBackgrounds(Game2Activity.this, gameState, ll_c1);

        Listener listener = new Listener(Game2Activity.this, gameState);

        wordBank.setOnDragListener(listener.createDrag());
        ll_c1.setOnDragListener(listener.createDrag());
        ll_trash.setOnDragListener(listener.createDrag());

    }

}
