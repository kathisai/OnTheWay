package com.vl.ontheway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vl.ontheway.components.CustomTextView;

public class GetARide extends AppCompatActivity implements View.OnClickListener {

    private CustomTextView departuretime;
    private CustomTextView location;
    private CustomTextView confirmBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_aride);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_get_aride, menu);
        departuretime = (CustomTextView) findViewById(R.id.depTime);
        location = (CustomTextView) findViewById(R.id.location);
        confirmBtn = (CustomTextView) findViewById(R.id.confirmTV);
        confirmBtn.setOnClickListener(this);
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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmTV:
        }
    }
}
