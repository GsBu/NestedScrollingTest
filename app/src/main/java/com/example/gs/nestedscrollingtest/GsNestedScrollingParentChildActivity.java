package com.example.gs.nestedscrollingtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GsNestedScrollingParentChildActivity extends AppCompatActivity {
    private final static String TAG = GsNestedScrollingParentChildActivity.class.getSimpleName();

    public static void showActivity(Activity rootActivity) {
        Intent intent = new Intent(rootActivity, GsNestedScrollingParentChildActivity.class);
        //Bundle bundle = new Bundle();
        //intent.putExtras(bundle);
        rootActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gs_nested_scrolling_parent_child);


    }
}
