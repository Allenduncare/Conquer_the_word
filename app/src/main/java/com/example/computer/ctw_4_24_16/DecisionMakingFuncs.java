package com.example.computer.ctw_4_24_16;

import android.util.Log;

/**
 * Created by Computer on 4/24/2016.
 */
public class DecisionMakingFuncs {

    public static boolean in_cluster1(GameState gameState, String word_to_match){
        return gameState.getCluster1_words().contains(word_to_match);
    }
    public static boolean in_cluster2(GameState gameState, String word_to_match){
        return gameState.getCluster2_words().contains(word_to_match);
    }
    public static boolean in_cluster3(GameState gameState, String word_to_match){
        return gameState.getCluster3_words().contains(word_to_match);
    }
    public static boolean is_dummy_word(GameState gameState, String word_to_match){
        return gameState.getDummy_words().contains(word_to_match);
    }

    public static boolean is_correct(GameState gameState, String cluster_name_of_region, String word){
        if(cluster_name_of_region == gameState.getCluster1_name()){
            return in_cluster1(gameState, word);
        }
        else if(cluster_name_of_region == gameState.getCluster2_name()){
            return in_cluster2(gameState, word);
        }
        else if(cluster_name_of_region == gameState.getCluster3_name()){
            return in_cluster3(gameState, word);
        }
        else    Log.e("AWAHEED", "cluster_name_of_region does not match any cluster");
        return false;
    }


}
