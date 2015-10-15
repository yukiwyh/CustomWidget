package CustomWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by wyh on 15/10/12.
 */
public class MoveableView extends View{

    private int lastX;
    private int lastY;
    public MoveableView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MoveableView(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                Log.e("down","yes");
                break;
            case MotionEvent.ACTION_MOVE:
                //offsetLeftAndRight(x-lastX);
                //offsetTopAndBottom(y-lastY);
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                Log.e("x,y",offsetX+","+offsetY);
                layout(getLeft() + offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                break;
        }
        return true;
    }
}
