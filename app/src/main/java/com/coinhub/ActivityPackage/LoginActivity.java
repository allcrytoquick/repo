package com.coinhub.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.coinhub.FragmentPackage.ForgotPasswordFragment;
import com.coinhub.FragmentPackage.SignupFragment;
import com.coinhub.R;
import com.coinhub.UtilPackage.APIController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    /**
     * Field instances of views and variables
     */
    TextView tvRegister;
    public static FragmentManager manager;
    TextView tvForgotPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * setting up the content view of this screen
         */
        setContentView(R.layout.activity_login);
        /**
         * assigning the support fragment manager for future fragment replacement from any where
         */
        manager = getSupportFragmentManager();
        /**
         * Type casting of all views of login screen
         */
        initialiseViews();

/**
 * click listener of Register text which will redirect to the sign up screen
 */
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * replacing the login screen with sign up fragment screen here #fragment_container is the id of root layout of content main to
                 * change its content to fragment's content
                 */
                manager.beginTransaction().replace(R.id.fragment_container, new SignupFragment()).addToBackStack(null).commit();
            }
        });

        /**
         * click listener of forgot password text which will redirect to the forgot password screen
         */
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * replacing the login screen with forgot password fragment screen here #fragment_container is the id of root layout of content main to
                 * change its content to fragment's content
                 */
                manager.beginTransaction().replace(R.id.fragment_container, new ForgotPasswordFragment()).addToBackStack(null).commit();
            }
        });


        /**
         * click listener for login button which will call api for login and if login succeed then will redirecet to main transaction
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * starting the main screen
                 */
                startActivity(new Intent(LoginActivity.this, MainActivity.class));


                /**
                 * demo login API call on loigin button click
                 */
                //  apiCallForLogin("email","password");
            }
        });

    }

    /**
     * this method will type cast the all views of this screen
     */
    public void initialiseViews() {
        tvRegister = (TextView) findViewById(R.id.tv_register);
        tvForgotPassword = (TextView) findViewById(R.id.tv_forgot_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
    }


    /**
     * Demo API call for login
     * just pass email and password and changed json tags as your need.
     */

    private void apiCallForLogin(final String emailText, final String passwordText) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "Put your login API url here", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    /**
                     * demo json data parsing from login api success response
                     */
                    /**
                     * converting the whole response to json object
                     */
                    JSONObject jsonObject = new JSONObject(response);
                    /**
                     * parsing personal information from the converted json object
                     */
                    String firstname = jsonObject.getString("first_name");
                    String lastname = jsonObject.getString("last_name");
                    String dob = jsonObject.getString("dob");
                    String sex = jsonObject.getString("sex");
                    String age = jsonObject.getString("age");


                    /**
                     * do your task here after successfull login and parsing json data
                     */


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

                    Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new Hashtable<String, String>();
                /**
                 * passing  given email and password to the API as key  value pair.if you want to add more parameter to the api then just put them below like others.
                 */
                params.put("email", emailText);
                params.put("password", passwordText);


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
