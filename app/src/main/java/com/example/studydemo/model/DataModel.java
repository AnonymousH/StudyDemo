package com.example.studydemo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HJ on 2016/9/14.
 */
public class DataModel {


    public List<String>  getData(){

        List<String> dataList =new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            dataList.add("这是第 "+i+" 条数据");
        }

        return dataList;

    }

}
