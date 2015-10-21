package com.example.wyh.customwidget;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        final ImageView first = (ImageView) findViewById(R.id.first);
        final ImageView last = (ImageView) findViewById(R.id.last);
        Button btn = (Button) findViewById(R.id.button);

        final RotateAnimation firstAnimationtoRight = new RotateAnimation(30,0,
                Animation.ABSOLUTE,first.getLeft()+(first.getRight()-first.getLeft())/2,
                Animation.ABSOLUTE,first.getTop()+(first.getBottom()-first.getTop())/2 - 300);
        firstAnimationtoRight.setDuration(300);
        final RotateAnimation firstAnimationtoLeft = new RotateAnimation(0,30,
                Animation.ABSOLUTE,first.getLeft()+(first.getRight()-first.getLeft())/2,
                Animation.ABSOLUTE,first.getTop()+(first.getBottom()-first.getTop())/2 - 300);
        firstAnimationtoLeft.setDuration(300);

        final RotateAnimation lastAnimationtoLeft = new RotateAnimation(-30,0,
                Animation.ABSOLUTE,last.getLeft()+(last.getRight()-last.getLeft())/2,
                Animation.ABSOLUTE,last.getTop()+(last.getBottom()-last.getTop())/2 - 300);
        lastAnimationtoLeft.setDuration(300);
        final RotateAnimation lastAnimationtoRight = new RotateAnimation(0,-30,
                Animation.ABSOLUTE,last.getLeft()+(last.getRight()-last.getLeft())/2,
                Animation.ABSOLUTE,last.getTop()+(last.getBottom()-last.getTop())/2 - 300);
        lastAnimationtoRight.setDuration(300);
        firstAnimationtoRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                last.startAnimation(lastAnimationtoRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        lastAnimationtoRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                last.startAnimation(lastAnimationtoLeft);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        lastAnimationtoLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                first.startAnimation(firstAnimationtoLeft);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        firstAnimationtoLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                first.startAnimation(firstAnimationtoRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first.startAnimation(firstAnimationtoRight);
            }
        });

    }


}
