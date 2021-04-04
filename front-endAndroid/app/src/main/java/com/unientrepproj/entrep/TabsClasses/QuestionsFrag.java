package com.unientrepproj.entrep.TabsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.Results;

import java.util.ArrayList;


public class QuestionsFrag extends Fragment {
    GridView gridView;
    int image[] = {R.mipmap.afraid,R.mipmap.sad,R.mipmap.happy, R.mipmap.angry,R.mipmap.surprised};
    ArrayList<imageModel> arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_questions,container,false);
        //gridView=(GridView)v.findViewById(R.id.girdviewQuestionAnswers);

        gridView = v.findViewById(R.id.girdviewQuestionAnswers);
        arrayList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            imageModel imagemodel = new imageModel();
            imagemodel.setmThumbIds(image[i]);
            //add in array list
            arrayList.add(imagemodel);
        }

        questionImagesAdapter adpter= new questionImagesAdapter(getActivity().getApplicationContext(), arrayList);
        gridView.setAdapter(adpter);
        //item click listner
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                arrayList.get(position).setPressed();
            }
        });
        Button button=v.findViewById(R.id.submit_quest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), Results.class);
                ArrayList<Integer> booleans=new ArrayList<>();
                for(imageModel i:arrayList){
                    booleans.add(i.isPressed());
                }
                intent.putExtra("booleans",booleans);
                startActivity(intent);
            }
        });
        return v;

    }

    public GridView getGridView() {
        return gridView;
    }
}