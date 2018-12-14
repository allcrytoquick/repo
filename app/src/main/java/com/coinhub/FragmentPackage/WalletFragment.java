package com.coinhub.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.coinhub.ActivityPackage.MainActivity;
import com.coinhub.AdapterPackage.NavigationDrawerSingleItemAdapter;
import com.coinhub.AdapterPackage.TransactionHistoryAdapter;
import com.coinhub.DataModelPackage.TransactionModel;
import com.coinhub.R;
import com.coinhub.UtilPackage.APIController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

/**
 * Created by "MD.Ibrahim Khalil" on 28-Mar-18.
 */

public class WalletFragment extends Fragment {
    /**
     * field instance of all views and variables
     */
    View rootView;
    public static TextView tvAmount, tvCoinName;
    RecyclerView recyclerViewTransactionList;
    Button btn_send_or_deposit;
    public static boolean isFromCurrecy = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * assigning the layout of wallet screen and type casting of all child views
         */
        rootView = inflater.inflate(R.layout.layout_wallet, container, false);

        /**
         * type casting the all views
         */

        initializeViews();
/**
 * checking if this is come from currency or coin
 * if currency then setting text tp #Deposit
 * else setting text too send
 */
        if (isFromCurrecy) {
            btn_send_or_deposit.setText("Deposit");
        } else {
            btn_send_or_deposit.setText("Send");
        }

        /**
         * setting up the recycler view with the linear layout manager
         */

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewTransactionList.setLayoutManager(linearLayoutManager);
        /**
         * setting up demo data to text views and recycler view
         */
        tvCoinName.setText(NavigationDrawerSingleItemAdapter.CoinName);
        tvAmount.setText(new Random().nextInt(10000) + "");
        /**
         * adding demo data to arraylist by looping
         */
        ArrayList<TransactionModel> transactionModels = new ArrayList<TransactionModel>();
        for (int i = 0; i < 50; i++) {
            if (i <= 25) {
                transactionModels.add(new TransactionModel(0, "2.333" + i, "50.365" + i, "25987446213" + i));
            } else {
                transactionModels.add(new TransactionModel(1, "2.333" + i, "50.365" + i, "25987446213" + i));
            }

        }
        /**
         * setting up recycler view with adapter by using this demo data array list
         */
        recyclerViewTransactionList.setAdapter(new TransactionHistoryAdapter(getActivity(), transactionModels));

/**
 * click listener of send or deposit button
 */
        btn_send_or_deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * checking if it is from currency then launching deposit screen
                 * else launching send fragment
                 */
                if (isFromCurrecy) {
                    MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new DepositFragment()).addToBackStack(null).commit();
                } else {
                    MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new SendFragment()).addToBackStack(null).commit();

                }
            }
        });


        return rootView;
    }

    /**
     * this method will initialise all views
     */
    public void initializeViews() {
        tvAmount = (TextView) rootView.findViewById(R.id.tv_amount);
        tvCoinName = (TextView) rootView.findViewById(R.id.tv_coin_name);
        btn_send_or_deposit = (Button) rootView.findViewById(R.id.btn_send_or_deposit);
        recyclerViewTransactionList = (RecyclerView) rootView.findViewById(R.id.recycler_transaction_list);

    }


    /**
     * Demo API call for getting last month transaction history list
     * just pass user id and changed json tags as your need.
     */

    private void apiCallForGettingLastMonthTransactionList(final String userId) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "Put your get Last Month Transaction List API url here", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    /**
                     * demo json data parsing from Get Last Month Transaction List api success response
                     */
                    /**
                     * converting the whole response to json object
                     */
                    JSONObject jsonObject = new JSONObject(response);
                    /**
                     * parsing transaction list data from the converted json object
                     */
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        /**
                         * creating json array by tag as this should be an array
                         */
                        JSONArray transactionListArray = jsonObject.getJSONArray("last_month_transaction_list_array");
                        ArrayList<TransactionModel> transactionModels = new ArrayList<TransactionModel>();
                        for (int i = 0; i < transactionListArray.length(); i++) {
                            /**
                             * creating inner json object and parsing the data and adding to the transactionModels array list
                             */
                            JSONObject innerObject = transactionListArray.getJSONObject(i);
                            int type = innerObject.getInt("type");
                            String amount = innerObject.getString("amount");
                            String total = innerObject.getString("total");
                            String cardNo = innerObject.getString("card_no");
                            transactionModels.add(new TransactionModel(type, amount, total, cardNo));
                        }

                        /**
                         * setting up recycler view with adapter by using this demo data array list
                         */
                        recyclerViewTransactionList.setAdapter(new TransactionHistoryAdapter(getActivity(), transactionModels));
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

        APIController.getInstance().addToRequestQueue(stringRequest, MainActivity.class.getSimpleName());
    }


}
