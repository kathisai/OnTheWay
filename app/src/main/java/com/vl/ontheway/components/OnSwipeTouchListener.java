package com.vl.ontheway.components;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewAnimator;

import com.vl.ontheway.components.util.Util;


public class OnSwipeTouchListener implements OnTouchListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private final GestureDetector mGestureDetector;
    private ImageView mEditIV;
    private ViewAnimator mFlipper;
    private LinearLayout mTickLL;

    public OnSwipeTouchListener(Activity ctx, ImageView editBtn, ViewAnimator flipper) {
        mFlipper = flipper;
        mEditIV = editBtn;
        mGestureDetector = new GestureDetector(ctx, new MyGestureListener());
//		mFlipper.setVisibility(View.VISIBLE);
//		mTickLL.setVisibility(View.GONE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    /**
     * This gesture is used show and hide edit button with animation
     *
     * @author akurella
     */
    public class MyGestureListener extends SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        mFlipper.setInAnimation(Util.inFromLeftAnimation());
                        mFlipper.setOutAnimation(Util.outToRightAnimation());
                        mFlipper.showPrevious();
                        mFlipper.setVisibility(View.VISIBLE);
                        mEditIV.setVisibility(View.GONE);
//                	  mTickLL.setVisibility(View.GONE);
                    } else {
                        mFlipper.setVisibility(View.VISIBLE);
                        mEditIV.setVisibility(View.VISIBLE);
//                	  mTickLL.setVisibility(View.GONE);
                        mFlipper.setInAnimation(Util.inFromRightAnimation());
                        mFlipper.setOutAnimation(Util.outToLeftAnimation());
                        mFlipper.showNext();
                    }
                }
            }
            return true;
        }

    }
}