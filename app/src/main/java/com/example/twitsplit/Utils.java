package com.example.twitsplit;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

public class Utils {

    /**
     * function to get text from Object of EditText or TextView.
     *
     * @param obj view instance of EditText or TextView.
     * @return text of obj above.
     */
    public static String getText(final Object obj) {
        if (obj != null) {
            if (obj instanceof EditText) {
                return ((EditText) obj).getText().toString();
            } else if (obj instanceof TextView) {
                return ((TextView) obj).getText().toString();
            }
        }
        return "";
    }

    /**
     * check is empty string.
     *
     * @param text text to check.
     * @return true if empty or null.
     */
    public static boolean empty(final String text) {
        return TextUtils.isEmpty(text);
    }

    /**
     * validate email field.
     *
     * @param s the current text.
     * @return true if valid email string else false.
     */
    public static boolean validateEmail(@NonNull final String s) {
        final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return (!empty(s) && s.matches(emailPattern));
    }

    /**
     * check required field not empty or null
     *
     * @param string field {@link String} to check.
     * @return false if null or empty, else true.
     */
    public static boolean requiredField(final String string) {
        return !TextUtils.isEmpty(string);
    }
}
