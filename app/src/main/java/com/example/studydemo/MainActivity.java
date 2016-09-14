package com.example.studydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.studydemo.adapter.ListViewAdapter;
import com.example.studydemo.present.MyPresent;
import com.example.studydemo.view.InterView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements InterView {

    private ListView listView;
    private ListViewAdapter adapter;

    private MyPresent myPresent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }

    private void initData() {
        myPresent = new MyPresent(this);
        myPresent.getData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter(this);
        listView.setAdapter(adapter);
    }

    @Override
    public void setData(List<String> dataList) {
        adapter.addData(dataList);
        adapter.notifyDataSetChanged();
    }
}
