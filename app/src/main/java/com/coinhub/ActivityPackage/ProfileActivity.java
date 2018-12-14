package com.coinhub.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.coinhub.R;
import com.coinhub.UtilPackage.APIController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    /**
     * Field instances of views and variables
     */
    ImageButton btnBack;
    ImageView imageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * setting up the content view of this screen
         */
        setContentView(R.layout.activity_profile);

        /**
         * type casting the all views
         */
        initialiseViews();

        /**
         * click listener for back button
         */
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * calling on back pressed method of this activity to finish this activity with animation
                 */
                ProfileActivity.super.onBackPressed();
            }
        });
    }

    /**
     * this method will type cast the all views of this screen
     */
    public void initialiseViews() {
        imageViewProfile = (ImageView) findViewById(R.id.imageView_profile);
        btnBack = (ImageButton) findViewById(R.id.btn_back);

    }


    /**
     * Demo API call for getting profile information
     * just pass user id and changed json tags as your need.
     */

    private void apiCallForgettingProfileInfo(final String userId) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.bitquick.co/sell", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("TAG1234", "onResponse: " + response);

                    /**
                     * demo json data parsing from Get Profile Info api success response
                     */
                    /**
                     * converting the whole response to json object
                     */
                    JSONObject jsonObject = new JSONObject(response);
                    /**
                     * parsing  information from the converted json object
                     */
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {

                        /**
                         * if success then parsing all profile info like below
                         * and after parsing just set the value to the views
                         */

                        String name = jsonObject.getString("name");
                        String country = jsonObject.getString("country");
                        String followers = jsonObject.getString("followers");
                        String followings = jsonObject.getString("following");
                        String email = jsonObject.getString("email");
                        String phoneNumber = jsonObject.getString("phone_number");
                        String memberSince = jsonObject.getString("memberSince");

                    }

                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG1234", "onResponse: " + error.getMessage());
                /**
                 * checking for error message and then showing it in a toast
                 */
                if (error.getMessage() != null) {


                    Toast.makeText(ProfileActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                /**
                 * passing user id to the API as key  value pair.if you want to add more parameter to the api then just put them below like others.
                 */
                params.put("user_id", userId);
                return params;
            }
        };
        /**
         * setting retry policy if the connection fails
         */
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        /**
         * adding the request to the volley request queue.
         * this queue execute the API request synchronously
         */

        APIController.getInstance().addToRequestQueue(stringRequest, LoginActivity.class.getSimpleName());
    }


}
