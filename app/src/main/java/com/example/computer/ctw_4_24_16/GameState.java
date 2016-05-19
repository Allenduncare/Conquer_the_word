package com.example.computer.ctw_4_24_16;

import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Computer on 4/24/2016.
 */
public class GameState {

    private final String TAG = "GameState";

    private ArrayList<String> words_left_row1;
    private ArrayList<String> words_left_row2;
    private int rows_created = 0;

    private ArrayList<Integer> remaining_levels;

    private int score = 0;
    private int lives_left = GameConstants.INIT_LIVES;
    private int words_left = GameConstants.WORDS_NEEDED;

    private ParcelableScoreMap words_to_scores;

    private int activeClusters;
    private static String cluster1_name;
    private static String cluster2_name;
    private static String cluster3_name;
    private ArrayList<String> cluster1_words;
    private ArrayList<String> cluster2_words;
    private ArrayList<String> cluster3_words;
    private ArrayList<String> dummy_words;

    private ArrayList<String> words;//randomized words to send

    private Boolean playGame = false;


    public GameState(ArrayList<Integer> remaining_levels, String c1_name, String c2_name, String c3_name, ArrayList<String> cluster1_words_offered, ArrayList<String> cluster2_words_offered,
                         ArrayList<String> cluster3_words_offered, ArrayList<String> dummy_words_offered) {

        this.remaining_levels = remaining_levels;

        this.words_to_scores = new ParcelableScoreMap(remaining_levels.get(0));

        this.cluster1_name = c1_name;
        this.cluster2_name = c2_name;
        this.cluster3_name = c3_name;

        activeClusters = 0;
        if(cluster1_words_offered != null)    activeClusters++;
        if(cluster2_words_offered != null)    activeClusters++;
        if(cluster3_words_offered != null)    activeClusters++;

        choose_words(cluster1_words_offered, cluster2_words_offered, cluster3_words_offered, dummy_words_offered);
        initRows();

    }

    public void addRow(){
        int startIdx = rows_created * GameConstants.MAX_WORDS_PER_ROW;
        //words_left_row1 = words_left_row2;
        words_left_row2.clear();
        for(int i = startIdx; i < startIdx + GameConstants.MAX_WORDS_PER_ROW && i < words.size(); i++){
            //Utils_debug_awaheed.log_awaheed(TAG, "i: " + Integer.toString(i));
            words_left_row2.add(words.get(i));
        }
    }

    public void initRows(){
        words_left_row1 = new ArrayList<String>(GameConstants.MAX_WORDS_PER_ROW);
        words_left_row2 = new ArrayList<String>(GameConstants.MAX_WORDS_PER_ROW);

        for(int i = 0; i < GameConstants.MAX_WORDS_PER_ROW; i++){
            words_left_row1.add(words.get(i));
        }
        for(int i = GameConstants.MAX_WORDS_PER_ROW; i < 2 * GameConstants.MAX_WORDS_PER_ROW; i++){
            words_left_row2.add(words.get(i));
        }

        rows_created += 2;
    }

    public void choose_words(ArrayList<String> cluster1_words_offered, ArrayList<String> cluster2_words_offered,
                             ArrayList<String> cluster3_words_offered, ArrayList<String> dummy_words_offered){

        int word_cnt = 0;
        words = new ArrayList<String>();

        cluster1_words = new ArrayList<String>();
        for(int i = 0; (i < cluster1_words_offered.size()) && (i < GameConstants.WORDS_NEEDED / activeClusters); i++) {
            cluster1_words.add(cluster1_words_offered.get(i));
            words.add(cluster1_words_offered.get(i));
            word_cnt++;
        }

        if(cluster2_words_offered != null) {
            cluster2_words = new ArrayList<String>();
            for (int i = 0; (i < cluster2_words_offered.size()) && (i < GameConstants.WORDS_NEEDED / activeClusters); i++) {
                cluster2_words.add(cluster2_words_offered.get(i));
                words.add(cluster2_words_offered.get(i));
                word_cnt++;
            }
        }

        if(cluster3_words_offered != null) {
            cluster3_words = new ArrayList<String>();
            for (int i = 0; (i < cluster3_words_offered.size()) && (i < GameConstants.WORDS_NEEDED / activeClusters); i++) {
                cluster3_words.add(cluster3_words_offered.get(i));
                words.add(cluster3_words_offered.get(i));
                word_cnt++;
            }
        }

        int word_cnt_init = word_cnt;
        dummy_words = new ArrayList<String>();
        for(int i = 0; (i < dummy_words_offered.size()) && (i < GameConstants.WORDS_NEEDED - word_cnt_init); i++) {
            dummy_words.add(dummy_words_offered.get(i));
            words.add(dummy_words_offered.get(i));
            word_cnt++;
        }

        if(word_cnt != GameConstants.WORDS_NEEDED)    Log.e("AWAHEED " + TAG, "word_cnt != WORDS_NEEDED");

        Collections.shuffle(words);
    }

