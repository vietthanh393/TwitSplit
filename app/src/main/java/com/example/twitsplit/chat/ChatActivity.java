package com.example.twitsplit.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.twitsplit.BaseActivity;
import com.example.twitsplit.R;
import com.example.twitsplit.chat.adapter.MessageAdapter;
import com.example.twitsplit.chat.model.Message;
import com.example.twitsplit.utils.Constant;
import com.example.twitsplit.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ChatActivity.class.getSimpleName();
    public static String USER_NAME = "user_name";

    private RecyclerView mRecyclerView;
    private MessageAdapter mMessageAdapter;
    private List<Message> mMessagesList = new ArrayList<>();
    private ImageView mSendMessage;
    private EditText mChatContentView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
        final String userName = getIntent().getStringExtra(USER_NAME);
        initActionBar(userName, true);

        mMessageAdapter = new MessageAdapter(getApplicationContext(), mMessagesList);
        initView();
        setUpRecyclerView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chat_send_message:
                final String text = Utils.getText(mChatContentView);
                mChatContentView.setText("");
                final String chatContent = Utils.removeNewLineCharacter(text);
                if (Utils.empty(chatContent)) {
                    return;
                }
                if (chatContent.length() <= Constant.MAX_CHARACTER) {
                    Message message = new Message(chatContent);
                    mMessagesList.add(0, message);
                    mMessageAdapter.notifyDataSetChanged();
                } else {
                    final List<Message> splitList = splitMessage(chatContent);
                    if (splitList.size() > 0) {
                        for (Message message : splitList) {
                            mMessagesList.add(0, message);
                            mMessageAdapter.notifyDataSetChanged();
                        }
                    }
                }
                break;
        }
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.chat_list);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(true);
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable
                .custom_divider));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setAdapter(mMessageAdapter);
    }

    private void initView() {
        mChatContentView = findViewById(R.id.chat_content_view);
        mSendMessage = findViewById(R.id.chat_send_message);
        mSendMessage.setOnClickListener(this);
        mSnackBarDock = mChatContentView.getRootView();
    }

    private List<Message> splitMessage(final String message) {
        final List<Message> list = new ArrayList<>();
        final String[] childStrings = message.split(" ");
        if (childStrings.length == 1) {
            onShowToast(getString(R.string.error_message));
            return list;
        }
        boolean isFirstIndex = true;
        String result = "";
        for (int index = 0; index <= childStrings.length; index++) {
            if (index < childStrings.length) {
                if (childStrings[index].length() > Constant.MAX_LENGTH_CAN_ADD) {
                    onShowToast(getString(R.string.error_message));
                    list.clear();
                    return list;
                }
                if (isFirstIndex) {
                    result = result.concat(childStrings[index]);
                    isFirstIndex = false;
                    continue;
                }
                //Should add space character between 2 word, space length = 1
                if (result.length() + childStrings[index].length() <= Constant.MAX_LENGTH_CAN_ADD - 1) {
                    result = result.concat(" ").concat(childStrings[index]);
                } else {
                    list.add(new Message(result));
                    result = childStrings[index];
                }
            } else {
                list.add(new Message(result));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            final String partFormat = getString(R.string.part_indicator, i+ 1, list.size()).concat(" ");
            final String finalMessage = partFormat.concat(list.get(i).getContent());
            list.set(i, new Message(finalMessage));
        }
        return list;
    }
}
