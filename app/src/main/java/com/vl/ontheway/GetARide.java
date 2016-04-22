package com.vl.ontheway;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class GetARide extends AppCompatActivity {

    private TextView departuretime;
    private TextView location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_aride);
        getAcarAlert();

    }

    public void getAcarAlert(){
        final Dialog dialog = new Dialog(GetARide.this);

        LayoutInflater inflater = GetARide.this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_car_pulling, null);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
        Button mOwnACar=(Button)findViewById(R.id.BT_OwnCar);
        Button mGroupCar=(Button)findViewById(R.id.BT_GroupCar);



        mOwnACar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 4/22/2016  server hit to get push notification with car details

                dialog.dismiss();
            }
        });
        mGroupCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_get_aride, menu);
        departuretime = (TextView) findViewById(R.id.depTime);
        location = (TextView) findViewById(R.id.location);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
