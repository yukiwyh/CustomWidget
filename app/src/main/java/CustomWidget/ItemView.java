package CustomWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by wyh on 15/10/18.
 */
public class ItemView extends LinearLayout {


    public final static int SCROLL_CLOSE = 0;
    public final static int SCROLL_OPEN = 1;
    public int mState;
    public int downX;
    private int mBaseX;
    public View mContentView;
    public View mMenuView;
    private Scroller mScroller;

    public ItemView(Context context) {
        super(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ItemView(View ContentView, View MenuView)
    {
        super(ContentView.getContext());
        this.mContentView = ContentView;
        this.mMenuView = MenuView;
        init();
    }

    private void init()
    {
        mScroller = new Scroller(getContext());
        setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        mContentView.setLayoutParams(params);
        mMenuView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(mContentView);
        addView(mMenuView);

        mState = SCROLL_CLOSE;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    public void swipe(int dis)
    {

        Log.e("swipw","by move");
        if(dis >= mMenuView.getWidth())
        {
            dis= mMenuView.getWidth();
        }else if (dis < 0)
        {
            dis = 0;
        }

        mContentView.layout(-dis, mContentView.getTop()
                , -dis + mContentView.getWidth(), mContentView.getBottom());
        mMenuView.layout(-dis + mContentView.getWidth(), mMenuView.getTop(),
                -dis + mContentView.getWidth() + mMenuView.getWidth(),
                mMenuView.getBottom());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMenuView.measure(MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY));
        mContentView.measure(MeasureSpec.makeMeasureSpec(0,
                MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(
                getMeasuredHeight(), MeasureSpec.EXACTLY));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mContentView.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        mMenuView.layout(getMeasuredWidth(), 0, getMeasuredWidth() + mMenuView.getMeasuredWidth()
                , getMeasuredHeight());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mState == SCROLL_OPEN)
        {
            if(mScroller.computeScrollOffset())
            {
                swipe(mScroller.getCurrX());
                postInvalidate();
            }
        }else
        {
            if(mScroller.computeScrollOffset())
            {
                swipe(mBaseX - mScroller.getCurrX());
                postInvalidate();
            }
        }
    }

    public void closeMenu()
    {
        mState = SCROLL_CLOSE;
        mBaseX = -mContentView.getLeft();
        mScroller.startScroll(0,0,mBaseX,0,100);
        postInvalidate();
    }

    public void openMenu()
    {
        mState = SCROLL_OPEN;
        mScroller.startScroll(-mContentView.getLeft(), 0,
                mMenuView.getWidth()/2, 0, 100);
    }


}
