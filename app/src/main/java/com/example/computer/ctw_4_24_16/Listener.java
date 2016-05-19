package com.example.computer.ctw_4_24_16;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Computer on 4/24/2016.
 */
public class Listener {

    Context context;
    GameState gameState;

    enum Decision { WAS_RIGHT_AND_DUMMY, WAS_RIGHT_AND_NOT_DUMMY, WAS_WRONG, ERROR };

    private final String TAG = "Listener";
    Listener(Context context, GameState gameState){
        this.context = context;
        this.gameState = gameState;
    }

    public View.OnTouchListener createTouch() {
        return new Touch();
    }

    public View.OnDragListener createDrag() {
        return new Dragger();

    }

    private final class Touch implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class Dragger implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:

                    Button view_b = (Button) event.getLocalState();
                    String word_dragged = (String) view_b.getText();

                    LinearLayout wordbank = (LinearLayout) view_b.getParent();

                    Decision decision = determine_outcome(view_b, v, gameState.get_curr_level_id());

                    if(decision == Decision.ERROR) {
                        view_b.setVisibility(View.VISIBLE);
                        break;
                    }

                    if(decision == Decision.WAS_RIGHT_AND_NOT_DUMMY || decision == Decision.WAS_RIGHT_AND_DUMMY){
                        wordbank.removeView(view_b);

                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT ).show();

                        gameState.remove_from_rows(word_dragged);
                        Utils_debug_awaheed.log_awaheed(TAG, "after remove");
                        //Utils_debug_awaheed.log_awaheed(TAG, "gamestate after remove from rows: " + gameState.toString());

