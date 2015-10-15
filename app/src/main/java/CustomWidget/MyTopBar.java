package CustomWidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wyh.customwidget.R;

/**
 * Created by wyh on 15/10/9.
 */
public class MyTopBar extends LinearLayout {

    private OnTopListener listener;
    private TypedArray typedArray;
    private Context context;
    private Drawable lBackground;
    private Drawable rBackground;
    private String Title;
    private String lText;
    private String rText;
    private float titleSize;

    public interface OnTopListener
    {
        void onLClick();
        void onRClick();
    }

    public MyTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        Log.e("init","yes");
        init(attrs);
    }

    public MyTopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Log.e("init","yes");
        init(attrs);
    }

    public MyTopBar(Context context) {
        super(context);
        this.context = context;
        Log.e("init", "yes");
    }

    public void init(AttributeSet attrs)
    {
        initTypeArray(attrs);
        Button lButton = new Button(context);
        Button rButton = new Button(context);
        TextView textView = new TextView(context);

        lButton.setBackground(lBackground);
        lButton.setText(lText);
        rButton.setBackground(rBackground);
        rButton.setText(rText);
        textView.setText(Title);
        textView.setTextSize(titleSize);
        textView.setGravity(Gravity.CENTER);

        this.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams btnparams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,0.1f);
        LinearLayout.LayoutParams titleparams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,0.8f);
        //titleparams.setMargins(150,0,0,0);
        addView(lButton, btnparams);
        addView(textView,titleparams);
        addView(rButton,btnparams);

        setListener(lButton,rButton);

    }

    public void setOnTopListener(OnTopListener listener)
    {
        this.listener = listener;
    }

    public void setListener(Button lBtn, Button rBtn)
    {
        lBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                {
                    listener.onLClick();
                }
            }
        });
        rBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                {
                    listener.onRClick();
                }
            }
        });
    }

    public void initTypeArray(AttributeSet attrs)
    {
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.mytopbar);
        lBackground = typedArray.getDrawable(R.styleable.mytopbar_left_background);
        rBackground = typedArray.getDrawable(R.styleable.mytopbar_right_background);
        lText = typedArray.getString(R.styleable.mytopbar_left_text);
        rText = typedArray.getString(R.styleable.mytopbar_right_text);
        titleSize = typedArray.getDimension(R.styleable.mytopbar_title_size, 10);
        Title = typedArray.getString(R.styleable.mytopbar_title_text);
        typedArray.recycle();

    }
}
