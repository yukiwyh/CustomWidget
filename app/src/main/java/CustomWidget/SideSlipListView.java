package CustomWidget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

import com.example.wyh.customwidget.R;

/**
 * Created by wyh on 15/10/12.
 */
public class SideSlipListView extends ListView implements View.OnTouchListener{


    private Context mContext;
    private ViewGroup mItemLayout;
    private LinearLayout.LayoutParams mItemParams;
    private Button mDeleteButton;
    private ObjectAnimator mObjectAnimator;
    private Scroller mScroller;


    private boolean isShow;  /** deleteButton **/
    private boolean isActionUp;
    private int screenWidth;
    private int mCurrItemIndex;
    private float density;
    private GestureDetector mGestureDetector;

    private int downX;
    private int slipDistance = 0;
    private int tempWidth; //搞懂width参数之后替换掉

    public SideSlipListView(Context context) {
        super(context);
        init(context);
    }

    public SideSlipListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public SideSlipListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context)
    {
        this.mContext = context;
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        density = displayMetrics.density;
        screenWidth = displayMetrics.widthPixels;
        mCurrItemIndex = -1;
        isShow = false;
        isActionUp = false;
        tempWidth = -10;
        //setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        //Log.e("preindex",mCurrItemIndex+"");
        //Log.e("currindex",(pointToPosition((int)event.getX(),(int)event.getY())-getFirstVisiblePosition())+"");
        if(mCurrItemIndex != pointToPosition((int)event.getX(),(int)event.getY())-getFirstVisiblePosition())
        {
            Log.e("before", mDeleteButton + "");
            mDeleteButton = null;
            Log.e("after", mDeleteButton + "");
            if(isShow) {
                Log.e("Show","close");
                mItemLayout = (ViewGroup) getChildAt(mCurrItemIndex);
                mItemParams = (LinearLayout.LayoutParams) mItemLayout.getChildAt(0).getLayoutParams();
                mItemParams.setMargins(0, 0, 0, 0);
                mItemLayout.getChildAt(0).setLayoutParams(mItemParams);
                isShow = false;
            }
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                return dealWithDown(x,y);
            case MotionEvent.ACTION_MOVE:
                return dealWithMove(x,y);
            case MotionEvent.ACTION_UP:
                return dealWithUp(x, y);
        }
        return super.onTouchEvent(ev);
    }

    private boolean dealWithDown(int x,int y)
    {
        Log.e("position",pointToPosition(x,y)+"");

        if(mCurrItemIndex != pointToPosition(x,y)-getFirstVisiblePosition()) {
            Log.e("click same?","no");
            /** make contentview exactly **/
            mItemLayout = (ViewGroup) getChildAt(pointToPosition(x, y) - getFirstVisiblePosition());
            mItemParams = (LinearLayout.LayoutParams) mItemLayout.getChildAt(0).getLayoutParams();
            mItemParams.width = screenWidth;
            mItemParams.setMargins(0,0,0,0);
            mItemLayout.getChildAt(0).setLayoutParams(mItemParams);

            if(mItemLayout.getTag() != null)
            {
                Log.e("button","tag button");
                mDeleteButton = (Button) mItemLayout.getTag();

            }else
            {
                Log.e("button","new button");
                mDeleteButton = new Button(mContext);
                if(tempWidth < 0) {
                    tempWidth = (int) (mItemLayout.getMeasuredWidth() * 0.3);
                }
                mDeleteButton.setHeight(mItemParams.height);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        (int) (mItemLayout.getMeasuredWidth()*0.3),mItemParams.height);
                mDeleteButton.setText("删除");
                mDeleteButton.setTextColor(getResources().getColor(R.color.white));
                mDeleteButton.setBackgroundColor(getResources().getColor(R.color.red));
                mDeleteButton.setLayoutParams(params);
                mItemLayout.addView(mDeleteButton);
                mItemLayout.setTag(mDeleteButton);
            }
            mItemLayout.invalidate();
            Log.e("initButtonWidth", mDeleteButton.getMeasuredWidth() + "");
        }
        if (isActionUp) {
            if (mCurrItemIndex>=0) {
                mItemLayout = (ViewGroup) getChildAt(mCurrItemIndex);
                mItemParams = (LinearLayout.LayoutParams) mItemLayout.getChildAt(0).getLayoutParams();
                mDeleteButton.setTranslationX(0);
                mItemLayout.getChildAt(0).setTranslationX(0);
                mItemParams.setMargins(0, 0, 0, 0);
                mItemParams.width = screenWidth;
                mItemLayout.getChildAt(0).setLayoutParams(mItemParams);
                //invalidate();
                isActionUp = false;
                if(isShow) {
                    //mItemParams = (LinearLayout.LayoutParams) mItemLayout.getChildAt(0).getLayoutParams();
                    mDeleteButton.setTranslationX(0);
                    mItemLayout.getChildAt(0).setTranslationX(0);
                    mItemParams.setMargins(0, 0, 0, 0);
                    mItemParams.width = screenWidth;
                    mItemLayout.getChildAt(0).setLayoutParams(mItemParams);
                    Log.e("Show", "close");
                    invalidate();
                    isShow = false;
                }
            }
        }

        mCurrItemIndex = pointToPosition(x, y) - getFirstVisiblePosition();
        mItemLayout = (ViewGroup) getChildAt(mCurrItemIndex);
        downX = x;


       /* *//** make contentview exactly **//*
        mItemLayout = (ViewGroup) getChildAt(pointToPosition(x, y) - getFirstVisiblePosition());
        //Log.e("mItemLayout", mItemLayout.getClass().getName());

        mItemParams = (LinearLayout.LayoutParams) mItemLayout.getChildAt(0).getLayoutParams();
        mItemParams.width = screenWidth;
        mItemParams.setMargins(0,0,0,0);
        mItemLayout.getChildAt(0).setLayoutParams(mItemParams);
        mItemLayout.getChildAt(0).setTranslationX(0);

        if (mDeleteButton != null)
        {
            mDeleteButton.setTranslationX(0);
        }

        *//** prepare deletebutton **//*

        if(mDeleteButton == null) {
            mDeleteButton = new Button(mContext);
            tempWidth = (int) (mItemLayout.getMeasuredWidth() * 0.3);
            mDeleteButton.setHeight(mItemParams.height);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    (int) (mItemLayout.getMeasuredWidth()*0.3),mItemParams.height);
            mDeleteButton.setText("删除");
            mDeleteButton.setTextColor(getResources().getColor(R.color.white));
            mDeleteButton.setBackgroundColor(getResources().getColor(R.color.red));
            mDeleteButton.setLayoutParams(params);
            mItemLayout.addView(mDeleteButton);
        }
        mItemLayout.invalidate();
        Log.e("initButtonWidth", mDeleteButton.getMeasuredWidth() + "");*/
        return true;

    }

    private boolean dealWithMove(int x,int y)
    {

        /** 为什么getmeasurewidth 为0 **/
        //Log.e("movebuttonlength",mDeleteButton.getMeasuredWidth()+"");
        if(downX - x >= (int) (mItemLayout.getMeasuredWidth() * 0.3))
        {
            slipDistance = (int) (mItemLayout.getMeasuredWidth() * 0.3);
        }
        else if(downX - x < (int) (mItemLayout.getMeasuredWidth() * 0.3) && downX - x >= 0)
        {
            slipDistance = downX - x;
        }
        else
        {
            slipDistance = 0;
        }
        //Log.e("slipdistance",slipDistance+"");
        //Log.e("x,downx",x+","+downX);
        mItemParams = (LinearLayout.LayoutParams) mItemLayout.getChildAt(0).getLayoutParams();
        mItemParams.setMargins(-slipDistance, 0, 0, 0);
        mItemLayout.getChildAt(0).setLayoutParams(mItemParams);


        return true;
    }

    private boolean dealWithUp(int x,int y)
    {
        //Log.e("slipdistance",slipDistance+"");
        /*if(slipDistance <= tempWidth/2)
        {

            mItemParams.leftMargin = 0;
            mItemLayout.getChildAt(0).setLayoutParams(mItemParams);
        }
        else
        {
            isShow = true;
            mItemParams.leftMargin = -tempWidth;
            mItemLayout.getChildAt(0).setLayoutParams(mItemParams);
        }*/
        //Log.e("tempwidth",tempWidth+"");
        ObjectAnimator test;
        if(slipDistance <= tempWidth/2)
        {
            mObjectAnimator = ObjectAnimator.ofFloat(mDeleteButton,"translationX",slipDistance);
            test = ObjectAnimator.ofFloat(mItemLayout.getChildAt(0),"translationX",slipDistance);
        }
        else
        {
            Log.e("buttonWidthbefore",mDeleteButton.getMeasuredWidth()+"");
            mObjectAnimator = ObjectAnimator.ofFloat(mDeleteButton,"translationX",slipDistance-tempWidth);
            test = ObjectAnimator.ofFloat(mItemLayout.getChildAt(0),"translationX",slipDistance-tempWidth);
            isShow = true;
        }
        mObjectAnimator.setDuration(100);
        test.setDuration(100);
        mObjectAnimator.start();
        test.start();


      /*  if(slipDistance <= tempWidth/2) {
            Log.e("upback", slipDistance + "");
            int l = mItemLayout.getChildAt(0).getLeft();
            int t = mItemLayout.getChildAt(0).getTop();
            int r = mItemLayout.getChildAt(0).getRight();
            int b = mItemLayout.getChildAt(0).getBottom();

            mScroller.startScroll(mItemLayout.getScrollX(),mItemLayout.getScrollY()
                    ,mItemLayout.getScrollX()-slipDistance,0);
        }
        else
        {
            isShow = true;
            mScroller.startScroll(mItemLayout.getScrollX(),mItemLayout.getScrollY(),tempWidth,0);
            Log.e("upfront", tempWidth + "");
            invalidate();
        }*/
        //Log.e("buttonWidthafter", mDeleteButton.getMeasuredWidth() + "");
        //slipDistance = 0;
        //mItemLayout.invalidate();
        isActionUp = true;
        return true;
    }



    /**
     * find current item by (x,y) include total member
     * @param x
     * @param y
     * @return
     */
    @Override
    public int pointToPosition(int x, int y) {
        return super.pointToPosition(x, y);
    }


}
