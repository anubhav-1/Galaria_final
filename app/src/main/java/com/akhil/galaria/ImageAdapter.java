package com.akhil.galaria;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder>{

    public ArrayList<ImageData> mImageDataArray;
    public LayoutInflater mLayoutInflater;

    public ImageAdapter(Context context, ArrayList<ImageData> data){
        mLayoutInflater = LayoutInflater.from(context);
        mImageDataArray = data;
    }

    @Override
    public ImageAdapter.ImageHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mLayoutInflater.
                inflate(R.layout.recycler_row_layout,
                        parent,false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ImageHolder holder, int position){
        ImageData currentImageData = mImageDataArray.get(position);

        holder.mImageDescriptionHolder.setText(
                currentImageData.getImageDescription());
        holder.mImageIconHolder.setImageResource(
                currentImageData.getImageIcon());
        holder.mHeartButton.setLiked(currentImageData.getLikedStatus());
    }

    @Override
    public int getItemCount(){return mImageDataArray.size();};


    public class ImageHolder extends RecyclerView.ViewHolder{
        ImageView mImageIconHolder;
        TextView mImageDescriptionHolder;
        LikeButton mHeartButton;

        private ImageData mEditImageData;

        public ImageHolder(View itemView){
            super(itemView);

            mImageDescriptionHolder = itemView.findViewById(R.id.image_description_id);
            mImageIconHolder = itemView.findViewById(R.id.image_id);
            mHeartButton = itemView.findViewById(R.id.heart_button);

            mHeartButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    changeLikedStatus(true);
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    changeLikedStatus(false);
                }
            });

        }

        private void changeLikedStatus(boolean status){
            // Get the current ImageData object by using the
            // getAdapterPosition() method
            mEditImageData = mImageDataArray.
                    get(getAdapterPosition());

            //Update the liked status of the image in question
            mEditImageData.setLikedStatus(status);

            //Update the current object in the ArrayList object too
            mImageDataArray.set(getAdapterPosition(), mEditImageData);

            //Notify the adapter about the changes
            // made at the current position
            notifyItemChanged(getAdapterPosition());
        }
    }
}
