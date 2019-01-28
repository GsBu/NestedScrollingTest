package com.example.gs.nestedscrollingtest.activity.coordinatorlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.gs.nestedscrollingtest.R;
import com.example.gs.nestedscrollingtest.activity.util.DisplayUtil;

public class CoordinatorActivity extends AppCompatActivity {

    private TextView tv1;

    public static void showActivity(Activity rootActivity) {
        Intent intent = new Intent(rootActivity, CoordinatorActivity.class);
        //Bundle bundle = new Bundle();
        //intent.putExtras(bundle);
        rootActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        tv1 = findViewById(R.id.tv_1);
        tv1.setY(500 + DisplayUtil.dip2px(this, 40));
    }
}
