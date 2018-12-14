package com.coinhub.ActivityPackage;
/**
 * all required libraries imported here
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.coinhub.AdapterPackage.ShowcaseAdapter;
import com.coinhub.AdapterPackage.ShowcaseIndacatorAdapter;
import com.coinhub.R;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class ShowcaseActivity extends AppCompatActivity {
    /**
     * Field instances of views and variables
     */
    DiscreteScrollView showcaseScrollView;
    RecyclerView showcaseIndactor;
    Button btn_get_started;
    ShowcaseIndacatorAdapter indactorAdapter;
    boolean f = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * setting up the content view of this screen
         */
        setContentView(R.layout.activity_showcase);


        /**
         * type casting the all views
         */
        initialiseViews();

        /**
         * setting up the demo images as array list
         */
        final ArrayList<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.storyboard1);
        imageList.add(R.drawable.storyboard2);
        imageList.add(R.drawable.storyboard3);

        /**
         * setting adapter to the showcase scroll view with the demo images array list
         */
        showcaseScrollView.setAdapter(new ShowcaseAdapter(ShowcaseActivity.this, imageList));
        indactorAdapter = new ShowcaseIndacatorAdapter(ShowcaseActivity.this, imageList.size());
        showcaseIndactor.setAdapter(indactorAdapter);


        /**
         * click listener for button get started
         */
        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * starting the login screen
                 */
                startActivity(new Intent(ShowcaseActivity.this, LoginActivity.class));
            }
        });
        /**
         * showcase item change  listener for  enabling  and disabling the get started button
         */
        showcaseScrollView.addOnItemChangedListener(new DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>() {
            @Override
            public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
                /**
                 * if current show case position is the last then enabling the get started button
                 * else disabling the get started button
                 */
                if (adapterPosition == imageList.size() - 1) {
                    btn_get_started.setEnabled(true);
                    btn_get_started.setAlpha(1.0f);
                } else {
                    btn_get_started.setEnabled(false);
                    btn_get_started.setAlpha(0.5f);
                }
                /**
                 * scrolling to indicator position smoothly according to the slider position
                 */
                showcaseIndactor.smoothScrollToPosition(adapterPosition);
                /**
                 * checking for is first time or not
                 * if not first time then the current indicator background to indicator and the background of rest will be set to indicator normal
                 *
                 */
                if (f) {
                    /**
                     * finding view holder of indicator list according to the current position
                     * setting background of this to indicator
                     * looping into the all indicator and checking the position
                     * if the position is not current position then setting background to indicator normal
                     */
                    ShowcaseIndacatorAdapter.GenericViewHolder genericViewHolder =
                            (ShowcaseIndacatorAdapter.GenericViewHolder)
                                    showcaseIndactor.findViewHolderForAdapterPosition(adapterPosition);
                    genericViewHolder.indacator.setBackground(ContextCompat.getDrawable(ShowcaseActivity.this, R.drawable.indacator));

                    for (int i = 0; i < imageList.size(); i++) {
                        if (i != adapterPosition) {
                            ShowcaseIndacatorAdapter.GenericViewHolder genericViewHolder1 =
                                    (ShowcaseIndacatorAdapter.GenericViewHolder)
                                            showcaseIndactor.findViewHolderForAdapterPosition(i);
                            genericViewHolder1.indacator.setBackground(ContextCompat.getDrawable(ShowcaseActivity.this, R.drawable.indacator_normal));
                        }
                    }
                } else {
                    f = true;
                }

            }
        });

    }

    /**
     * this method will type cast the all views of this screen
     */
    public void initialiseViews() {
        showcaseScrollView = (DiscreteScrollView) findViewById(R.id.show_case_discrete_scroll_view);
        showcaseIndactor = (RecyclerView) findViewById(R.id.recycler_indacator);
        btn_get_started = (Button) findViewById(R.id.btn_get_started);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowcaseActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        showcaseIndactor.setLayoutManager(linearLayoutManager);
        /**
         * setting scale transformer to the show case scroll view
         */
        showcaseScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build());

    }


}
