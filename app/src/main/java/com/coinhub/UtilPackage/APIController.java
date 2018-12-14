package com.coinhub.UtilPackage;
/**
 * All required libraries imported here
 */


import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by MD.Ibrahim Khalil on 30-Oct-17.
 */
public class APIController extends Application {
    /**
     * Field instances of views and variables
     */
    public static final String TAG = APIController.class
            .getSimpleName();
    private RequestQueue mRequestQueue;

    private static APIController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * assigning this to the api controller
         */
        mInstance = this;

    }

    /**
     * creating synchronized mehtod for retiring instance of this class
     *
     * @return
     */
    public static synchronized APIController getInstance() {
        return mInstance;
    }

    /**
     * creating volley request queue and returning for add new request
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    /**
     * adding request to the volley request queue to execute the API call
     *
     * @param req
     * @param tag
     * @param <T>
     */

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
}
