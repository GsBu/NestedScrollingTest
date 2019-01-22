package com.example.gs.nestedscrollingtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = MainActivity.class.getSimpleName();

    private Button bt1, bt2, bt3, bt4, btScroll;
    private TextView tv1;
    private LinearLayout ll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll1 = findViewById(R.id.ll_1);
        tv1 = findViewById(R.id.tv_1);
        bt1 = findViewById(R.id.bt_1);
        bt2 = findViewById(R.id.bt_2);
        bt3 = findViewById(R.id.bt_3);
        bt4 = findViewById(R.id.bt_4);
        btScroll = findViewById(R.id.bt_scroll);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        btScroll.setOnClickListener(this);

        tv1.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv1.setText("Scroll:("+ll1.getScrollX()+","+ll1.getScrollY()+")"
                        +"坐标:("+tv1.getX()+","+tv1.getY()+")");
            }
        }, 500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_1:
                GsNestedScrollingParentChildActivity.showActivity(this);
                break;
            case R.id.bt_2:
                GsNestedScrollingParentChildRecycleViewActivity.showActivity(this);
                break;
            case R.id.bt_3:
                GsNestedScrollingParentActivity.showActivity(this);
                break;
            case R.id.bt_4:
                GsNestedScrollingParentRecycleViewActivity.showActivity(this);
                break;
            case R.id.bt_scroll:
                if(ll1.getScrollX() == 0 && ll1.getScrollY() == 0){
                    ll1.scrollTo(-200, -200);
                }else if(ll1.getScrollX() == -200 && ll1.getScrollY() == -200){
                    ll1.scrollTo(-100, -100);
                }else if(ll1.getScrollX() == -100 && ll1.getScrollY() == -100){
                    ll1.scrollBy(100, 100);
                }
                tv1.setText("Scroll:("+ll1.getScrollX()+","+ll1.getScrollY()+")"
                        +"坐标:("+tv1.getX()+","+tv1.getY()+")");
                break;
            default:
                break;
        }
    }
}
