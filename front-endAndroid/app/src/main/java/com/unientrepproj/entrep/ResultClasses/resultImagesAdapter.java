package com.unientrepproj.entrep.ResultClasses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

        Glide.with(context).load(arrayList.get(position).getImgAcc()).into(imageModel);//download and place the images



        //imageModel.setImageBitmap(arrayList.get(position).getImgAcc());

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

                Intent intent=new Intent(Intent.ACTION_VIEW);


                String arr[]=arrayList.get(position).getSpotLink().split("/");

                intent.setData(Uri.parse("spotify:playlist:"+arr[arr.length-1]));


                intent.putExtra(
                        Intent.EXTRA_REFERRER,
                        Uri.parse("android-app://"+this)
                );
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
