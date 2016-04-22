package com.vl.ontheway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.vl.ontheway.components.CustomTextView;

public class FindFlightsActivity extends AppCompatActivity {

    ImageView back_button;
    TextView toolbar_title;

    CustomTextView fromtv_code ;
    CustomTextView from_sub_tv;

    CustomTextView totv_code;
    CustomTextView to_sub_tv;


    CustomTextView departTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_flights);

        back_button = (ImageView)findViewById(R.id.img_menu);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);

        toolbar_title.setText("Flight Booking");

        fromtv_code = (CustomTextView)findViewById(R.id.fromtv);
        from_sub_tv = (CustomTextView)findViewById(R.id.from_sub_tv);
        totv_code = (CustomTextView)findViewById(R.id.totv);
        to_sub_tv = (CustomTextView)findViewById(R.id.to_sub_tv);

        departTime = (CustomTextView)findViewById(R.id.departTime);



    }

}
