package com.vl.ontheway;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        findViewById(R.id.btn_flight_booking).setOnClickListener(this);
        findViewById(R.id.btn_check_in).setOnClickListener(this);
        findViewById(R.id.btn_my_booking).setOnClickListener(this);
        findViewById(R.id.btn_hot_deals).setOnClickListener(this);

        startAnimation();
    }

    private void startAnimation() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SlideToDown(R.id.home_btn_ll, R.id.btn_my_booking);
            }
        }, 1000);
    }
    private void SlideToDown(int currentAnimatingViewId, final int nextAnimatingViewId) {
        Animation slide = AnimationUtils.loadAnimation(this, R.anim.slide_from_top);

        final View currentAnimatingView = findViewById(currentAnimatingViewId);

        int originalPos[] = new int[2];
        currentAnimatingView.getLocationOnScreen(originalPos);

		/* float startPos = 0-originalPos[1]; */
        // float startPos = -200;

        // slide = new TranslateAnimation(Animation.ABSOLUTE, 0.0f,
        // Animation.ABSOLUTE, 0.0f, Animation.ABSOLUTE,
        // startPos, Animation.ABSOLUTE, 0.0f);
        // slide.setDuration(750);

        currentAnimatingView.startAnimation(slide);
        currentAnimatingView.setVisibility(View.VISIBLE);

        slide.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // currentAnimatingView.clearAnimation();

                // if(nextAnimatingViewId == -1)
                // ;
                // else if(nextAnimatingViewId == R.id.btn_my_booking)
                // SlideToDown(nextAnimatingViewId, R.id.btn_flight_status);
                // else if(nextAnimatingViewId == R.id.btn_flight_status)
                // SlideToDown(nextAnimatingViewId, R.id.btn_hot_deals);
                // else if(nextAnimatingViewId == R.id.btn_hot_deals)
                // SlideToDown(nextAnimatingViewId, -1);

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_flight_booking:
                Intent intent_find_flight = new Intent(HomeActivity.this,FindFlightsActivity.class);
                startActivity(intent_find_flight);
                break;

            case R.id.btn_my_booking:
                Intent intent = new Intent(HomeActivity.this,GetARide.class);
                startActivity(intent);
                break;

           /* case R.id.btn_flight_booking:

            case R.id.btn_flight_booking:
*/

        }
    }
}
