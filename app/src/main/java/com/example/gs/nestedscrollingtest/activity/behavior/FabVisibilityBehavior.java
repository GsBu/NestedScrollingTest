package com.example.gs.nestedscrollingtest.activity.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者    你的名字
 * 时间    2019/1/28 14:59
 * 文件    NestedScrollingTest
 * 描述
 */
public class FabVisibilityBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
    private final static String TAG = FabVisibilityBehavior.class.getSimpleName();

    public FabVisibilityBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull FloatingActionButton child, @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        Log.e(TAG, "onStartNestedScroll");
        if (axes == ViewCompat.SCROLL_AXIS_VERTICAL) { //scroll vertical
            child.hide();
            return true;
        }
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                   @NonNull FloatingActionButton child, @NonNull View target, int type) {
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
        Log.e(TAG, "onStopNestedScroll type="+type);
        if (!child.isShown()) {//onStopNestedScroll执行两次
            child.show();
        }
    }
}
