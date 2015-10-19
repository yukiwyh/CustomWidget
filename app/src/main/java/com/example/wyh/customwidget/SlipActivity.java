package com.example.wyh.customwidget;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import CustomWidget.MySlipAdapter;
import CustomWidget.SlipListView;

public class SlipActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip);
        SlipListView listView = (SlipListView) findViewById(R.id.slip);

        List<String> list = new ArrayList<>();
        for(int i = 0; i < 20; i++)
        {
            list.add("11");
        }

        MySlipAdapter adapter = new MySlipAdapter(list,this);
        listView.setAdapter(adapter);
    }


}
