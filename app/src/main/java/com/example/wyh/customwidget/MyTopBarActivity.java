package com.example.wyh.customwidget;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import CustomWidget.MyTopBar;

public class MyTopBarActivity extends Activity {

    public MyTopBar myTopBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_top_bar);
        myTopBar = (MyTopBar) findViewById(R.id.mytopbar);
        myTopBar.setOnTopListener(new MyTopBar.OnTopListener() {
            @Override
            public void onLClick() {
                Toast.makeText(MyTopBarActivity.this,"left click",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRClick() {
                Toast.makeText(MyTopBarActivity.this,"right click",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
