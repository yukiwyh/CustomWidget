package com.example.wyh.customwidget;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import CustomWidget.BounceListView;
import CustomWidget.CustomLabel;
import CustomWidget.MyAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList list = new ArrayList();
        for(int i = 0; i < 20; i++)
        {
            list.add("标签"+i);
        }
        //CustomLabel customLabel = new CustomLabel(this,list);
        //LinearLayout layout = (LinearLayout) findViewById(R.id.content);
        // layout.addView(customLabel);

        ListView listView = (BounceListView) findViewById(R.id.listview);

        listView.setAdapter(new MyAdapter(list,this));
    }


}
