package CustomWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.wyh.customwidget.R;

/**
 * Created by wyh on 15/10/12.
 */
public class SideSlipListView extends ListView {


    private Context mContext;
    private ViewGroup mItemLayout;
    private LinearLayout.LayoutParams mItemParams;
    private Button mDeleteButton;

    private boolean isShow;  /** deleteButton **/
    private int screenWidth;
    private float density;
    private GestureDetector mGestureDetector;

    private int downX;
    int slipDistance = 0;

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
        isShow = false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                dealWithDown(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                return dealWithMove(x,y);
            case MotionEvent.ACTION_UP:
                delaWithUp(x,y);
                break;
        }
        return super.onTouchEvent(ev);
    }

    private void dealWithDown(int x,int y)
    {

        downX = x;

        /** make contentview exactly **/
        mItemLayout = (ViewGroup) getChildAt(pointToPosition(x, y) - getFirstVisiblePosition());
        Log.e("mItemLayout",mItemLayout.getClass().getName());
        mItemParams = (LinearLayout.LayoutParams) mItemLayout.getChildAt(0).getLayoutParams();
        mItemParams.width = screenWidth;
        mItemLayout.getChildAt(0).setLayoutParams(mItemParams);

        /** prepare deletebutton **/

        Log.e("check",mItemParams.width+","+mItemParams.height);
        mDeleteButton = new Button(mContext);
        mDeleteButton.setWidth((int) (mItemLayout.getMeasuredWidth() * 0.3));
        Log.e("buttonwidth",(int) (mItemLayout.getMeasuredWidth() * 0.3)+"");
        mDeleteButton.setHeight(mItemParams.height);
        mDeleteButton.setText("删除");
        mDeleteButton.setTextColor(getResources().getColor(R.color.white));
        mDeleteButton.setBackgroundColor(getResources().getColor(R.color.red));

        mItemLayout.addView(mDeleteButton);

    }

    private boolean dealWithMove(int x,int y)
    {
        //int slipDistance = 0;

       /* if(x - downX < -mDeleteButton.getWidth())
        {
            slipDistance = mDeleteButton.getMeasuredWidth();
        }
        else
        {
            slipDistance = x - downX;
        }*/
        /** 为什么getmeasurewidth 为0 **/
        Log.e("movebuttonlength",mDeleteButton.getMeasuredWidth()+"");
        if(downX - x >= (int) (mItemLayout.getMeasuredWidth() * 0.3))
        {
            slipDistance = (int) (mItemLayout.getMeasuredWidth() * 0.3);
        }
        else if(downX - x < (int) (mItemLayout.getMeasuredWidth() * 0.3) && downX - x >= 0)
        {
            slipDistance = downX - x;
        }
        Log.e("slipdistance",slipDistance+"");
        Log.e("x,downx",x+","+downX);
        mItemParams.setMargins(-slipDistance, 0, 0, 0);
        //mItemParams.rightMargin = slipDistance;
        //mItemLayout.getChildAt(0).setLayoutParams(mItemParams);
        mItemLayout.getChildAt(0).setLayoutParams(mItemParams);

        isShow = true;

        return true;
    }

    private void delaWithUp(int x,int y)
    {

    }

    /**
     * find current item by (x,y)
     * @param x
     * @param y
     * @return
     */
    @Override
    public int pointToPosition(int x, int y) {
        return super.pointToPosition(x, y);
    }
}
