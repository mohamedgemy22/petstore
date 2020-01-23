package com.reload.petsstore.items;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.reload.petsstore.R;
import com.reload.petsstore.itemdetails.ItemDetailsActivity;
import com.reload.petsstore.items.Model.ItemResult;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    ArrayList<ItemResult> mList;
    Context mContext;
    public changeFav mInterface;

    public interface changeFav {
        void change(int hasFav, String ItemId);
    }

    ;

    public ItemAdapter(ArrayList<ItemResult> mList, Context mContext, changeFav mInterface) {
        this.mInterface = mInterface;
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_cat_item, parent, false);
        ItemAdapter.MyViewHolder myViewHolder = new ItemAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemAdapter.MyViewHolder holder, final int position) {
        holder.mItemName.setText(mList.get(position).getName());
        Glide.with(mContext).load(mList.get(position).getImage()).into(holder.mItemImage);

        final boolean hasFav = mList.get(position).getHasFavorite();
        if (hasFav) {
            holder.mFav.setBackgroundDrawable(mContext.getResources()
                    .getDrawable(R.drawable.ic_favorite_black_24dp));
        } else {
            holder.mFav.setBackgroundDrawable(mContext.getResources()
                    .getDrawable(R.drawable.ic_favorite_border_black_24dp));
        }

        holder.mFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasFav) {
                    mInterface.change(0, mList.get(position).getId());
                    holder.mFav.setBackgroundDrawable(mContext.getResources()
                            .getDrawable(R.drawable.ic_favorite_border_black_24dp));
                } else {
                    mInterface.change(1, mList.get(position).getId());
                    holder.mFav.setBackgroundDrawable(mContext.getResources()
                            .getDrawable(R.drawable.ic_favorite_black_24dp));

                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, ItemDetailsActivity.class)
                        .putExtra("itemId", mList.get(position).getId())
                        .putExtra("itemName", mList.get(position).getName()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mItemName;
        ImageView mItemImage;
        ImageView mFav;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemName = itemView.findViewById(R.id.item_name);
            mItemImage = itemView.findViewById(R.id.item_img);
            mFav = itemView.findViewById(R.id.fav);
        }
    }
}
