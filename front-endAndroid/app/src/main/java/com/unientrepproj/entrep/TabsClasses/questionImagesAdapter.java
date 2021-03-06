package com.unientrepproj.entrep.TabsClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.imageModel;

import java.util.ArrayList;

public class questionImagesAdapter extends BaseAdapter {
    Context context;
    ArrayList<imageModel> arrayList;

    public questionImagesAdapter(Context context, ArrayList<imageModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.image_list, parent, false);
        }
        ImageView imageView;
        imageView = (ImageView) convertView.findViewById(R.id.image);
        imageView.getLayoutParams().height = 300;
        imageView.getLayoutParams().width = 300;
        imageView.setImageResource(arrayList.get(position).getmThumbIds());
        return convertView;
    }
}
