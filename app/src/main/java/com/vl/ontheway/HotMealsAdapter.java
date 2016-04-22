package com.vl.ontheway;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by skathi on 4/22/2016.
 */
public class HotMealsAdapter  extends RecyclerView.Adapter<HotMealsAdapter.CustomViewHolder>  {
    Context context;

    public HotMealsAdapter(Context context) {
        this.context=context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_mealrecycleritems, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        //FeedItem feedItem = feedItemList.get(i);

        //Download image using picasso library
      /*  Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);*/



    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView mTick;
        protected ImageView mFoodImage;
        public CustomViewHolder(View view) {
            super(view);
            this.mTick = (ImageView) view.findViewById(R.id.IV_tick);
            this.mFoodImage=(ImageView)view.findViewById(R.id.IV_Meal);

        }
    }
}
