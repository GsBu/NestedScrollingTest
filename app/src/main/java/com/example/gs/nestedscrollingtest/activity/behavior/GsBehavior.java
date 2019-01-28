package com.example.gs.nestedscrollingtest.activity.behavior;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.gs.nestedscrollingtest.activity.util.DisplayUtil;

/**
 * 作者    你的名字
 * 时间    2019/1/28 15:24
 * 文件    NestedScrollingTest
 * 描述
 */
public class GsBehavior extends CoordinatorLayout.Behavior {
    private final static String TAG = GsBehavior.class.getSimpleName();

    private float mOriginalY = -9999;
    private final int MIN_DISTANCE_Y = 500;

    public GsBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                                       @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        Log.e(TAG, "onStartNestedScroll type="+type);
        if (axes == ViewCompat.SCROLL_AXIS_VERTICAL) { //scroll vertical
            if(mOriginalY == -9999) {
                mOriginalY = child.getY();
            }
            return true;
        }
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child,
                                  @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.e(TAG, "onNestedPreScroll type="+type + " dy="+dy +" child.getY="+child.getY()+" mOriginalY="+mOriginalY);
        boolean isFirstChildVisible = (dy < 0 && target.getScrollY() <= 0);
        if (isFirstChildVisible && type == ViewCompat.TYPE_TOUCH) {
            Log.e(TAG, "aaaaaaa 距离="+(child.getY() - dy));
            consumed[1] = dy;//consume dy。有没有都可
            child.setY(child.getY() - dy);
            return;//有没有都可
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                   @NonNull View child, @NonNull View target, int type) {
        Log.e(TAG, "onStopNestedScroll type="+type +" child.getY="+child.getY()+" mOriginalY="+mOriginalY);
        if(type == ViewCompat.TYPE_TOUCH) {
            if ((child.getY() - mOriginalY) < MIN_DISTANCE_Y) {
                recovery(child);
            } else {
                sliding(child);
            }
        }
        super.onStopNestedScroll(coordinatorLayout, child, target, type);
    }

    private void recovery(final View child) {
        if (child != null) {
            ValueAnimator anim = ValueAnimator.ofFloat(child.getY(), mOriginalY).setDuration(200);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float) animation.getAnimatedValue();
                    child.setY(value);
                }
            });
            anim.start();
        }
    }

    private void sliding(final View child) {
        if (child != null) {
            ValueAnimator anim = ValueAnimator.ofFloat(child.getY(), DisplayUtil.getScreenHeight()).setDuration(300);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float value = (float) animation.getAnimatedValue();
                    child.setY(value);
                }
            });
            anim.start();
        }
    }
}
