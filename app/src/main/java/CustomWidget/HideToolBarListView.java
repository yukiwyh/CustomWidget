package CustomWidget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;



/**
 * Created by wyh on 15/10/11.
 */
public class HideToolBarListView extends ListView {

    private final static int DIRECTION_DOWN = 0;
    private final static int DIRECTION_UP = 1;

    private int mState;
    private View headView;



    private Toolbar mToolbar;
    private DisplayMetrics displayMetrics;
    private float density;
    private int downY;
    private int mTouchSlop;
    private ObjectAnimator mObjectAnimator;

    private int downflag = 0;
    private int upflag = 0;


    public HideToolBarListView(Context context,Toolbar mToolbar) {
        super(context);
        init(context);
    }

    public HideToolBarListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HideToolBarListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public Toolbar getmToolbar() {
        return mToolbar;
    }

    public void setmToolbar(Toolbar mToolbar) {
        this.mToolbar = mToolbar;
    }

    private void init(Context context)
    {
        initHeadView(context);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    protected void initHeadView(Context context)
    {
        displayMetrics = getContext().getResources().getDisplayMetrics();
        density = displayMetrics.density;
        headView = new View(context);
        AbsListView.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , (int) getResources().getDimension(android.support.v7.appcompat.R.dimen.abc_action_bar_default_height_material));
        headView.setLayoutParams(params);
        this.addHeaderView(headView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int currY = (int) ev.getY();
                if(downY - currY > 5)
                {
                    mState = DIRECTION_DOWN;

                    if(downflag == 0) {
                        changeToolBarByDirection();
                        Toast.makeText(getContext(), "down", Toast.LENGTH_SHORT).show();
                    }
                    downflag = 1;
                    upflag = 0;
                }
                else if(currY - downY > 5)
                {
                    mState = DIRECTION_UP;
                    if(upflag == 0) {
                        changeToolBarByDirection();
                        Toast.makeText(getContext(), "up", Toast.LENGTH_SHORT).show();
                    }
                    upflag = 1;
                    downflag = 0;

                }
                break;
           /* case MotionEvent.ACTION_UP:
                break;*/
        }
        return super.onTouchEvent(ev);
    }

    private void changeToolBarByDirection()
    {
        switch (mState)
        {
            case DIRECTION_DOWN:
                Log.e("changetoolbar","down");
                mObjectAnimator = ObjectAnimator.ofFloat(mToolbar,"translationY",mToolbar.getTranslationY(),-mToolbar.getHeight());
                break;
            case DIRECTION_UP:
                Log.e("changetoolbar","up");
                mObjectAnimator = ObjectAnimator.ofFloat(mToolbar,"translationY",mToolbar.getTranslationY(),0);
                break;
        }
        mObjectAnimator.start();
    }
}
