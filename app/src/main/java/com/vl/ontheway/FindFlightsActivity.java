package com.vl.ontheway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vl.ontheway.components.CustomTextView;

import org.json.JSONException;
import org.json.JSONObject;

public class FindFlightsActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView back_button;
    TextView toolbar_title;

    CustomTextView fromtv_code ;
    CustomTextView from_sub_tv;

    CustomTextView totv_code;
    CustomTextView to_sub_tv;


    CustomTextView departTime;
    TextView take_a_ride;
    String Userid;

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
        take_a_ride = (TextView)findViewById(R.id.take_a_ride);
        take_a_ride.setOnClickListener(this);
        Userid = getIntent().getExtras().getString("UserId");
        try {
            preloadData();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void preloadData() throws JSONException {
        final String BASE_URL = "http://202.89.107.47:8080/Spicejet/rest/getdetailsofuser";
        Uri builtUri = Uri.parse(BASE_URL).buildUpon().build();
        String URL = builtUri.toString();
        Log.e("LOGIN_ACTIVITY", "URL " + BASE_URL);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userID", Userid);
        Log.e("GETDEATILS",""+jsonObject.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    UpdateUi(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley error " + error.toString());
                Toast.makeText(FindFlightsActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestManager.getInstance(this.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void UpdateUi(JSONObject response) throws JSONException {
        String souceCode = response.getString("souceCode");
        String DestinatioCode = response.getString("DestinatioCode");
        String Sourcename = response.getString("Sourcename");
        String Locaiton = response.getString("Locaiton");
        String Departuretimedate = response.getString("Departuretimedate ");
        String Destinationname = response.getString("Destinationname");

        fromtv_code.setText(souceCode);
        from_sub_tv.setText(Sourcename);
        totv_code.setText(DestinatioCode);
        to_sub_tv.setText(Destinationname);
        departTime.setText(Departuretimedate);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_a_ride:
                Intent intent = new Intent(FindFlightsActivity.this,GetARide.class);
                startActivity(intent);
                break;

        }
    }
}
