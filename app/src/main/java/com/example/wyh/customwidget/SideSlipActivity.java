package com.example.wyh.customwidget;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import CustomWidget.MyAdapter;
import CustomWidget.SideSlipListView;

public class SideSlipActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_slip);

        SideSlipListView listView = (SideSlipListView) findViewById(R.id.sidesliplistview);

        List<String> list = new ArrayList<>();
        for(int i = 0; i < 20; i++)
        {
            list.add("aa");
        }
        MyAdapter myAdapter = new MyAdapter(list,this);
        listView.setAdapter(myAdapter);
    }


}
