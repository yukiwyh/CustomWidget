package com.example.wyh.customwidget;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import CustomWidget.HideToolBarListView;
import CustomWidget.MyAdapter;

public class HideToolBarListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_tool_bar_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HideToolBarListView listView = (HideToolBarListView) findViewById(R.id.hidetoolbarlistview);
        listView.setmToolbar(toolbar);
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 20; i++)
        {
            list.add("aa");
        }
        MyAdapter myAdapter = new MyAdapter(list,this);
        listView.setAdapter(myAdapter);
    }


}
