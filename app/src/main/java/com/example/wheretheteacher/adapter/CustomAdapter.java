package com.example.wheretheteacher.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wheretheteacher.R;
import com.example.wheretheteacher.room.Teacher;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<Teacher> stringList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, List<Teacher> stringList) {
        this.context = context;
        this.stringList = stringList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int i) {
        return stringList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View view = convertView;

       if(view == null){
           view = layoutInflater.inflate(R.layout.item, parent, false);
       }

       Teacher teacher = (Teacher) getItem(position);

        ((TextView) view.findViewById(R.id.teachersNames)).setText(teacher.getTeacherName());

        return view;
    }
}
