package com.coinhub.AdapterPackage;
/**
 * all required libraries imported here
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coinhub.R;

import java.util.ArrayList;


/**
 * Created by ibrahim on 4/10/17.
 */

public class ShowcaseAdapter extends RecyclerView.Adapter<ShowcaseAdapter.GenericViewHolder> {
    /**
     * Field instance of transaction context and type for receive and send
     */
    Activity context;
    ArrayList<Integer> arrayList;

    /**
     * constructor for getting the current context and image data as array list
     *
     * @param context
     */
    public ShowcaseAdapter(Activity context, ArrayList<Integer> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

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
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_showcase_style, viewGroup, false);
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

        holder.image_view_bg.setImageResource(arrayList.get(position));

    }

    /**
     * returning total item counts measuring the array list size
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 3;
    }


    /**
     * view holder for defining the views and holding up
     */

    public static class GenericViewHolder extends RecyclerView.ViewHolder {
        /**
         * field instances of all views of the row
         */
        TextView tv_title;
        ImageView image_view_bg;

        public GenericViewHolder(View itemView) {
            super(itemView);
            /**
             * type casting all views
             */
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            image_view_bg = (ImageView) itemView.findViewById(R.id.image_view_bg);
        }
    }
}
