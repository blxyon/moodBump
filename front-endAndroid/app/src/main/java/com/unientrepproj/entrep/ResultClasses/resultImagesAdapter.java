package com.unientrepproj.entrep.ResultClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.imageModel;
import com.unientrepproj.entrep.imageModelDislikeLike;

import java.util.ArrayList;

public class resultImagesAdapter extends BaseAdapter{
    Context context;
    ArrayList<imageModelDislikeLike> arrayList;

    public resultImagesAdapter(Context context, ArrayList<imageModelDislikeLike> arrayList) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.results_images_buttons, parent, false);
        }
        ImageView imageModel= convertView.findViewById(R.id.imageView3);
        imageModel.setImageResource(arrayList.get(position).getmThumbIds());

        ImageButton like=convertView.findViewById(R.id.button9);
        ImageButton dislike=convertView.findViewById(R.id.button8);
        arrayList.get(position).setButtons(like,dislike);

        arrayList.get(position).setImg(imageModel);

        arrayList.get(position).getLike().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.get(position).setLikeOrDislike(1);
                Log.i("id1",(arrayList.get(position).getLikeOrDislike().toString()+" "+arrayList.get(position).getLike().toString()));
            }
        });
        arrayList.get(position).getDisklike().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.get(position).setLikeOrDislike(-1);
                Log.i("id2",(arrayList.get(position).getLikeOrDislike().toString()+" "+arrayList.get(position).getLike().toString()));
            }
        });

        arrayList.get(position).getImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("id3",arrayList.get(position).getSpotLink());
            }
        });
        return convertView;
    }
}
