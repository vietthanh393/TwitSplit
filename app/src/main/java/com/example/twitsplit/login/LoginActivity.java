package com.example.twitsplit.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.example.twitsplit.BaseActivity;
import com.example.twitsplit.utils.Constant;
import com.example.twitsplit.MainActivity;
import com.example.twitsplit.R;
import com.example.twitsplit.utils.Utils;

public class LoginActivity extends BaseActivity implements View.OnClickListener, OnLoginEventListener {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText mEmail;
    private EditText mPassword;
    private LoginInteractor mLoginInteractor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.hide();
        }

        mEmail = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mSnackBarDock = mPassword.getRootView();
        mLoginInteractor = new LoginInteractor();

        findViewById(R.id.login_btn).setOnClickListener(this);
        findViewById(R.id.register_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                doLogin();
                break;
            case R.id.register_btn:
                onShowToast("Coming soon");
                break;
        }
    }

    /**
     * called to start login.
     * if validate user and password valid will call LoginInteractor
     */
    private void doLogin() {
        final String username = Utils.getText(mEmail);
        final String password = Utils.getText(mPassword);
        if (!Utils.validateEmail(username) || !Utils.requiredField(password)) {
            String msg = "Put the valid email and password";
            onShowToast(msg);
            return;
        }
        showDialog("Logging in", getString(R.string.dialog_login_progress_text));
        mLoginInteractor.login(username, password, this);
    }

    @Override
    public void onSuccess() {
        dismissDialog();
        //Save to SharedPreferences when login successfully
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(Constant.IS_LOGIN, true);
        editor.apply();
        Log.d(TAG, "IS_LOGIN :" + preferences.getBoolean(Constant.IS_LOGIN, false));

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(Error error) {
        dismissDialog();
        onShowToast(error.getMessage());
    }
}
