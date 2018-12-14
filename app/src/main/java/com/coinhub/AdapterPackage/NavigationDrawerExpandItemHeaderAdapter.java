package com.coinhub.AdapterPackage;
/**
 * all required libraries imported here
 */

import android.widget.ImageButton;
import android.widget.TextView;

import com.coinhub.DataModelPackage.MenuHeadingModel;
import com.coinhub.R;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.Collapse;
import com.mindorks.placeholderview.annotations.expand.Expand;
import com.mindorks.placeholderview.annotations.expand.Parent;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;
import com.mindorks.placeholderview.annotations.expand.SingleTop;
import com.mindorks.placeholderview.annotations.expand.Toggle;


@Parent
@SingleTop
/**
 * assigning the heading item layout
 */
@Layout(R.layout.layout_menu_heading_item)
public class NavigationDrawerExpandItemHeaderAdapter {
    /**
     * binding views and variables
     */
    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;

    @View(R.id.tv_heading_menu)
    private TextView tvheading;


    @Toggle(R.id.uparrow)
    @View(R.id.uparrow)
    private ImageButton uparrow;

    @Toggle(R.id.linear_layout_press)


    public String title;

    /**
     * constructor for getting heading items data
     *
     * @param heading
     */
    public NavigationDrawerExpandItemHeaderAdapter(MenuHeadingModel heading) {

        this.title = heading.getName();
    }

    @Resolve
    private void onResolved() {
        /**
         * setting title text to the heading textview
         */
        tvheading.setText(title);
    }

    @Expand
    private void onExpand() {
        /**
         * on expand the arrow icon will be set to arrow up
         */
        uparrow.setImageResource(R.drawable.arrow_up);
    }

    @Collapse
    private void onCollapse() {
        /**
         * on collapse the arrow icon will be set to arrow down
         */
        uparrow.setImageResource(R.drawable.arrow_down);
    }
}
