package com.coinhub.UtilPackage;
/**
 * all required libraries imported here
 */

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;

/**
 * Created by "MD.Ibrahim Khalil" on transactions-Jan-18.
 */
public class StatusBarView extends View {
    /**
     * Field instances of variables
     */
    private int mStatusBarHeight;

    /**
     * constructor for getting current context
     *
     * @param context
     */
    public StatusBarView(Context context) {
        this(context, null);

    }

    /**
     * constructor for getting current context and attribute
     * and setting the layout full screen
     *
     * @param context
     * @param attrs
     */
    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSystemUiVisibility(SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }


    /**
     * if build version sdk greater than or equal to lollipop then the status bar will be consumed
     *
     * @param insets
     * @return
     */

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mStatusBarHeight = insets.getSystemWindowInsetTop();
            return insets.consumeSystemWindowInsets();
        }
        return insets;
    }

    /**
     * this method will called on measure the status bar
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), mStatusBarHeight);
    }
}