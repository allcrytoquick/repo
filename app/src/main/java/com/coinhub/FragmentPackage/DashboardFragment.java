package com.coinhub.FragmentPackage;
/**
 * all required libraries imported here
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.coinhub.ActivityPackage.LoginActivity;
import com.coinhub.R;
import com.coinhub.UtilPackage.APIController;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;


/**
 * Created by "MD.Ibrahim Khalil" on 29-Mar-18.
 */

public class DashboardFragment extends Fragment {
    /**
     * Field instances of all views and variables
     */

    View rootView;
    public Button btn_btc, btn_eth, btn_ltc;
    private Button btn_one_hour, btn_one_day, btn_one_month, btn_one_week, btn_one_year,
            btn_two_years, btn_three_years;


    TextView tvCoinName;
    private ValueLineChart mCubicValueLineChart;
    Button btnBuyOrder, btnSellOrder;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * setting up the dashboard layout to this fragment
         */
        rootView = inflater.inflate(R.layout.layout_dashboard, container, false);

        /**
         * type casting of all views
         */
        initializeViews();

        /**
         * setting up the line chart data
         * here 0 means week and 1 means month
         */
        setupLineChartData(0);


        /**
         * setting default things of this layout
         */
        //setting buy order button background
        btnBuyOrder.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_buy_sell_button_bg_selected));
        //setting sell order button background
        btnSellOrder.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_buy_sell_button_bg_normal));
        //setting sell order button text color
        btnSellOrder.setTextColor(Color.parseColor("#E57616"));
        //setting buy order button text color
        btnBuyOrder.setTextColor(Color.parseColor("#ffffff"));
        return rootView;
    }


    /**
     * tab click listener
     * when user click on any tab  then the #tab_Onclick method of main activity will be called and from there this method will be call with view parameter
     * and the respective task will be completed here
     *
     * @param v
     */

    public void tab_Onclick(View v) {
        switch (v.getId()) {
            /**
             * btc tab click listener
             */
            case R.id.ic_dash_btn_btc:
                /**
                 * changing background of all tabs
                 * setting text to coin name textview
                 */
                btn_btc.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_pressed));
                btn_eth.setBackgroundColor(Color.parseColor("#00000000"));
                btn_ltc.setBackgroundColor(Color.parseColor("#00000000"));

                tvCoinName.setText("bitcoin price");
                break;

            /**
             * eth tab click listener
             */
            case R.id.ic_dash_btn_eth:
                /**
                 * changing background of all tabs
                 * setting text to coin name textview
                 */

                btn_eth.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_pressed));
                btn_btc.setBackgroundColor(Color.parseColor("#00000000"));
                btn_ltc.setBackgroundColor(Color.parseColor("#00000000"));


                tvCoinName.setText("ethereum price");
                break;
            /**
             * ltc tab click listener
             */
            case R.id.ic_dash_btn_ltc:

                /**
                 * changing background of all tabs
                 * setting text to coin name textview
                 */

                btn_ltc.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_pressed));
                btn_btc.setBackgroundColor(Color.parseColor("#00000000"));
                btn_eth.setBackgroundColor(Color.parseColor("#00000000"));


                tvCoinName.setText("litecoin price");
                break;

        }
    }


    /**
     * button click listener
     * when user click on any button then the #btn_onClick method of main activity will be called and from there this method will be call with view parameter
     * and the respective task will be completed here
     *
     * @param v
     */

    public void btn_onClick(View v) {

        switch (v.getId()) {
            /**
             * one hour button click listener
             */

            case R.id.btn_one_hour: {

                /**
                 * changing the all button's background of line chart
                 * if you want to call api to get data from server then call here the api
                 */
                btn_one_hour.setBackgroundResource(R.drawable.button_border);
                btn_one_day.setBackgroundColor(Color.TRANSPARENT);
                btn_one_week.setBackgroundColor(Color.TRANSPARENT);
                btn_one_year.setBackgroundColor(Color.TRANSPARENT);
                btn_two_years.setBackgroundColor(Color.TRANSPARENT);
                btn_three_years.setBackgroundColor(Color.TRANSPARENT);
                btn_one_month.setBackgroundColor(Color.TRANSPARENT);
//API Call here

                break;
            }
            /**
             * one day button click listener
             */

            case R.id.btn_one_day: {
                /**
                 * changing the all button's background of line chart
                 * if you want to call api to get data from server then call here the api
                 */
                btn_one_hour.setBackgroundColor(Color.TRANSPARENT);
                btn_one_day.setBackgroundResource(R.drawable.button_border);
                btn_one_week.setBackgroundColor(Color.TRANSPARENT);
                btn_one_year.setBackgroundColor(Color.TRANSPARENT);
                btn_two_years.setBackgroundColor(Color.TRANSPARENT);
                btn_three_years.setBackgroundColor(Color.TRANSPARENT);
                btn_one_month.setBackgroundColor(Color.TRANSPARENT);


                break;
            }
            /**
             * one week button click listener
             */

            case R.id.btn_one_week: {
                /**
                 * changing the all button's background of line chart
                 * if you want to call api to get data from server then call here the api
                 */
                btn_one_hour.setBackgroundColor(Color.TRANSPARENT);
                btn_one_day.setBackgroundColor(Color.TRANSPARENT);
                btn_one_week.setBackgroundResource(R.drawable.button_border);
                btn_one_year.setBackgroundColor(Color.TRANSPARENT);
                btn_two_years.setBackgroundColor(Color.TRANSPARENT);
                btn_three_years.setBackgroundColor(Color.TRANSPARENT);
                btn_one_month.setBackgroundColor(Color.TRANSPARENT);
/**
 * setting up one week line chart data
 * here 0 mean month
 */
                setupLineChartData(0);


                break;
            }
            /**
             * one month button click listener
             */

            case R.id.btn_one_month: {

                /**
                 * changing the all button's background of line chart
                 * if you want to call api to get data from server then call here the api
                 */
                btn_one_hour.setBackgroundColor(Color.TRANSPARENT);
                btn_one_day.setBackgroundColor(Color.TRANSPARENT);
                btn_one_week.setBackgroundColor(Color.TRANSPARENT);
                btn_one_month.setBackgroundResource(R.drawable.button_border);
                btn_two_years.setBackgroundColor(Color.TRANSPARENT);
                btn_three_years.setBackgroundColor(Color.TRANSPARENT);
/**
 * setting up one month line chart data
 * here 1 mean month
 */
                setupLineChartData(1);


                break;
            }
            /**
             * one year button click listener
             */

            case R.id.btn_one_year: {
                /**
                 * changing the all button's background of line chart
                 * if you want to call api to get data from server then call here the api
                 */

                btn_one_hour.setBackgroundColor(Color.TRANSPARENT);
                btn_one_day.setBackgroundColor(Color.TRANSPARENT);
                btn_one_week.setBackgroundColor(Color.TRANSPARENT);
                btn_one_year.setBackgroundResource(R.drawable.button_border);
                btn_two_years.setBackgroundColor(Color.TRANSPARENT);
                btn_three_years.setBackgroundColor(Color.TRANSPARENT);
                btn_one_month.setBackgroundColor(Color.TRANSPARENT);


                break;
            }
            /**
             * two years button click listener
             */

            case R.id.btn_two_years: {
                /**
                 * changing the all button's background of line chart
                 * if you want to call api to get data from server then call here the api
                 */

                btn_one_hour.setBackgroundColor(Color.TRANSPARENT);
                btn_one_day.setBackgroundColor(Color.TRANSPARENT);
                btn_one_week.setBackgroundColor(Color.TRANSPARENT);
                btn_one_year.setBackgroundColor(Color.TRANSPARENT);
                btn_two_years.setBackgroundResource(R.drawable.button_border);
                btn_three_years.setBackgroundColor(Color.TRANSPARENT);
                btn_one_month.setBackgroundColor(Color.TRANSPARENT);
                break;
            }
            /**
             * three hours button click listener
             */

            case R.id.btn_three_years: {
                /**
                 * changing the all button's background of line chart
                 * if you want to call api to get data from server then call here the api
                 */
                btn_one_hour.setBackgroundColor(Color.TRANSPARENT);
                btn_one_day.setBackgroundColor(Color.TRANSPARENT);
                btn_one_week.setBackgroundColor(Color.TRANSPARENT);
                btn_one_year.setBackgroundColor(Color.TRANSPARENT);
                btn_two_years.setBackgroundColor(Color.TRANSPARENT);
                btn_three_years.setBackgroundResource(R.drawable.button_border);
                btn_one_month.setBackgroundColor(Color.TRANSPARENT);
                break;
            }
            /**
             * buy order button click listener
             */

            case R.id.btn_buy_order: {
                /**
                 * changing the buy and sell order button's background
                 * if you want to call api to get data from server then call here the api
                 */
                btnBuyOrder.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_buy_sell_button_bg_selected));
                btnSellOrder.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_buy_sell_button_bg_normal));
                btnSellOrder.setTextColor(Color.parseColor("#E57616"));
                btnBuyOrder.setTextColor(Color.parseColor("#ffffff"));

                break;
            }

            /**
             * sell order button click listener
             */

            case R.id.btn_sell_order: {

                /**
                 * changing the buy and sell order button's background
                 * if you want to call api to get data from server then call here the api
                 */

                btnSellOrder.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_buy_sell_button_bg_selected));
                btnBuyOrder.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.dashboard_buy_sell_button_bg_normal));

                btnBuyOrder.setTextColor(Color.parseColor("#E57616"));
                btnSellOrder.setTextColor(Color.parseColor("#ffffff"));
            }
        }
    }

    /**
     * this method will setup the line chart data for one month and one week
     *
     * @param type
     */

    public void setupLineChartData(int type) {
        /**
         * clearing the line chart data first
         */
        mCubicValueLineChart.clearChart();
        /**
         * value line series for adding value with time or date
         */
        ValueLineSeries series = new ValueLineSeries();

        /**
         * setting line color
         */
        series.setColor(0xFF56B7F1);
/**
 * here 0 mean week
 * so if week then adding data to series with day names
 */
        if (type == 0) {
            /**
             * adding data with day names and values
             */
            series.addPoint(new ValueLinePoint("Sat", 2.4f));
            series.addPoint(new ValueLinePoint("Sun", 3.4f));
            series.addPoint(new ValueLinePoint("Mon", 1.0f));
            series.addPoint(new ValueLinePoint("Tue", 1.2f));
            series.addPoint(new ValueLinePoint("Wed", 2.6f));
            series.addPoint(new ValueLinePoint("Thr", 1.0f));
            series.addPoint(new ValueLinePoint("Fri", 3.5f));
            /**
             * add total series to the line chart and starting the line chart animation
             */
            mCubicValueLineChart.addSeries(series);
            mCubicValueLineChart.startAnimation();
        }
        /**
         * here 1 mean month
         * so if month then adding data to series with month names
         */
        else if (type == 1) {
            /**
             * adding data with month names and values
             */
            series.addPoint(new ValueLinePoint("Jan", 2.4f));
            series.addPoint(new ValueLinePoint("Feb", 3.4f));
            series.addPoint(new ValueLinePoint("Mar", 1.4f));
            series.addPoint(new ValueLinePoint("Apr", 1.2f));
            series.addPoint(new ValueLinePoint("Mai", 2.6f));
            series.addPoint(new ValueLinePoint("Jun", 1.0f));
            series.addPoint(new ValueLinePoint("Jul", 3.5f));
            series.addPoint(new ValueLinePoint("Aug", 2.4f));
            series.addPoint(new ValueLinePoint("Sep", 2.4f));
            series.addPoint(new ValueLinePoint("Oct", 3.4f));
            series.addPoint(new ValueLinePoint("Nov", 1.4f));
            series.addPoint(new ValueLinePoint("Dec", 1.3f));
            /**
             * add total series to the line chart and starting the line chart animation
             */
            mCubicValueLineChart.addSeries(series);
            mCubicValueLineChart.startAnimation();
        }
    }

    /**
     * this method will type cast the all views of this screen
     */

    public void initializeViews() {
        btn_btc = (Button) rootView.findViewById(R.id.ic_dash_btn_btc);
        btn_eth = (Button) rootView.findViewById(R.id.ic_dash_btn_eth);
        btn_ltc = (Button) rootView.findViewById(R.id.ic_dash_btn_ltc);
        btn_one_hour = (Button) rootView.findViewById(R.id.btn_one_hour);
        btn_one_day = (Button) rootView.findViewById(R.id.btn_one_day);
        btn_one_week = (Button) rootView.findViewById(R.id.btn_one_week);
        btn_one_year = (Button) rootView.findViewById(R.id.btn_one_year);
        btn_one_month = (Button) rootView.findViewById(R.id.btn_one_month);
        btn_two_years = (Button) rootView.findViewById(R.id.btn_two_years);
        tvCoinName = (TextView) rootView.findViewById(R.id.tv_coin_name);
        btnBuyOrder = (Button) rootView.findViewById(R.id.btn_buy_order);
        btnSellOrder = (Button) rootView.findViewById(R.id.btn_sell_order);
        btn_three_years = (Button) rootView.findViewById(R.id.btn_three_years);
        mCubicValueLineChart = (ValueLineChart) rootView.findViewById(R.id.linechart);
    }


    /**
     * Demo API call for getting dashboard data
     * just pass filter type and coin Name and changed json tags as your need.
     */

    private void apiCallForgettingDashboardData(final String filerType, final String coinName) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "Put your get Dashboard Data API url here", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    /**
                     * demo json data parsing from Get Dashboard Data api success response
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
                         * if success then parsing all dashboard data like below
                         * and after parsing just set the value to the views
                         */


                        String sinceLastMonth = jsonObject.getString("since_last_month");

                        String marketPrediction = jsonObject.getString("market_prediction");

                        String coinPrice = jsonObject.getString("coin_price");

                        String currentDate = jsonObject.getString("current_date");


                        /**
                         * value line series for adding value with time or date
                         */

                        ValueLineSeries series = new ValueLineSeries();
                        /**
                         * clearing the line chart data first
                         */
                        mCubicValueLineChart.clearChart();

                        /**
                         * setting line color
                         */
                        series.setColor(0xFF56B7F1);

                        /**
                         * getting the line chart data json array as line chart data should be a list of data
                         * iterating the json array and getting the date and value of each respective position
                         * creating a date by converting the gotten date to millisecond by multiplying
                         * creating a calender object by current instance
                         * setting the date to the calender's current date so that we can get the day name and also other reuired information of that respective date from calender
                         * adding the date and value to the series by getting the day name from the calender.
                         */
                        JSONArray array = jsonObject.getJSONArray("line_chart_data");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object1 = array.getJSONObject(i);
                            String date = object1.getString("date");
                            String value = object1.getString("value");
                            Date date1 = new Date(Long.parseLong(date) * 1000);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date1);
                            /**
                             * here you have to check the filter type the user selected like 1hour, 1day, 1month etc.
                             * And after that you have to make the date like that and add to the series
                             * In below the 1week is done.
                             * adding data with day names and values
                             */
                            series.addPoint(new ValueLinePoint(String.valueOf(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH)).substring(0, 2), Float.parseFloat(value.trim())));
                        }


                        /**
                         * add total series to the line chart
                         * and starting the line chart animation
                         */
                        mCubicValueLineChart.addSeries(series);
                        mCubicValueLineChart.startAnimation();
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
                 * passing filter type and coinName to the API as key  value pair.if you want to add more parameter to the api then just put them below like others.
                 */
                params.put("filterType", filerType);
                params.put("coinName", coinName);
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
