package CustomWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wyh on 15/10/5.
 */
public class CustomLabel extends ViewGroup {
    private int mContentHeight,mContentWidth;
    private TextView[] TextViewSet;
    public CustomLabel(Context context, ArrayList<String> list) {
        super(context);
        init(list, context);
    }

    public CustomLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLabel(Context context, AttributeSet attrs, int defStyleAttr,ArrayList<String> list) {
        super(context, attrs, defStyleAttr);
        init(list,context);
    }

    public CustomLabel(Context context, AttributeSet attrs,ArrayList<String> list) {
        super(context, attrs);
        init(list,context);
    }

    private void init(ArrayList<String> list,Context context)
    {
        TextViewSet = new TextView[list.size()];
        for(int i = 0; i < list.size(); i++)
        {
            TextViewSet[i] = new TextView(context);
            TextViewSet[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
            TextViewSet[i].setText(list.get(i));
            TextViewSet[i].setSingleLine();
            TextViewSet[i].setPadding(0,10,20,0);
            this.addView(TextViewSet[i]);
        }
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mContentWidth = dm.widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.e("childcount", getChildCount() + "");
//        Log.e("widthMeasureSpec",widthMeasureSpec+"");
//        Log.e("heightMeasureSpec",heightMeasureSpec+"");
        for(int i = 0; i < getChildCount(); i++)
        {
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
        //measureChildren(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int height = 0,sigleLineWidth = 0;
        Log.e("childcount", getChildCount() + "");
        for(int i = 0; i < getChildCount(); i++)
        {
            View child = getChildAt(i);
            if(sigleLineWidth + child.getMeasuredWidth() > mContentWidth)
            {
                sigleLineWidth = 0;
                height = height + child.getMeasuredHeight();
            }
            Log.e("count l t r b", i+" "+sigleLineWidth+" "+height+" "+child.getMeasuredWidth()+" "+child.getMeasuredHeight());
            child.layout(sigleLineWidth, height, sigleLineWidth + child.getMeasuredWidth(), height + child.getMeasuredHeight());
            sigleLineWidth = sigleLineWidth + child.getMeasuredWidth();
            //child.layout(0, sigleLineWidth, child.getMeasuredWidth(), child.getMeasuredHeight());
            //sigleLineWidth = sigleLineWidth + child.getWidth();
            //childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
        }
    }
}
