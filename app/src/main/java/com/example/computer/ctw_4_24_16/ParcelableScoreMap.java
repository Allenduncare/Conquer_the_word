package com.example.computer.ctw_4_24_16;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by administrator on 4/29/16.
 */
public class ParcelableScoreMap implements Parcelable {


    private static Map<String, Integer> words_to_ids;

    //if 1, never wrong
    //if neg #, shows how many times was wrong before right (if ever right)
    private Map<String, Integer> map_words_to_scores = new HashMap<String,Integer>();


    public void addOrUpdateWordScore(String word, boolean was_right){
        if(!words_to_ids.containsKey(word))    return;
        if( map_words_to_scores.containsKey(word) && !was_right ){
            map_words_to_scores.put(word, map_words_to_scores.get(word) - 1);
        }
        else if(!map_words_to_scores.containsKey(word))    map_words_to_scores.put(word, was_right ? 1 : -1);
        //if it contains the word (meaning got it wrong in the past) and it is right now, do nothing
    }


    public static final Parcelable.Creator<ParcelableScoreMap> CREATOR = new Parcelable.Creator<ParcelableScoreMap>() {
        public ParcelableScoreMap createFromParcel(Parcel p) {
            return new ParcelableScoreMap(p);
        }

        public ParcelableScoreMap[] newArray(int size) {
            return new ParcelableScoreMap[size];
        }
    };

    public ParcelableScoreMap(Parcel p) {

        int size_map = p.readInt();

        for(int i = 0; i < size_map; i++){
            String word_key = p.readString();
            int score_value = p.readInt();
            map_words_to_scores.put(word_key, score_value);
        }

    }

    public ParcelableScoreMap(int level_id){
        words_to_ids = Utils_string_manip_awaheed.strToMapStrIntWithTabNewline(
                LevelCoordinator.getIdToWords(level_id) );
    }

    public ParcelableScoreMap(Map<String, Integer> words_to_scores) {
        this.map_words_to_scores = words_to_scores;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(map_words_to_scores.size());

        for( Map.Entry<String, Integer> entry : map_words_to_scores.entrySet()){
            dest.writeString(entry.getKey());
            dest.writeInt(entry.getValue());
        }

    }

    @Override
    public int describeContents() { return 0; }

    public Map<String, Integer> getMap_words_to_scores() {
        return map_words_to_scores;
    }

    @Override
    public String toString() {
        return "ParcelableScoreMap{" +
                "words_to_scores=" + map_words_to_scores +
                '}';
    }
}
