package com.vl.ontheway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseYourRide extends AppCompatActivity {

    ImageView back_button;
    TextView toolbar_title;

    TextView singlecar;
    TextView groupCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_your_ride);

        back_button = (ImageView)findViewById(R.id.img_menu);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);


        singlecar = (TextView)findViewById(R.id.singlecar);
        groupCar = (TextView)findViewById(R.id.groupCar);

        singlecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseYourRide.this,CarDetailsActivity.class);
                intent.putExtra("Type","single");
                startActivity(intent);
            }
        });
        groupCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseYourRide.this,GetARide.class);
                intent.putExtra("Type","group");
                startActivity(intent);
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
