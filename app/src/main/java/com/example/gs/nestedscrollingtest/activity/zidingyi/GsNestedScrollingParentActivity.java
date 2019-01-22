package com.example.gs.nestedscrollingtest.activity.zidingyi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.gs.nestedscrollingtest.R;

public class GsNestedScrollingParentActivity extends AppCompatActivity {

    public static void showActivity(Activity rootActivity) {
        Intent intent = new Intent(rootActivity, GsNestedScrollingParentActivity.class);
        //Bundle bundle = new Bundle();
        //intent.putExtras(bundle);
        rootActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gs_nested_scrolling_parent);
    }
}