    public void update_rows(){
        if(words_left_row1.size() == 0) {
            for( String word : words_left_row2)
                words_left_row1.add(word);
        }
        words_left_row2.clear();
        addRow();
        rows_created++;
    }

    public void remove_from_rows(String word){
        if(words_left_row1.contains(word))    words_left_row1.remove(word);
        else    words_left_row2.remove(word);
    }

    public int getRows_created() {
        return rows_created;
    }

    public ArrayList<String> getWords_left_row2() {
        return words_left_row2;
    }

    public ArrayList<String> getWords_left_row1() {
        return words_left_row1;
    }

    public int getActiveClusters() {
        return activeClusters;
    }

    public ArrayList<String> getDummy_words() {
        return dummy_words;
    }

    public int getNum_dummy_words() {
        return dummy_words.size();
    }


    public void incScoreBy(int increment){  score += increment;  }
    //public void decScoreBy(int decrement){  score += decrement;  }

    public void takeAwayALife(){  lives_left--;  }
    public void takeAwayAWord(){  words_left--;  }

    public int get_curr_level_num(){
        return Utils_ctw_awaheed.TOTAL_WORKING_LEVELS - remaining_levels.size() + 1;
    }

    public int get_curr_level_id(){
        return remaining_levels.get(0);
    }

    public void remove_curr_level_id(){
        remaining_levels.remove(0);
    }

    public boolean levelWon(){
        return words_left == getNum_dummy_words();
    }
    public boolean isLastLevel(){
        return get_curr_level_num() == GameConstants.TOTAL_LEVELS;
    }
    public boolean levelLost(){
        return lives_left <= 0;
    }

    public void addOrUpdateWordScore(String word, boolean was_right){
        words_to_scores.addOrUpdateWordScore(word,was_right);
    }

    public ArrayList<Integer> getRemaining_levels() {
        return remaining_levels;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public int getScore() {
        return score;
    }

    public int getLives_left() {
        return lives_left;
    }

    public int getWords_left() {
        return words_left;
    }

    public String getTAG() {
        return TAG;
    }

    public static String getCluster1_name() {
        return cluster1_name;
    }

    public static String getCluster2_name() {
        return cluster2_name;
    }

    public static String getCluster3_name() {
        return cluster3_name;
    }

    public ArrayList<String> getCluster1_words() {
        return cluster1_words;
    }

    public ArrayList<String> getCluster2_words() {
        return cluster2_words;
    }

    public ArrayList<String> getCluster3_words() {
        return cluster3_words;
    }

    public ParcelableScoreMap getWords_to_scores() {
        return words_to_scores;
    }

    public Boolean getPlayGame() {
        return playGame;
    }

    public void setPlayGame(Boolean playGame) {
        this.playGame = playGame;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "TAG='" + TAG + '\'' +
                ", curr_level_num=" + get_curr_level_num() +
                ", curr_level_id=" + get_curr_level_id() +
                ", score=" + score +
                ", lives_left=" + lives_left +
                ", words_left=" + words_left +
                ", remaining_levels=" + remaining_levels +
                ", cluster1_name=" + cluster1_name +
                ", cluster2_name=" + cluster2_name +
                ", cluster3_name=" + cluster3_name +
                ", rows_created=" + rows_created +
                ", \nwords_left_row1=" + words_left_row1 +
                ", \nwords_left_row2=" + words_left_row2 +
                ", \nwords_to_scores=" + words_to_scores +
                ", \nactiveClusters=" + activeClusters +
                ", \ncluster1_words=" + cluster1_words +
                ", \ncluster2_words=" + cluster2_words +
                ", \ncluster3_words=" + cluster3_words +
                ", \ndummy_words=" + dummy_words +
                ", \nwords=" + words +
                '}';
    }
}
