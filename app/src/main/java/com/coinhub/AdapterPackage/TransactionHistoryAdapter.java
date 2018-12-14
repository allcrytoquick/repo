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

import com.coinhub.DataModelPackage.TransactionModel;
import com.coinhub.R;

import java.util.ArrayList;


/**
 * Created by ibrahim on 4/10/17.
 */

public class TransactionHistoryAdapter extends RecyclerView.Adapter<TransactionHistoryAdapter.GenericViewHolder> {
    /**
     * Field instance of transaction context and type for receive and send
     */
    Activity context;

    ArrayList<TransactionModel> arrayList;

    /**
     * constructor for getting the current context and data
     *
     * @param context
     * @param arrayList
     */
    public TransactionHistoryAdapter(Activity context, ArrayList<TransactionModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    /**
     * creating each item of reycler view
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
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_transaction_item, viewGroup, false);
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
         * if type is 0 then this will be buy bitcoin
         * else then this will be sell bitcoin
         */
        if (arrayList.get(position).getType() == 0) {
            /**
             * setting the title to #buy bitcoin and change the icon to buy bitcoin
             */
            holder.row_icon.setImageResource(R.drawable.buy);
            holder.tvSellOrBuy.setText("Buy BitCoin");
            holder.tvAmount.setText("2.513666974565");
            holder.tvTotal.setText("$ 2.51365 k");
        } else {
            /**
             * setting the title to #sell bitcoin and change the icon to sell bitcocin
             */
            holder.row_icon.setImageResource(R.drawable.sell);
            holder.tvSellOrBuy.setText("Sell BitCoin");
            holder.tvAmount.setText("10.513666974565");
            holder.tvTotal.setText("$ 35.51365 k");
        }

    }

    /**
     * returning total item counts measuring the array list size
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    /**
     * view holder for defining the views and holding up
     */

    public static class GenericViewHolder extends RecyclerView.ViewHolder {
        /**
         * field instances of all views of the row
         */
        TextView tvSellOrBuy, tvCardNo, tvAmount, tvTotal;
        ImageView row_icon;

        public GenericViewHolder(View itemView) {
            super(itemView);
            /**
             * type casting all views
             */
            tvSellOrBuy = (TextView) itemView.findViewById(R.id.tv_sell_or_buy_bitcoin);
            tvCardNo = (TextView) itemView.findViewById(R.id.tv_card_no);
            tvAmount = (TextView) itemView.findViewById(R.id.tv_amount);
            tvTotal = (TextView) itemView.findViewById(R.id.tv_total);
            row_icon = (ImageView) itemView.findViewById(R.id.imageView_buy_or_sell);
        }

    }
}
