package com.example.computer.ctw_4_24_16;

import java.util.ArrayList;

/**
 * Created by Computer on 5/4/2016.
 */
public class LevelCoordinator {

    private static final String TAG = "LevelCoordinator";

    public static GameState createGameState(ArrayList<Integer> remaining_levels){

        if( remaining_levels.get(0) == GameConstants.ONE_CLUSTER_LEVEL_ID) {
            return new GameState(remaining_levels, getC1Name(GameConstants.ONE_CLUSTER_LEVEL_ID), null, null,
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine( getC1Words(GameConstants.ONE_CLUSTER_LEVEL_ID) ), null, null,
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine( Utils_ctw_awaheed.DUMMY_BY_NEWLINE )
            );
        }
        else if( remaining_levels.get(0) == GameConstants.THREE_CLUSTER_LEVEL_ID) {
            return new GameState(remaining_levels, getC1Name(remaining_levels.get(0)),
                    getC2Name(remaining_levels.get(0)), getC3Name(),
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine( getC1Words(remaining_levels.get(0)) ),
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine( getC2Words(remaining_levels.get(0)) ),
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine( getC3Words() ),
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine( Utils_ctw_awaheed.DUMMY_BY_NEWLINE )
            );
        }
        else {
            return new GameState(remaining_levels, getC1Name(remaining_levels.get(0)),
                    getC2Name(remaining_levels.get(0)), null,
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine(getC1Words(remaining_levels.get(0))),
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine(getC2Words(remaining_levels.get(0))),
                    null,
                    Utils_string_manip_awaheed.stringTo1DArrayNewLine(Utils_ctw_awaheed.DUMMY_BY_NEWLINE)
            );
        }

    }

    public static String getC1Words(int level_id){
        switch (level_id){
            case 1: return Utils_ctw_awaheed.GOOD_WORDS_BY_NEWLINE;
            case 2: return Utils_ctw_awaheed.OLD_WORDS_BY_NEWLINE;
            case 3: return Utils_ctw_awaheed.HIGH_MIGHTY_WORDS_BY_NEWLINE;
            case 4: return Utils_ctw_awaheed.FIGHTING_WORDS_BY_NEWLINE;
            case 5: return Utils_ctw_awaheed.TALK_A_LOT_WORDS_BY_NEWLINE;
            case GameConstants.THREE_CLUSTER_LEVEL_ID: return Utils_ctw_awaheed.BEGINNING_WORDS_BY_NEWLINE;
            case GameConstants.ONE_CLUSTER_LEVEL_ID: return Utils_ctw_awaheed.MARTIAL_ARTS_WORDS_BY_NEWLINE;
            default:
                Utils_debug_awaheed.log_awaheed(TAG, "invalid level id for getLeftWords");
                break;
        }
        return "";
    }

    public static String getC2Words(int level_id){
        switch (level_id){
            case 1: return Utils_ctw_awaheed.EVIL_WORDS_BY_NEWLINE;
            case 2: return Utils_ctw_awaheed.NEW_WORDS_BY_NEWLINE;
            case 3: return Utils_ctw_awaheed.HUMBLE_SERVING_WORDS_BY_NEWLINE;
            case 4: return Utils_ctw_awaheed.HARMONIUS_WORDS_BY_NEWLINE;
            case 5: return Utils_ctw_awaheed.TALK_A_LITTLE_WORDS_BY_NEWLINE;
            case GameConstants.THREE_CLUSTER_LEVEL_ID: return Utils_ctw_awaheed.MIDDLE_WORDS_BY_NEWLINE;
            default:
                Utils_debug_awaheed.log_awaheed(TAG, "invalid level id for getLeftWords");
                break;
        }
        return "";
    }

    public static String getC3Words(){
        return Utils_ctw_awaheed.END_WORDS_BY_NEWLINE;
    }

    public static String getIdToWords(int level_id){
        switch (level_id){
            case 1: return Utils_ctw_awaheed.id_and_word_good_evil;
            case 2: return Utils_ctw_awaheed.id_and_word_old_new;
            case 3: return Utils_ctw_awaheed.id_and_word_high_humble;
            case 4: return Utils_ctw_awaheed.id_and_word_fighting_harmonius;
            case 5: return Utils_ctw_awaheed.id_and_word_talk_a_lot_little;
            case GameConstants.THREE_CLUSTER_LEVEL_ID: return Utils_ctw_awaheed.id_and_word_beginning_middle_end;
            case GameConstants.ONE_CLUSTER_LEVEL_ID: return Utils_ctw_awaheed.id_and_word_martial_arts;
            default:
                Utils_debug_awaheed.log_awaheed(TAG, "invalid level id for getLeftWords");
                break;
        }
        return "";
    }

    public static String getC1Name(int level_id){
        switch (level_id){
            case 1: return "good";
            case 2: return "old";
            case 3: return "high_and_mighty";
            case 4: return "fighting";
            case 5: return "talk_a_lot";
            case GameConstants.THREE_CLUSTER_LEVEL_ID: return "beginning";
            case GameConstants.ONE_CLUSTER_LEVEL_ID: return "martial_arts";
            default:
                Utils_debug_awaheed.log_awaheed(TAG, "invalid level id for getLeftWords");
                break;
        }
        return "";
    }

    public static String getC2Name(int level_id){
        switch (level_id){
            case 1: return "evil";
            case 2: return "new";
            case 3: return "humble_and_serving";
            case 4: return "harmonius";
            case 5: return "talk_a_little";
            case GameConstants.THREE_CLUSTER_LEVEL_ID: return "middle";
            default:
                Utils_debug_awaheed.log_awaheed(TAG, "invalid level id for getLeftWords");
                break;
        }
        return "";
    }

    public static String getC3Name(){
        return "end";
    }
}
