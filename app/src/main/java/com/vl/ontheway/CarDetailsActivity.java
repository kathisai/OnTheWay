package com.vl.ontheway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CarDetailsActivity extends AppCompatActivity {

    ImageView back_button;
    TextView toolbar_title;
    TextView car_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        back_button = (ImageView)findViewById(R.id.img_menu);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText("Car Details");
        car_number = (TextView)findViewById(R.id.car_number);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        String type = getIntent().getExtras().getString("Type");
        if(type.contains("single")){
            car_number.setText("123456");
        }else{
            car_number.setText("987456");
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
