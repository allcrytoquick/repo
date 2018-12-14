package com.coinhub.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
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

public class SettingsActivity extends AppCompatActivity {
    /**
     * Field instances of all views and variables
     */
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * setting up the content view of this screen
         */
        setContentView(R.layout.activity_settings);

/**
 * type casting the all views
 */
        initialiseViews();

        /**
         * click listener of back button
         */
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * calling on back pressed method of this activity to finish this activity with animation
                 */
                SettingsActivity.super.onBackPressed();
            }
        });


    }

    /**
     * this method will type cast the all views of this screen
     */
    public void initialiseViews() {
        btnBack = (ImageButton) findViewById(R.id.btn_back);
    }


    /**
     * Demo API call for getting Settings Prefs information
     * just pass user id and changed json tags as your need.
     */

    private void apiCallForgettingSettingsPrefsInfo(final String userId) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "Put your get Settings Prefs Info API url here", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    /**
                     * demo json data parsing from Get Settings Prefs Info api success response
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
                         * if success then parsing all Settings Prefs info like below
                         * and after parsing just set the value to the views
                         */

                        String name = jsonObject.getString("name");
                        String country = jsonObject.getString("country");
                        String email = jsonObject.getString("email");
                        String phoneNumber = jsonObject.getString("phone_number");
                        boolean email_notif = jsonObject.getBoolean("email_notif");
                        boolean push_notif = jsonObject.getBoolean("push_notif");

                    }

                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /**
                 * checking for error message and then showing it in a toast
                 */
                if (error.getMessage() != null) {

                    Toast.makeText(SettingsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
