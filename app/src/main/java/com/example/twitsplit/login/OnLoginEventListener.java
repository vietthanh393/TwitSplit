package com.example.twitsplit.login;


/**
 * CallBack to listen the request to update to UI
 */
public interface OnLoginEventListener {
    /**
     * called when login successfully.
     */
    void onSuccess();

    /**
     * called when error login happened.
     *
     * @param error the error obj2son string.
     */
    void onError(Error error);
}
