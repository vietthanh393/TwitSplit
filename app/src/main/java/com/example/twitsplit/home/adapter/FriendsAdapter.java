package com.example.twitsplit.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.twitsplit.R;
import com.example.twitsplit.home.listener.OnItemClickListener;
import com.example.twitsplit.home.model.Friends;
import com.example.twitsplit.utils.GlideApp;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {
    private Context context;
    private List<Friends> mFriendsList;
    private OnItemClickListener mListener;

    public FriendsAdapter(Context context, List<Friends> friends, OnItemClickListener listener) {
        this.context = context;
        mFriendsList = friends;
        mListener = listener;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friend_item_view, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FriendViewHolder holder, final int position) {
        final Friends friends = mFriendsList.get(position);

        GlideApp.with(context)
                .load(friends.getImgSrc())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);

        holder.nameView.setText(friends.getName());
        holder.emailView.setText(friends.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFriendsList.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameView;
        TextView emailView;
        RelativeLayout itemView;

        public FriendViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.friend_img);
            nameView = view.findViewById(R.id.friend_name);
            emailView = view.findViewById(R.id.friend_email);
            itemView = view.findViewById(R.id.friend_view);
        }
    }
}