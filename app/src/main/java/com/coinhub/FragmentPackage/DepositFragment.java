package com.coinhub.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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
import com.coinhub.ActivityPackage.MainActivity;
import com.coinhub.R;
import com.coinhub.UtilPackage.APIController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by "MD.Ibrahim Khalil" on 30-Mar-18.
 */

public class DepositFragment extends Fragment {
    /**
     * Field instances of all views and variables
     */
    View rootView;
    TextView tvTerms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * setting up the deposit layout to this fragment
         */
        rootView = inflater.inflate(R.layout.layout_deposit, container, false);
        /**
         * type casting views
         */
        initializeViews();

        /**
         * setting terms and condition text clickable and setting movement method for open in browser
         */
        tvTerms.setClickable(true);
        tvTerms.setMovementMethod(new LinkMovementMethod());

        /**
         * setting terms and condition for deposit with web link
         */
        if (Build.VERSION.SDK_INT >= N) {
            /**
             * setting link text color
             */
            tvTerms.setLinkTextColor(Color.BLUE);

            /**
             * terms and condition text for deposit
             * replace this link with your link
             */
            tvTerms.setText(Html.fromHtml(getString(R.string.before_you) + " " + "<a href= https://www.example.com >" + "<font color = red >" + getString(R.string.terms) + "</font>" + "</a>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            /**
             * setting link text color
             */
            tvTerms.setLinkTextColor(Color.BLUE);
            /**
             * terms and condition text for deposit
             * replace this link with your link
             */
            tvTerms.setText(Html.fromHtml(getString(R.string.before_you) + " " + "<a href= https://www.example.com >" + "<font color = red >" + getString(R.string.terms) + "</font>" + "</a>"));

        }
        return rootView;
    }

    /**
     * this method will type cast the all views of this screen
     */

    public void initializeViews() {
        tvTerms = (TextView) rootView.findViewById(R.id.tv_terms);
    }


    /**
     * Demo API call for Depositing coin
     * just pass wallet id and userId and amount and bankId and changed json tags as your need.
     */
    private void apiCallForDepositingCoin(final String walletId, final String userId, final String amount, final String bankId) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "Put your depositing coin API url here", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    /**
                     * demo json data parsing from depositing coin api success response
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
                 * passing wallet id and user id and amount and bank id to the API as key value pair.if you want to add more parameter to the api then just put them below like others.
                 */
                params.put("wallet_id", walletId);
                params.put("user_id", userId);
                params.put("amount", amount);
                params.put("bank_id", bankId);
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
