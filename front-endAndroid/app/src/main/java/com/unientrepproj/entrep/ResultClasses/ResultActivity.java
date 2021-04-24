package com.unientrepproj.entrep.ResultClasses;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unientrepproj.entrep.CalendarActivity;
import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.StartingPage;
import com.unientrepproj.entrep.TabsClasses.questionImagesAdapter;
import com.unientrepproj.entrep.imageModel;
import com.unientrepproj.entrep.imageModelDislikeLike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ResultActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<imageModelDislikeLike> arrayList;
    ViewFlipper viewFlipper;
    String quotes[]={"asfasg","asgasgas","asgashahashahsas"};

    //int image[] = {,R.mipmap.s2,R.mipmap.s3, R.mipmap.s4,R.mipmap.s5};
    String spotLinks[]={"https1","ht","asgas","asgas","asgasg"};
//    String urls[]={"https://images-ext-2.discordapp.net/external/dIhtCsH7KMv3XToNx9rSoTxdqohW9kDWafMvTT-BT8o/%3Fwidth%3D620%26quality%3D85%26auto%3Dformat%26fit%3Dmax%26s%3D56d5de4c5609ca98def0c3382bd569b4/https/i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg","" +
//            "https://images-ext-2.discordapp.net/external/dIhtCsH7KMv3XToNx9rSoTxdqohW9kDWafMvTT-BT8o/%3Fwidth%3D620%26quality%3D85%26auto%3Dformat%26fit%3Dmax%26s%3D56d5de4c5609ca98def0c3382bd569b4/https/i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg",
//            "https://images-ext-2.discordapp.net/external/dIhtCsH7KMv3XToNx9rSoTxdqohW9kDWafMvTT-BT8o/%3Fwidth%3D620%26quality%3D85%26auto%3Dformat%26fit%3Dmax%26s%3D56d5de4c5609ca98def0c3382bd569b4/https/i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg",
//    "https://images-ext-2.discordapp.net/external/dIhtCsH7KMv3XToNx9rSoTxdqohW9kDWafMvTT-BT8o/%3Fwidth%3D620%26quality%3D85%26auto%3Dformat%26fit%3Dmax%26s%3D56d5de4c5609ca98def0c3382bd569b4/https/i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg",
//    "https://images-ext-2.discordapp.net/external/dIhtCsH7KMv3XToNx9rSoTxdqohW9kDWafMvTT-BT8o/%3Fwidth%3D620%26quality%3D85%26auto%3Dformat%26fit%3Dmax%26s%3D56d5de4c5609ca98def0c3382bd569b4/https/i.guim.co.uk/img/media/fe1e34da640c5c56ed16f76ce6f994fa9343d09d/0_174_3408_2046/master/3408.jpg"};
    //String urls[]={"http://127.0.0.1:9999/3408.jpg","http://127.0.0.1:9999/3408.jpg","http://127.0.0.1:9999/3408.jpg","http://127.0.0.1:9999/3408.jpg","http://127.0.0.1:9999/3408.jpg"};
    String urls[]={"https://i.scdn.co/image/ab67706f00000003aa93fe4e8c2d24fc62556cba",
"https://i.scdn.co/image/ab67706f00000003aa93fe4e8c2d24fc62556cba","https://i.scdn.co/image/ab67706f00000003aa93fe4e8c2d24fc62556cba"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);
        ImageButton calendarButon=findViewById(R.id.calendar);
        calendarButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCalendar();
            }
        });
        ImageButton homeButton=findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHome();
            }
        });
        gridView = findViewById(R.id.grivViewResults);

        arrayList = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            imageModelDislikeLike imagemodel = new imageModelDislikeLike();
            //imagemodel.setmThumbIds(image[i]);
            imagemodel.setLink(spotLinks[i]);
            imagemodel.setImgAcc(urls[i]);
            //add in array list
            arrayList.add(imagemodel);
        }

        resultImagesAdapter adpter = new resultImagesAdapter(this, arrayList);
        gridView.setAdapter(adpter);
        //item click listner
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), ("direct to spotify link for..." + image[position]), Toast.LENGTH_SHORT).show();
//            }
//        });

        viewFlipper=findViewById(R.id.quoteSlider);
        for(int i=0;i<quotes.length;i++){
            flipperQuotes(quotes[i]);
        }

    }
//    public Bitmap LoadImageFromWebOperations(String url) {
//        try {
//            URL url2;
//            Bitmap bmp;
//            //InputStream in=url2.openStream();
//            //itmap bmp = BitmapFactory.decodeStream(in);
//            try {
//                url2 = new URL(url);
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url2.openStream()));
//                String stringBuffer;
//                String string = "";
//                while ((stringBuffer = bufferedReader.readLine()) != null){
//                    string = String.format("%s%s", string, stringBuffer);
//                }
//                bufferedReader.close();
//                result = string;
//            } catch (IOException e){
//                e.printStackTrace();
//                result = e.toString();
//            }
//            return null;
//
////            InputStream is = (InputStream) new URL(url).getContent();
////            Drawable d = Drawable.createFromStream(is, "src name");
//            return bmp;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    public void flipperQuotes(String quote){
        TextView textView=findViewById(R.id.textViewSlider);
        textView.setText(quote);

        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    public void toCalendar(){
        startActivity(new Intent(this, CalendarActivity.class));
    }
    public void toHome(){
        startActivity(new Intent(this, StartingPage.class));
    }
}

