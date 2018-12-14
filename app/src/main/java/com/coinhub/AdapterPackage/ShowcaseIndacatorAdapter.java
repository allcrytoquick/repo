package com.coinhub.AdapterPackage;
/**
 * all required libraries imported here
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coinhub.R;


/**
 * Created by ibrahim on 4/10/17.
 */

public class ShowcaseIndacatorAdapter extends RecyclerView.Adapter<ShowcaseIndacatorAdapter.GenericViewHolder> {
    /**
     * Field instance of transaction context and type for receive and send
     */
    Activity context;
    int count = 0;
    public static GenericViewHolder holderForSetColor;

    /**
     * constructor for getting the current context and image data as array list
     *
     * @param context
     */
    public ShowcaseIndacatorAdapter(Activity context, int count) {
        this.context = context;
        this.count = count;

    }

    /**
     * creating each item of recycler view
     *
     * @param viewGroup
     * @param viewType
     * @return
     */

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        /**
         * inflating the each item of recycler view with this defined layout of row
         */
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_showcase_indacator, viewGroup, false);
        /**
         * returning the each row
         */
        return new GenericViewHolder(itemview);
    }

    /**
     * this method will called when the view holder will bind to each item created
     *
     * @param holder
     * @param position
     */

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {

/**
 * setting image to the the image view
 * you may set text here also
 */
        holderForSetColor = holder;

        //  holder.image_view_bg.setImageResource(arrayList.get(position));

    }

    /**
     * returning total item counts measuring the array list size
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return count;
    }


    /**
     * view holder for defining the views and holding up
     */

    public static class GenericViewHolder extends RecyclerView.ViewHolder {
        /**
         * field instances of all views of the row
         */
        public View indacator;

        public GenericViewHolder(View itemView) {
            super(itemView);
            /**
             * type casting all views
             */
            indacator = (View) itemView.findViewById(R.id.indacator_view);
        }

    }


}
