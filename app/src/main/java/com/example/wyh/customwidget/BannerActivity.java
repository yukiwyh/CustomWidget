package com.example.wyh.customwidget;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends Activity implements ViewPager.OnPageChangeListener{

    private ViewPager mViewPager;
    private ImageView[] imageView;
    private List<View> view;
    private List<ImageView> pointview;
    private int currIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);


        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        imageView = new ImageView[5];
        view = new ArrayList<>();
        pointview = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            ImageView iv = new ImageView(this);
            iv.setBackground(getResources().getDrawable(R.mipmap.ic_launcher));
            iv.setLayoutParams(params);
            view.add(iv);

        }

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        GuideAdapter ga = new GuideAdapter(view);
        mViewPager.setAdapter(ga);
        mViewPager.setOnPageChangeListener(this);

        initDot();
    }


    public void initDot()
    {
        LinearLayout layout = (LinearLayout) findViewById(R.id.redpoint);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(30 ,30);
        params.setMargins(20,0,0,0);
        for(int i = 0; i < 5; i++)
        {
            ImageView redpoint = new ImageView(this);
            if(i == 0)
            {
                redpoint.setImageResource(R.mipmap.ic_launcher);
            }
            else {
                redpoint.setImageResource(R.drawable.reddot);
            }
            redpoint.setLayoutParams(params);
            layout.addView(redpoint);
            pointview.add(redpoint);
        }
        currIndex = 0;
    }

    public void setCurDot(int position)
    {
        pointview.get(position).setImageResource(R.mipmap.ic_launcher);
        pointview.get(currIndex).setImageResource(R.drawable.reddot);
        currIndex = position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class GuideAdapter extends PagerAdapter
    {

        private List<View> view;

        public GuideAdapter(List<View> view) {
            super();
            this.view = view;
        }
        @Override
        public int getCount() {
            return view.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(view.get(arg1));
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {

            ((ViewPager) arg0).addView(view.get(arg1), 0);

            return view.get(arg1);
        }
    }



}
