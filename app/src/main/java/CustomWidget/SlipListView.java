package CustomWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by wyh on 15/10/18.
 */
public class SlipListView extends ListView {

    private boolean moveable;
    private boolean closed;
    private Context mContext;
    private int mTouchPos;
    private int mOldPos;
    private ItemView mItemView, mOldView;
    private int downX, downY;

    public SlipListView(Context context) {
        super(context);
        init(context);
    }

    public SlipListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SlipListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        mOldPos = -1;
        moveable = false;
        closed = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchPos = pointToPosition((int) ev.getX(), (int) ev.getY());
                Log.e("mTouchPos",mTouchPos+"");
                mItemView = (ItemView) getChildAt(mTouchPos - getFirstVisiblePosition());
                downX = (int) ev.getX();
                downY = (int) ev.getY();
                if (mTouchPos == mOldPos || closed) {
                    moveable = true;
                    Log.e("mItemView",mItemView+"");
                    mItemView.downX = downX;

                } else {
                    moveable = false;
                    if (mOldView != null) {
                        mOldView.closeMenu();
                    }
                }
                mOldView = mItemView;
                mOldPos = mTouchPos;

                Log.e("position",pointToPosition((int)ev.getX(),(int)ev.getY())+"");
                break;
            case MotionEvent.ACTION_MOVE:
                /*if (Math.abs(downX - ev.getX()) < Math.abs(downY - ev.getY()*dp2px(5) ))
                    break;*/
                if (moveable) {
                    int dis = (int) (mItemView.downX - ev.getX());
                    //此时方向会变
                    if (mItemView.mState == mItemView.SCROLL_OPEN)
                        dis += mItemView.mMenuView.getWidth();
                    mItemView.swipe(dis);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (moveable) {
                    if ((mItemView.downX - ev.getX()) > (mItemView.mMenuView.getWidth() / 2)) {
                        // open
                        mItemView.openMenu();
                        closed = false;
                    } else {
                        // close
                        mItemView.closeMenu();
                        closed = true;
                    }
                    ev.setAction(MotionEvent.ACTION_CANCEL);
                }
                break;
        }
        //return true;
        return super.onTouchEvent(ev);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getContext().getResources().getDisplayMetrics());
    }
}