package com.coinhub.AdapterPackage;
/**
 * all required libraries imported here
 */

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinhub.ActivityPackage.MainActivity;
import com.coinhub.DataModelPackage.MenuExpandedModel;
import com.coinhub.FragmentPackage.BuyFragment;
import com.coinhub.FragmentPackage.SellFragment;
import com.coinhub.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;

/**
 * assigning the expanded item layout
 */
@Layout(R.layout.layout_menu_expanded_item)
public class NavigationDrawerExpandItemAdapter {
    /**
     * binding views and variables
     */
    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;

    @View(R.id.tv_heading_menu)
    private TextView tvheading;

    @View(R.id.row_expanded_menu)
    private LinearLayout linearLayout;

    @View(R.id.image_view_icon)
    private ImageView imageViewIcon;
    public String title;
    public int icon;
    public Activity mContext;

    /**
     * constructor for getting activity context and expanded items data
     *
     * @param activity
     * @param heading
     */
    public NavigationDrawerExpandItemAdapter(Activity activity, MenuExpandedModel heading) {

        this.title = heading.getName();
        this.icon = heading.getIcon();
        this.mContext = activity;
    }

    @Resolve
    private void onResolved() {
        /**
         * setting title and icons to the expanding items according to the data
         */
        tvheading.setText(title);
        imageViewIcon.setImageResource(icon);

        /**
         * click listener of item
         */
        linearLayout.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                /**
                 * closing navigation drawer if it is opened
                 */
                if (MainActivity.drawer.isDrawerOpen(GravityCompat.START)) {
                    MainActivity.drawer.closeDrawer(GravityCompat.START);
                }
                /**
                 * popping up the all existing fragment
                 */
                for (int i = 0; i < MainActivity.manager.getBackStackEntryCount(); i++) {
                    MainActivity.manager.popBackStack(i, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                /**
                 * setting layout configuration of header and status bar
                 */
                ((MainActivity) mContext).layoutColorChange.setBackgroundColor(Color.parseColor("#F99547"));
                ((MainActivity) mContext).layoutDashboardHeader.setVisibility(android.view.View.GONE);
                ((MainActivity) mContext).layoutAllHeader.setVisibility(android.view.View.VISIBLE);
                ((MainActivity) mContext).statusBarView.setBackgroundColor(Color.parseColor("#F99547"));
                MainActivity.btnHam.setImageResource(R.drawable.ham_white);

                /**
                 * checking the title and launching the screen according to the title
                 */
                if (title.equals(mContext.getString(R.string.buy_bitcoin))) {
                    /**
                     * setting confirm text view visible
                     * setting text to title textview
                     * lunching buy fragment
                     */
                    MainActivity.tvTitle.setText(mContext.getString(R.string.buy_bitcoin));
                    MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new BuyFragment()).commit();

                } else if (title.equals(mContext.getString(R.string.sell_bitcoin))) {

                    /**
                     * setting confirm text view visible
                     * setting text to title textview
                     * lunching buy fragment
                     */
                    MainActivity.tvTitle.setText(mContext.getString(R.string.sell_bitcoin));
                    MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new SellFragment()).commit();

                } else {

                }
            }
        });
    }

}
