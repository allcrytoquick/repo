package com.coinhub.AdapterPackage;
/**
 * all required libraries imported here
 */

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coinhub.ActivityPackage.MainActivity;
import com.coinhub.DataModelPackage.MenuHeadingModel;
import com.coinhub.FragmentPackage.WalletFragment;
import com.coinhub.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;
import com.mindorks.placeholderview.annotations.expand.SingleTop;

@Parent
@SingleTop
/**
 * assigning the single item layout
 */
@Layout(R.layout.layout_menu_single_item)
public class NavigationDrawerSingleItemAdapter {
    /**
     * binding views and variables
     */
    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;

    @View(R.id.tv_heading_menu)
    private TextView tvheading;


    @View(R.id.linear_layout_press)
    private LinearLayout linearLayout;

    private Activity mContext;
    public String title;

    public static String CoinName = "";

    /**
     * constructor for getting heading items data and activity context
     *
     * @param heading
     */
    public NavigationDrawerSingleItemAdapter(Activity activity, MenuHeadingModel heading) {
        this.mContext = activity;
        this.title = heading.getName();

    }

    @Resolve
    private void onResolved() {
        /**
         * setting title
         */
        tvheading.setText(title);
/**
 * click listener of items
 */
        linearLayout.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                /**
                 * closing the navigation drawer if its opened
                 */
                if (MainActivity.drawer.isDrawerOpen(GravityCompat.START)) {
                    MainActivity.drawer.closeDrawer(GravityCompat.START);
                }
                /**
                 * popping up the all existed fragments
                 */
                for (int i = 0; i < MainActivity.manager.getBackStackEntryCount(); i++) {
                    MainActivity.manager.popBackStack(i, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                /**
                 * checking title
                 */
                if (title.equals(mContext.getString(R.string.btc_wallet))) {
                    /**
                     * setting title
                     */
                    MainActivity.tvTitle.setText(mContext.getString(R.string.btc_wallet));
                    /**
                     * assigning coin name for access from wallet fragment
                     */
                    CoinName = mContext.getString(R.string.bitcoin);
                    /**
                     * setting header and status bar layout configuration
                     */
                    ((MainActivity) mContext).layoutColorChange.setBackgroundColor(Color.parseColor("#F99547"));
                    ((MainActivity) mContext).layoutDashboardHeader.setVisibility(android.view.View.GONE);
                    ((MainActivity) mContext).layoutAllHeader.setVisibility(android.view.View.VISIBLE);
                    ((MainActivity) mContext).statusBarView.setBackgroundColor(Color.parseColor("#F99547"));
                    WalletFragment.isFromCurrecy = false;
                    MainActivity.btnHam.setImageResource(R.drawable.ham_white);

                    /**
                     * launching the wallet fragment
                     */
                    MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new WalletFragment()).commit();

                } else if (title.equals(mContext.getString(R.string.eth_wallet))) {
                    /**
                     * setting title
                     */
                    MainActivity.tvTitle.setText(mContext.getString(R.string.eth_wallet));
                    /**
                     * assigning the coin name for accessing from the wallet fragment
                     */
                    CoinName = mContext.getString(R.string.eth);
                    /**
                     * setting header and status bar layout configuration
                     */
                    ((MainActivity) mContext).layoutColorChange.setBackgroundColor(Color.parseColor("#F99547"));
                    ((MainActivity) mContext).layoutDashboardHeader.setVisibility(android.view.View.GONE);
                    ((MainActivity) mContext).layoutAllHeader.setVisibility(android.view.View.VISIBLE);
                    ((MainActivity) mContext).statusBarView.setBackgroundColor(Color.parseColor("#F99547"));
                    MainActivity.btnHam.setImageResource(R.drawable.ham_white);
                    WalletFragment.isFromCurrecy = false;
                    /**
                     * launching wallet fragment
                     */
                    MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new WalletFragment()).commit();

                } else if (title.equals(mContext.getString(R.string.lte_wallet))) {
                    /**
                     * setting title
                     */
                    MainActivity.tvTitle.setText(mContext.getString(R.string.lte_wallet));
                    /**
                     * assigning the coin name for accessing from the wallet fragment
                     */
                    CoinName = mContext.getString(R.string.ltc);
                    /**
                     * setting header and status bar layout configuration
                     */
                    ((MainActivity) mContext).layoutColorChange.setBackgroundColor(Color.parseColor("#F99547"));
                    ((MainActivity) mContext).layoutDashboardHeader.setVisibility(android.view.View.GONE);
                    ((MainActivity) mContext).layoutAllHeader.setVisibility(android.view.View.VISIBLE);
                    ((MainActivity) mContext).statusBarView.setBackgroundColor(Color.parseColor("#F99547"));
                    MainActivity.btnHam.setImageResource(R.drawable.ham_white);
                    WalletFragment.isFromCurrecy = false;

                    /**
                     * launching the wallet fragment
                     */
                    MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new WalletFragment()).commit();

                } else if (title.equals(mContext.getString(R.string.usd_wallet))) {
                    /**
                     * setting title
                     */
                    MainActivity.tvTitle.setText(mContext.getString(R.string.usd_wallet));
                    /**
                     * assigning the currency name for accessing from the wallet fragment
                     */
                    CoinName = mContext.getString(R.string.usd);
                    /**
                     * setting the header and status bar layout configuration
                     */
                    ((MainActivity) mContext).layoutColorChange.setBackgroundColor(Color.parseColor("#F99547"));
                    ((MainActivity) mContext).layoutDashboardHeader.setVisibility(android.view.View.GONE);
                    ((MainActivity) mContext).layoutAllHeader.setVisibility(android.view.View.VISIBLE);
                    ((MainActivity) mContext).statusBarView.setBackgroundColor(Color.parseColor("#F99547"));
                    MainActivity.btnHam.setImageResource(R.drawable.ham_white);
                    WalletFragment.isFromCurrecy = true;
                    /**
                     * launching the wallet fragment
                     */
                    MainActivity.manager.beginTransaction().replace(R.id.fragment_container, new WalletFragment()).commit();

                } else {

                }
            }
        });
    }
}

