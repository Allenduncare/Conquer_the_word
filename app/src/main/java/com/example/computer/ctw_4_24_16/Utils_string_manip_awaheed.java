package com.example.computer.ctw_4_24_16;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Computer on 4/17/2016.
 */
public class Utils_string_manip_awaheed {

    public static String intToString(int integer){
        return Integer.toString(integer);
    }

    public static int stringToInt(String str){
        return Integer.getInteger(str);
    }

    public static ArrayList<String> stringTo1DArraySpace(String str){

        ArrayList<String> result = new ArrayList<String>();

        Collections.addAll(result, str.split("\\s+"));
        return result;
    }

    public static ArrayList<String> stringTo1DArrayNewLine(String str){

        ArrayList<String> result = new ArrayList<String>();

        Collections.addAll(result, str.split("\\n"));
        return result;
    }

    public static ArrayList<String> stringTo1DArrayTab(String str){

        ArrayList<String> result = new ArrayList<String>();

        Collections.addAll(result, str.split("\\t"));
        return result;
    }

    public static Map<String, Integer> strToMapStrIntWithTabNewline(String str){

        ArrayList<String> sepByNewline = stringTo1DArrayNewLine(str);
        ArrayList<String> temp;
        Map<String, Integer> result = new HashMap<String, Integer>();

        for(int i = 0; i < sepByNewline.size(); i++){
            temp = stringTo1DArrayTab(sepByNewline.get(i));
            result.put(temp.get(1), Integer.parseInt(temp.get(0))); //reverses order
        }
        return result;
    }

}
