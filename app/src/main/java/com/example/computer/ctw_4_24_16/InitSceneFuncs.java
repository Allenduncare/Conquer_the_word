package com.example.computer.ctw_4_24_16;

import android.content.Context;
import android.util.MutableInt;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Computer on 4/24/2016.
 */
public class InitSceneFuncs {

    private static final String TAG = "InitSceneFuncs";

    public static void display_buttons(Context context, GameState gameState, LinearLayout linearLayout){
        //MutableInt word_cnt = new MutableInt(0);

        Listener listener = new Listener(context, gameState);

        //int terminate = GameConstants.WORDS_NEEDED / GameConstants.MAX_WORDS_PER_ROW;
        //if( GameConstants.WORDS_NEEDED % GameConstants.MAX_WORDS_PER_ROW != 0 )    terminate++;

        create_init_rows(context, gameState, listener, linearLayout);


    }


    public static void add_invisible_dummy_row( Context context, GameState gameState, LinearLayout linearLayout){

        LinearLayout innerLinLayout = new LinearLayout(context);
        innerLinLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(innerLinLayout);

        Button button = new Button(context);
        button.setText("Dummy");
        button.setVisibility(View.INVISIBLE);
        innerLinLayout.addView(button);


    }

    public static void add_row( Context context, GameState gameState, Listener listener, LinearLayout linearLayout){
        create_row2(context, gameState, listener, linearLayout);
    }

    public static void create_init_rows( Context context, GameState gameState, Listener listener, LinearLayout linearLayout){
        create_row1(context, gameState, listener, linearLayout);
        create_row2(context, gameState, listener, linearLayout);
    }

    public static void create_row1( Context context, GameState gameState, Listener listener, LinearLayout linearLayout){

        LinearLayout innerLinLayout = new LinearLayout(context);
        innerLinLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(innerLinLayout);

        for(String word : gameState.getWords_left_row1()){
            Button button = new Button(context);
            button.setText(word);
            button.setOnTouchListener(listener.createTouch());
            innerLinLayout.addView(button);
        }

    }
    public static void create_row2( Context context, GameState gameState, Listener listener, LinearLayout linearLayout){

        LinearLayout innerLinLayout = new LinearLayout(context);
        innerLinLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(innerLinLayout);

        for(String word : gameState.getWords_left_row2()){
            Button button = new Button(context);
            button.setText(word);
            button.setOnTouchListener(listener.createTouch());
            innerLinLayout.addView(button);
        }

    }

    public static void embedDrawableBackgrounds(Context context, GameState gameState, LinearLayout linearLayout1){
        linearLayout1.setBackground(context.getResources().getDrawable(
                (context.getResources().getIdentifier("cluster_" + gameState.getCluster1_name(), "drawable", context.getPackageName())   )));
    }

    public static void embedDrawableBackgrounds(Context context, GameState gameState, LinearLayout linearLayout1, LinearLayout linearLayout2){

        linearLayout1.setBackground(context.getResources().getDrawable(
                (context.getResources().getIdentifier("cluster_" + gameState.getCluster1_name(), "drawable", context.getPackageName())   )));

        linearLayout2.setBackground(context.getResources().getDrawable(
                (context.getResources().getIdentifier("cluster_" + gameState.getCluster2_name(), "drawable", context.getPackageName())   )));

    }

    public static void embedDrawableBackgrounds(Context context, GameState gameState, LinearLayout linearLayout1, LinearLayout linearLayout2, LinearLayout linearLayout3){

        linearLayout1.setBackground(context.getResources().getDrawable(
                (context.getResources().getIdentifier("cluster_" + gameState.getCluster1_name(), "drawable", context.getPackageName())   )));

        linearLayout2.setBackground(context.getResources().getDrawable(
                (context.getResources().getIdentifier("cluster_" + gameState.getCluster2_name(), "drawable", context.getPackageName())   )));

        linearLayout3.setBackground(context.getResources().getDrawable(
                (context.getResources().getIdentifier("cluster_" + gameState.getCluster3_name(), "drawable", context.getPackageName())   )));

    }

    public static void embedDrawableBackgroundByName(Context context, LinearLayout linearLayout, String imageTitle){

        linearLayout.setBackground(context.getResources().getDrawable(
                (context.getResources().getIdentifier(imageTitle, "drawable", context.getPackageName())   )));

    }

}
