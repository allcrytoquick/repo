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
import android.widget.TextView;
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

public class SignupFragment extends Fragment {
    /**
     * field instance of all views
     */
    View rootView;
    TextView tvSignIn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * assigning the layout of sign up and type casting of all child views
         */
        rootView = inflater.inflate(R.layout.layout_signup, container, false);

        tvSignIn = (TextView) rootView.findViewById(R.id.tv_sign_in);
        /**
         * click listener of sign in text view
         */
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * popping the sign up fragment to show the sign in screen
                 */
                LoginActivity.manager.popBackStackImmediate();
            }
        });
        return rootView;
    }


    /**
     * Demo API call for signup
     * just pass user inputted information and changed json tags as your need.
     */

    private void apiCallForSignup(final String firstName, final String lastName, final String userName, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "Put your singup API url here", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    /**
                     * demo json data parsing from signup api success response
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
                         * do your task here if successfully signed up
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
                 * passing  given information to the API as key  value pair.if you want to add more parameter to the api then just put them below like others.
                 */
                params.put("firstName", firstName);
                params.put("lastName", lastName);
                params.put("userName", userName);
                params.put("password", password);
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
