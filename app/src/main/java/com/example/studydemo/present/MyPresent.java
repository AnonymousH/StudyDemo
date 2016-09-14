package com.example.studydemo.present;


import com.example.studydemo.model.DataModel;
import com.example.studydemo.view.InterView;

/**
 * Created by HJ on 2016/9/14.
 */
public class MyPresent implements InterPresent{

    private DataModel dataModel ;

    private InterView interView;

    public MyPresent(InterView interView) {
        this.interView = interView;
        dataModel =new DataModel();
    }

    @Override
    public void getData() {
        interView.setData(dataModel.getData());
    }


}
