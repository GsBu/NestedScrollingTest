package com.example.gs.nestedscrollingtest.activity.zidingyi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gs.nestedscrollingtest.R;
import com.example.gs.nestedscrollingtest.adapter.GsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class GsNestedScrollingParentChildRecycleViewActivity extends AppCompatActivity {

    private RecyclerView rv1, rv2;
    private List<String> mDataList1 = new ArrayList<>();
    private GsRecyclerViewAdapter mAdapter1;

    public static void showActivity(Activity rootActivity) {
        Intent intent = new Intent(rootActivity, GsNestedScrollingParentChildRecycleViewActivity.class);
        //Bundle bundle = new Bundle();
        //intent.putExtras(bundle);
        rootActivity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gs_nested_scrolling_parent_child_recycle_view);

        rv1 = findViewById(R.id.rv_1);

        initData();
        mAdapter1 = new GsRecyclerViewAdapter(this, mDataList1);
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        rv1.setAdapter(mAdapter1);
    }

    private void initData(){
        for(int i = 0; i < 30; i++){
            mDataList1.add("数据" + i);
        }
    }
}
