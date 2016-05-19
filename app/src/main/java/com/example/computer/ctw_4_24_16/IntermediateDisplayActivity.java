package com.example.computer.ctw_4_24_16;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by administrator on 4/29/16.
 */
public class IntermediateDisplayActivity extends Activity {

    private static final String TAG = "IntermediateDisplayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils_debug_awaheed.log_awaheed(TAG, "in intermedDisp");
        setContentView(R.layout.intermediate_display);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken != null)
        {final GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            String name= object.getString("first_name");
                            System.out.print(name);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
        ShareButton shareButton = (ShareButton)findViewById(R.id.shareButton);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("google.com"))
                .setContentDescription("Player has scored 100 right! Play today!")
                .build();
        shareButton.setShareContent(content);}
        final ArrayList<Integer> remaining_levels;

        Boolean won = true;
        Boolean lost_because_of_time = true;
        Map<String, Integer> map_words_to_scores;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                Utils_debug_awaheed.log_awaheed(TAG+"ERROR", "can't get necessary extras");
                remaining_levels = new ArrayList<Integer>();
                map_words_to_scores = new HashMap<String, Integer>();
            }
            else {
                remaining_levels = extras.getIntegerArrayList(GameConstants.EXTRA_ARRAY_REMAINING_LEVELS);
                won = extras.getBoolean(GameConstants.EXTRA_LEVEL_WON_BOOL);
                map_words_to_scores = ( (ParcelableScoreMap) extras.getParcelable(GameConstants.EXTRA_SCORE_MAP_PARCELABLE) ).getMap_words_to_scores();
                if(!won)    lost_because_of_time = extras.getBoolean(GameConstants.EXTRA_LEVEL_LOST_BECAUSE_TIME_BOOL);

                Utils_debug_awaheed.log_awaheed(TAG, "in getting extras: " + remaining_levels.toString());
            }
        }
        else {
            remaining_levels = (ArrayList<Integer>) savedInstanceState.getSerializable(GameConstants.EXTRA_ARRAY_REMAINING_LEVELS);
            won = (Boolean) savedInstanceState.getSerializable(GameConstants.EXTRA_LEVEL_WON_BOOL);
            map_words_to_scores = ( (ParcelableScoreMap) savedInstanceState.getSerializable(GameConstants.EXTRA_SCORE_MAP_PARCELABLE) ).getMap_words_to_scores();
            if(!won)    lost_because_of_time = (Boolean) savedInstanceState.getSerializable(GameConstants.EXTRA_LEVEL_LOST_BECAUSE_TIME_BOOL);

            Utils_debug_awaheed.log_awaheed(TAG, "in has saved instance: " + remaining_levels.toString());

        }

        Utils_debug_awaheed.log_awaheed(TAG, "in intermedDisp2");

        TextView tv_heading_itm_disp = (TextView) findViewById(R.id.heading_itm_disp_tv);
        ScrollView sv_display_itm_stats = (ScrollView) findViewById(R.id.display_itm_stats_sv);
        Button b1 = (Button) findViewById(R.id.intDisp_b1);
        Button b2 = (Button) findViewById(R.id.intDisp_b2);

        if(won) {

            tv_heading_itm_disp.setText("Congratulation! Level Passed");
            if(remaining_levels.size() > 1) {
                b1.setText("Next Level");
                b2.setText("Quit");

                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        remaining_levels.remove(0);
                        goToGame2Activity(remaining_levels);
                    }
                });
            }
            else {

                b1.setText("See Stats");
                b2.setText("Main Menu");

            }

        }
        else {

            if(lost_because_of_time)    tv_heading_itm_disp.setText("Oh No! Ran Out of Time");
            else    tv_heading_itm_disp.setText("Oh No! Too Many Mistakes");

            b1.setText("Replay Level");
            b2.setText("Main Menu");

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToGame2Activity(remaining_levels);
                }
            });
        }

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        Utils_debug_awaheed.log_awaheed(TAG, "after onclicklistener");
        TableLayout tl_display_stats = new TableLayout(IntermediateDisplayActivity.this);
        Utils_debug_awaheed.log_awaheed(TAG, "after table layout creation");
        //tl_display_stats.setStretchAllColumns(true);

        Utils_debug_awaheed.log_awaheed(TAG, "map: " + map_words_to_scores.toString());

        if(map_words_to_scores.size() > 0) {
            Utils_debug_awaheed.log_awaheed(TAG, "in size > 0");

            for (Map.Entry<String, Integer> entry : map_words_to_scores.entrySet()) {
                TableRow tr = new TableRow(IntermediateDisplayActivity.this);

                TextView tv_word = new TextView(IntermediateDisplayActivity.this);
                TextView tv_padding = new TextView(IntermediateDisplayActivity.this);
                TextView tv_score = new TextView(IntermediateDisplayActivity.this);

                tv_word.setText(entry.getKey());
                tv_padding.setWidth(20);
                tv_score.setText(Integer.toString(entry.getValue()));

                tr.addView(tv_word);
                tr.addView(tv_padding);
                tr.addView(tv_score);

                tl_display_stats.addView(tr);
            }

            sv_display_itm_stats.addView(tl_display_stats);

        }
        else {
            TextView textView = new TextView(IntermediateDisplayActivity.this);
            textView.setText("No stats to show for this level.");

            sv_display_itm_stats.addView(textView);
        }
    }

    private void goToMainActivity(){
        Intent i = new Intent(IntermediateDisplayActivity.this, MainActivity.class);
        startActivity(i);
    }

    private void goToGame2Activity(ArrayList<Integer> remaining_levels){
        Intent i = new Intent(IntermediateDisplayActivity.this, Game2Activity.class);
        i.putExtra(GameConstants.EXTRA_ARRAY_REMAINING_LEVELS, remaining_levels);
        startActivity(i);
    }
}

