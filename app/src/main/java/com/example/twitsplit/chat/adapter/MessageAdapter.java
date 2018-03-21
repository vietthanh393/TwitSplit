package com.example.twitsplit.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.twitsplit.R;
import com.example.twitsplit.chat.model.Message;
import com.example.twitsplit.utils.Utils;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.FriendViewHolder> {
    private Context context;
    private List<Message> mMessageList;

    public MessageAdapter(Context context, List<Message> list) {
        this.context = context;
        mMessageList = list;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_item_view, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FriendViewHolder holder, final int position) {
        final Message chat = mMessageList.get(position);
        holder.chatContent.setText(chat.getContent());
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView chatContent;

        public FriendViewHolder(View view) {
            super(view);
            chatContent = view.findViewById(R.id.chat_content);

            final int screenWidth = Utils.getScreenWidth(context);
            Log.d("screenWidth", "screenWidth: " + screenWidth);
            final int maxWidth;
            if (screenWidth > 0) {
                maxWidth = 4 * screenWidth / 5;
            } else {
                maxWidth = 1000;
            }
            chatContent.setMaxWidth(maxWidth);
        }
    }
}