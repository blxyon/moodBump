package com.unientrepproj.entrep.TabsClasses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.ResultClasses.ResultActivity;


public class DiaryFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_diary,container,false);

        EditText journalText=v.findViewById(R.id.editTextTextPersonName2);


        ImageButton but=v.findViewById(R.id.button5);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=journalText.getText().toString();


                Log.i("string from diary:",s);
                Intent intent=new Intent(getActivity().getApplicationContext(), ResultActivity.class);//defining the next page to start

                startActivity(intent);//starting it
            }
        });

        ImageButton but2=v.findViewById(R.id.button7);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }


}