                        if(decision == Decision.WAS_RIGHT_AND_NOT_DUMMY){

                            gameState.incScoreBy(5);
                            gameState.takeAwayAWord();

                            setScore();
                        }
                    }
                    else {

                        Toast.makeText(context, "Incorrect", Toast.LENGTH_SHORT).show();

                        view_b.setVisibility(View.VISIBLE);

                        gameState.takeAwayALife();
                        setLives();
                    }

                    Utils_debug_awaheed.log_awaheed(TAG, "row1: " + gameState.getWords_left_row1());
                    Utils_debug_awaheed.log_awaheed(TAG, "row2: " + gameState.getWords_left_row2());
                    if(gameState.getWords_left_row1().size() == 0 || gameState.getWords_left_row2().size() == 0){
                        if((gameState.getRows_created() - 1) * GameConstants.MAX_WORDS_PER_ROW < GameConstants.WORDS_NEEDED){
                            gameState.update_rows();
                            InitSceneFuncs.add_row(context, gameState, Listener.this, wordbank);
                            if( gameState.getWords_left_row1().size() == 0 || gameState.getWords_left_row2().size() == 0 ){
                                //create invisible button
                                InitSceneFuncs.add_invisible_dummy_row(context, gameState, (LinearLayout) wordbank.getParent());
                            }
                        }
                    }
                    Utils_debug_awaheed.log_awaheed(TAG, "row1: " + gameState.getWords_left_row1());
                    Utils_debug_awaheed.log_awaheed(TAG, "row2: " + gameState.getWords_left_row2());

                    setWordsLeft();
                    //Utils_debug_awaheed.log_awaheed(TAG, word_dragged + (was_right ? " true" : " false"));

                    if(decision != Decision.WAS_RIGHT_AND_DUMMY) {
                        gameState.addOrUpdateWordScore(word_dragged, (decision == Decision.WAS_WRONG) ? false : true);
                    }

                    if(gameState.getLives_left() <= 0){
                        Intent i = new Intent(context, IntermediateDisplayActivity.class);
                        i.putExtra(GameConstants.EXTRA_ARRAY_REMAINING_LEVELS, gameState.getRemaining_levels());
                        i.putExtra(GameConstants.EXTRA_LEVEL_WON_BOOL, false);
                        i.putExtra(GameConstants.EXTRA_LEVEL_LOST_BECAUSE_TIME_BOOL, false);
                        i.putExtra(GameConstants.EXTRA_SCORE_MAP_PARCELABLE, gameState.getWords_to_scores());
                        context.startActivity(i);
                        gameState.setPlayGame(false);
                    }
                    else if(gameState.getWords_left() <= gameState.getNum_dummy_words()){
                        Intent i = new Intent(context, IntermediateDisplayActivity.class);
                        i.putExtra(GameConstants.EXTRA_ARRAY_REMAINING_LEVELS, gameState.getRemaining_levels());
                        i.putExtra(GameConstants.EXTRA_LEVEL_WON_BOOL, true);
                        i.putExtra(GameConstants.EXTRA_SCORE_MAP_PARCELABLE, gameState.getWords_to_scores());
                        context.startActivity(i);
                        gameState.setPlayGame(false);
                    }
                    Utils_debug_awaheed.log_awaheed(TAG, "here5");

                    Utils_debug_awaheed.log_awaheed(TAG, "end of listener decision: "+ decision);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                default:
                    break;
            }
            return true;
        }

        public void setScore(){

            TextView tv_score;
            if(gameState.get_curr_level_id() == GameConstants.THREE_CLUSTER_LEVEL_ID){
                tv_score = (TextView)((TesterCluster3Activity)context).findViewById(R.id.score_tv);
            }
            else {
                tv_score = (TextView)((Game2Activity)context).findViewById(R.id.score_tv);
            }
            tv_score.setText(Integer.toString(gameState.getScore()));

        }
        public void setLives(){

            TextView tv_lives_left;
            if(gameState.get_curr_level_id() == GameConstants.THREE_CLUSTER_LEVEL_ID){
                tv_lives_left = (TextView)((TesterCluster3Activity)context).findViewById(R.id.lives_left_tv);
            }
            else {
                tv_lives_left = (TextView)((Game2Activity)context).findViewById(R.id.lives_left_tv);
            }

            tv_lives_left.setText(Integer.toString(gameState.getLives_left()));
        }

        public void setWordsLeft(){

            TextView tv_words_left;

            if(gameState.get_curr_level_id() == GameConstants.THREE_CLUSTER_LEVEL_ID){
                tv_words_left = (TextView)((TesterCluster3Activity)context).findViewById(R.id.words_left_tv);
            }
            else {
                tv_words_left = (TextView)((Game2Activity)context).findViewById(R.id.words_left_tv);
            }

            tv_words_left.setText(Integer.toString(gameState.getWords_left() - gameState.getNum_dummy_words()));

        }

        public Decision determine_outcome(Button view_b, View v, int curr_level_id){

            boolean was_wrong = false;

            if(v.getId() == R.id.c1_ll){
                if( DecisionMakingFuncs.is_correct(gameState, gameState.getCluster1_name(), view_b.getText().toString()) ){
                    return Decision.WAS_RIGHT_AND_NOT_DUMMY;
                }
                else    was_wrong = true;

            }
            if(curr_level_id != GameConstants.ONE_CLUSTER_LEVEL_ID && v.getId() == R.id.c2_ll){
                if( DecisionMakingFuncs.is_correct(gameState, gameState.getCluster2_name(), view_b.getText().toString()) ){
                    return Decision.WAS_RIGHT_AND_NOT_DUMMY;
                }
                else    was_wrong = true;
            }
            if(curr_level_id == GameConstants.THREE_CLUSTER_LEVEL_ID && v.getId() == R.id.c3_ll){
                if( DecisionMakingFuncs.is_correct(gameState, gameState.getCluster3_name(), view_b.getText().toString()) ){
                    return Decision.WAS_RIGHT_AND_NOT_DUMMY;
                }
                else    was_wrong = true;
            }
            if(v.getId() == R.id.trash_ll){
                if( DecisionMakingFuncs.is_dummy_word( gameState, view_b.getText().toString())) {
                    return Decision.WAS_RIGHT_AND_DUMMY;
                }
                else    was_wrong = true;
            }
            if(was_wrong)    return Decision.WAS_WRONG;
            else {
                Log.d("AWAHEED", "Id was neither right nor wrong");
                return Decision.ERROR;
            }

        }
    }
}
