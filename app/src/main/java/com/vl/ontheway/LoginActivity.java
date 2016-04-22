package com.vl.ontheway;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.vl.ontheway.components.CustomButton;
import com.vl.ontheway.components.CustomEditText;
import com.vl.ontheway.components.CustomTextView;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {

    private ProgressDialog mProgressDialog;
    Activity activity;

    CustomEditText pwd;
    CustomEditText user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        View userInclude = (View)findViewById(R.id.include_user);
        View paswdInclude = (View)findViewById(R.id.include_pswd);
        CustomButton sign_in = (CustomButton) findViewById(R.id.tv_sign_in);
        CustomTextView username_tv = (CustomTextView)userInclude.findViewById(R.id.fieldname_et);
        CustomTextView password_tv = (CustomTextView)paswdInclude.findViewById(R.id.fieldname_et);

        user_name = (CustomEditText)userInclude.findViewById(R.id.fieldvalue_et);
         pwd = (CustomEditText)paswdInclude.findViewById(R.id.fieldvalue_et);
        username_tv.setText("UserName");
        password_tv.setText("Password");
        user_name.setHint("UserName");
        pwd.setHint("Password");

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateUsers()) {
                    JSONObject jsonObject =  new JSONObject();
                    try {
                        jsonObject.put("userID",user_name.getText());
                        jsonObject.put("password",pwd.getText());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    requestLogin(jsonObject);
                }

            }
        });

    }

    private boolean ValidateUsers() {
        if(TextUtils.isEmpty(user_name.getText())) {
            Toast.makeText(this, "Please enter UserName.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(pwd.getText())) {
            Toast.makeText(this, "Please enter Password.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    private void requestLogin(JSONObject jsonObject) {
        final String BASE_URL = "http://202.89.107.47:8080/Spicejet/rest/login";
        Uri builtUri = Uri.parse(BASE_URL).buildUpon().build();
        String URL = builtUri.toString();
        Log.e("LOGIN_ACTIVITY", "URL " + BASE_URL);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    validate(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               System.out.println("Volley error " + error.toString());
                Toast.makeText(LoginActivity.this,""+error.toString(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestManager.getInstance(this.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void validate(JSONObject response) throws JSONException {
       String stsus = response.getString("Status").toString();
        if(stsus.contains("logged in successfully")){
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            intent.putExtra("UserId",response.getString("UserID").toString());
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this,""+stsus.toString(),Toast.LENGTH_SHORT).show();
        }
    }


}
