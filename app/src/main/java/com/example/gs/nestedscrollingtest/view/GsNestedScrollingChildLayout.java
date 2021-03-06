package com.example.gs.nestedscrollingtest.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * 作者    你的名字
 * 时间    2019/1/17 17:29
 * 文件    NestedScrollingTest
 * 描述
 */
public class GsNestedScrollingChildLayout extends LinearLayout implements NestedScrollingChild2 {
    private final static String TAG = GsNestedScrollingChildLayout.class.getSimpleName();

    private NestedScrollingChildHelper mChildHelper;
    private int mViewHeight;
    private int mCanScrollY;
    private int mLastMotionY;
    /**
     * Used during scrolling to retrieve the new offset within the window.
     */
    private final int[] mScrollOffset = new int[2];
    private final int[] mScrollConsumed = new int[2];


    public GsNestedScrollingChildLayout(Context context) {
        this(context, null);
    }

    public GsNestedScrollingChildLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GsNestedScrollingChildLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GsNestedScrollingChildLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.mChildHelper = new NestedScrollingChildHelper(this);
        this.mChildHelper.setNestedScrollingEnabled(true);
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return this.mChildHelper.startNestedScroll(axes, type);
    }

    @Override
    public void stopNestedScroll(int type) {
        this.mChildHelper.stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return this.mChildHelper.hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return this.mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //这里不能使用getY，因为getY是该组件在父布局中的Y坐标，他在父组件中不断变化
                mLastMotionY = (int) event.getRawY();
                int i1 = (int) event.getY();
                Log.e(TAG, "按下 getRawY="+mLastMotionY+" getY="+i1);
                startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH);
                break;
            case MotionEvent.ACTION_MOVE:
                //这里不能使用getY，因为getY是该组件在父布局中的Y坐标，他在父组件中不断变化
                final int y = (int) event.getRawY();
                int i2 = (int) event.getY();
                Log.e(TAG, "移动 getRawY="+y+" getY="+i2);
                int deltaY = mLastMotionY - y;
                mLastMotionY = y;
                //Log.e(TAG, "消耗前 deltaY="+deltaY);
                if (dispatchNestedPreScroll(0, deltaY, mScrollConsumed,
                        mScrollOffset, ViewCompat.TYPE_TOUCH)) {
                    deltaY -= mScrollConsumed[1];
                }
                //Log.e(TAG, "消耗后 deltaY="+deltaY);
                scrollBy(0, deltaY);
                break;
            case MotionEvent.ACTION_UP:
                stopNestedScroll(ViewCompat.TYPE_TOUCH);
                break;
            case MotionEvent.ACTION_CANCEL:
                stopNestedScroll(ViewCompat.TYPE_TOUCH);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.mViewHeight <= 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            mViewHeight = getMeasuredHeight();
            //Log.e(TAG, "mViewHeight="+mViewHeight);
        } else {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            mCanScrollY = getMeasuredHeight() - mViewHeight;
            Log.e(TAG, "getMeasuredHeight()="+getMeasuredHeight()
                    +" mViewHeight="+mViewHeight +" mCanScrollY="+mCanScrollY);
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        Log.e(TAG, "scrollTo x="+x+" y="+y);
        if(y < 0){
            y = 0;
        }
        if(y > mCanScrollY){
            y = mCanScrollY;
        }
        super.scrollTo(x, y);
    }

    @Override
    public void scrollBy(int x, int y) {
        //Log.e(TAG, "scrollBy x="+x+" y="+y);
        super.scrollBy(x, y);
    }
}
