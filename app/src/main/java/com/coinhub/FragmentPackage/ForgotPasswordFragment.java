package com.coinhub.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.coinhub.ActivityPackage.LoginActivity;
import com.coinhub.R;
import com.coinhub.UtilPackage.APIController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by "MD.Ibrahim Khalil" on 14-Mar-18.
 */

public class ForgotPasswordFragment extends Fragment {

    /**
     * field instance of all views
     */
    View rootView;

    Button btnChangePassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * assigning the layout of forgot password and type casting of all child views
         */
        rootView = inflater.inflate(R.layout.layout_forgot_password, container, false);
        /**
         * type casting the all views
         */
        initialiseViews();
        /**
         * click listener of change password button
         */
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //api call
            }
        });
        return rootView;
    }

    /**
     * this method will type cast the all views of this screen
     */

    public void initialiseViews() {
        btnChangePassword = (Button) rootView.findViewById(R.id.btn_change_password);
    }


    /**
     * Demo API call for forgot password
     * just pass user inputted information and changed json tags as your need.
     */

    private void apiCallForforgotPassword(final String email) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "Put your forgot password API url here", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    /**
                     * demo json data parsing from forgot password api success response
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
                         * do your task here if successful
                         */
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

                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                /**
                 * passing  given email to the API as key  value pair.if you want to add more parameter to the api then just put them below like others.
                 */

                params.put("email", email);
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
