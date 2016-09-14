package com.example.studydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.studydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HJ on 2016/9/14.
 */
public class ListViewAdapter extends BaseAdapter {

    private Context context;

    private List<String> dataString;

    public ListViewAdapter(Context context) {
        this.context = context;
        dataString = new ArrayList<>();
    }

    public void addData(List<String> mList){
        if(mList==null ){
            return;
        }

        if(dataString==null){
            dataString =new ArrayList<>();
        }

        dataString.addAll(mList);
    }

    @Override
    public int getCount() {
        return dataString.size();
    }

    @Override
    public Object getItem(int i) {
        return dataString.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_data, null);

            viewHolder.item_text = (TextView) convertView.findViewById(R.id.item_text);


            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_text.setText(dataString.get(position));

        return convertView;
    }

    static class ViewHolder{
        TextView item_text;
    }
}
