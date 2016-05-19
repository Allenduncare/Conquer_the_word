package com.example.computer.ctw_4_24_16;

import android.util.Log;

/**
 * Created by Computer on 4/24/2016.
 */
public class Utils_debug_awaheed {

    private static final String IDENTIFIER_TAG = "AWAHEED";

    public static void log_awaheed(String TAG, String msg){
        Log.d(IDENTIFIER_TAG + " " + TAG, msg);
    }

    public static void log_string(String string){
        Log.d("AWAHEED log_string:", string);
    }


}
