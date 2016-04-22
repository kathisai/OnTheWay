package com.vl.ontheway;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class Orderfood extends AppCompatActivity {
    ImageView mImg_menu;
    TextView mToolbar_title;
    TextView TV_GoAhead;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderfood);

        mImg_menu = (ImageView) findViewById(R.id.img_menu);
        mToolbar_title = (TextView) findViewById(R.id.toolbar_title);
        TV_GoAhead = (TextView) findViewById(R.id.TV_GoAhead);
        mRecyclerView = (RecyclerView) findViewById(R.id.RV_Drinks);
        mImg_menu.setImageResource(R.drawable.back);
        mToolbar_title.setText("Hot Meals");

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        HotMealsAdapter mHotmealsAdapter = new HotMealsAdapter(Orderfood.this);
        mRecyclerView.setAdapter(mHotmealsAdapter);


        TV_GoAhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertdisplay();

            }
        });
        mImg_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void alertdisplay() {
        final Dialog dialog = new Dialog(Orderfood.this);

        LayoutInflater inflater = Orderfood.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_payment_alertdialog, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);

        CheckBox CB_spiceClubmem = (CheckBox) dialogView.findViewById(R.id.CB_SpiceCLub);
        CheckBox CB_PayOn = (CheckBox) dialogView.findViewById(R.id.CB_CashonPick);
        TextView TV_YoUAreDOne = (TextView)dialogView. findViewById(R.id.TV_YoUAreDOne);
        if (CB_spiceClubmem.isActivated()) {
            CB_PayOn.setActivated(false);
        }
        if (CB_PayOn.isActivated()) {
            CB_spiceClubmem.setActivated(false);
        }
        TV_YoUAreDOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
