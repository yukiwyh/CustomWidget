package CustomWidget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.AbsListView;
import android.widget.ListView;

import java.lang.reflect.Field;

/**
 * Created by wyh on 15/10/6.
 */
/**并不完美的回弹效果ListView**/
public class BounceListView extends ListView {

    private final static int MAX_Y_DISTANCE = 100;
    private final static float SCROLL_RATIO = 0.5f;
    private int mYOverScrollDestance;
    public BounceListView(Context context) {
        super(context);
        initBounceListView(context);
    }

    public BounceListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initBounceListView(context);
    }

    public BounceListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBounceListView(context);
    }

    public void initBounceListView(Context context)
    {
        final DisplayMetrics dm = context.getResources().getDisplayMetrics();
        final float density = dm.density;
        mYOverScrollDestance = (int) (density * MAX_Y_DISTANCE);

        try {
            Class<?> c = (Class<?>) Class.forName(AbsListView.class.getName());
            Field egtField = c.getDeclaredField("mEdgeGlowTop");
            Field egbBottom = c.getDeclaredField("mEdgeGlowBottom");
            egtField.setAccessible(true);
            egbBottom.setAccessible(true);
            Object egtObject = egtField.get(this); // this 指的是ListiVew实例
            Object egbObject = egbBottom.get(this);

            // egtObject.getClass() 实际上是一个 EdgeEffect 其中有两个重要属性 mGlow mEdge
            // 并且这两个属性都是Drawable类型
            Class<?> cc = (Class<?>) Class.forName(egtObject.getClass()
                    .getName());
            Field mGlow = cc.getDeclaredField("mGlow");
            mGlow.setAccessible(true);
            mGlow.set(egtObject, new ColorDrawable(Color.TRANSPARENT));
            mGlow.set(egbObject, new ColorDrawable(Color.TRANSPARENT));

            Field mEdge = cc.getDeclaredField("mEdge");
            mEdge.setAccessible(true);
            mEdge.set(egtObject, new ColorDrawable(Color.TRANSPARENT));
            mEdge.set(egbObject, new ColorDrawable(Color.TRANSPARENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        int newDeltaY = deltaY;
        int delta = (int) (deltaY * SCROLL_RATIO);
        if (delta != 0) newDeltaY = delta;
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, (int) (mYOverScrollDestance*SCROLL_RATIO), isTouchEvent);
    }
}
