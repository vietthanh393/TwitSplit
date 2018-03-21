package com.example.twitsplit.home.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.twitsplit.R;
import com.example.twitsplit.home.adapter.FriendsAdapter;
import com.example.twitsplit.home.listener.OnItemClickListener;
import com.example.twitsplit.home.model.Friends;
import com.example.twitsplit.utils.Utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener, OnItemClickListener {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private List<Friends> mFriendsList = new ArrayList<>();
    private RecyclerView mFriendsView;
    private FriendsAdapter mFriendsAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String json = loadJSONFromAssets();
        loadData(json);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frg_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onClick(View view) {
        final int id = view.getId();
        switch (id) {

        }
    }

    private void initView(final View view){
        mFriendsView = view.findViewById(R.id.friend_list);
        mFriendsAdapter = new FriendsAdapter(getContext(), mFriendsList, this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        final DividerItemDecoration itemDecorator = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable
                .custom_divider));
        mFriendsView.setLayoutManager(mLayoutManager);
        mFriendsView.addItemDecoration(itemDecorator);
        mFriendsView.setAdapter(mFriendsAdapter);
        mFriendsAdapter.notifyDataSetChanged();
    }

    private String loadJSONFromAssets() {
        String json;
        try {
            InputStream inputStream = getContext().getAssets().open("friends.json");

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void loadData(final String json) {
        if (json == null) return;
        final String requiredObject = (String) Utils.getJsonStringByKey(json, Friends.MODEL_JSON_TAG);
        mFriendsList = Utils.parseJson(null, requiredObject);
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick " + position);
    }
}
