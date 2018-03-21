package com.example.twitsplit.login;

import com.example.twitsplit.utils.Constant;

public class LoginInteractor {
    public void login(final String username, final String password, final OnLoginEventListener listener) {
        if (username.equals(Constant.USERNAME) && password.equals(Constant.PASSWORD)) {
            listener.onSuccess();
        } else {
            listener.onError(new Error("error"));
        }
    }
}
