package com.example.wyh.customwidget;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import CustomWidget.MyAdapter;

public class EmptyListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_list_view);
        ListView listView = (ListView) findViewById(R.id.emptylisview);
        ImageView imageView = (ImageView) findViewById(R.id.emptylistimage);
        listView.setEmptyView(imageView);
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 20; i++)
        {
            list.add("aa");
        }
        MyAdapter myAdapter = new MyAdapter(list,this);
        listView.setAdapter(myAdapter);
    }


}
