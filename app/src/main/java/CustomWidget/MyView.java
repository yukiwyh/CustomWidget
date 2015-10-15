package CustomWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by wyh on 15/10/9.
 */
public class MyView extends View {


    public DisplayMetrics displayMetrics;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init()
    {
        displayMetrics = getContext().getResources().getDisplayMetrics();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    public int measureWidth(int widthMeasureSpec)
    {
        float density = displayMetrics.density;
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if(specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
            return result;
        }
        else
        {
            result = (int) (100 * density);
            if(specMode == MeasureSpec.AT_MOST)
            {
                return  Math.min(result,specSize);
            }
        }
        return result;

    }
    public int measureHeight(int heightMeasureSpec)
    {
        float density = displayMetrics.density;
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if(specMode == MeasureSpec.EXACTLY)
        {
            result = specSize;
            return result;
        }
        else
        {
            result = (int) (100 * density);
            if(specMode == MeasureSpec.AT_MOST)
            {
                return  Math.min(result,specSize);
            }
        }
        return result;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
