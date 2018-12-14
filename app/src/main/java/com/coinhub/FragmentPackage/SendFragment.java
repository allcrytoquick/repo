package com.coinhub.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.coinhub.ActivityPackage.MainActivity;
import com.coinhub.R;
import com.coinhub.UtilPackage.APIController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by "MD.Ibrahim Khalil" on 30-Mar-18.
 */

public class SendFragment extends Fragment {
    /**
     * field instance of all views
     */
    View rootView;
    Button btnSend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * assigning the layout of send screen and type casting of all child views
         */
        rootView = inflater.inflate(R.layout.layout_send, container, false);
        /**
         * type casting the views
         */
        initialiseViews();

        /**
         * click listener of send now button
         */
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //api call here
            }
        });
        return rootView;
    }


    /**
     * click listener of send now button
     */
    public void initialiseViews() {
        btnSend = (Button) rootView.findViewById(R.id.btn_send_now);
    }


    /**
     * Demo API call for sending coin
     * just pass wallet id and userId and amount and changed json tags as your need.
     */
    private void apiCallForSendingCoin(final String walletId, final String userId, final String amount) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "Put your sending coin API url here", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    /**
                     * demo json data parsing from sending coin api success response
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
                         * if success then do your tasks.
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
                 * passing wallet id and user id and amount to the API as key value pair.if you want to add more parameter to the api then just put them below like others.
                 */
                params.put("wallet_id", walletId);
                params.put("user_id", userId);
                params.put("amount", amount);
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

        APIController.getInstance().addToRequestQueue(stringRequest, MainActivity.class.getSimpleName());
    }

}
