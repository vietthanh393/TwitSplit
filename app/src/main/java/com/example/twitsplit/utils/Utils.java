package com.example.twitsplit.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.twitsplit.home.model.Friends;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

    /**
     * get json from string by key
     *
     * @param jsonString the json body from server or file
     * @param key        the key to filter
     * @return
     */
    public static Object getJsonStringByKey(final String jsonString, final String key) {
        try {
            if (!jsonString.contains(key)) {
                return jsonString;
            }
            final JSONObject obj = new JSONObject(jsonString);
            final Object o = obj.get(key);
            if (o instanceof JSONArray) {
                return obj.getJSONArray(key).toString();
            } else if (o instanceof JSONObject) {
                return obj.getJSONObject(key).toString();
            }
            return o.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * parse json to object.
     *
     * @param tClass desired object to parse.
     * @param json   obj2son String.
     * @param <T>    instance to declare tClass.
     * @return the desired object or null.
     */
    public static <T> T parseJson(final Class<T> tClass, final String json) {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        final Gson gson = gsonBuilder.create();
        final Type token = new TypeToken<ArrayList<Friends>>() {
        }.getType();
        try {
            return gson.fromJson(json, token);
        } catch (Exception ex) {
            Log.e("Utils", "Could not parseJson: ", ex);
            return null;
        }
    }

    /**
     * function to remove newline character in string
     *
     * @param string the String need to check
     * @return the final result
     */
    public static String removeNewLineCharacter(final String string) {
        if (string.endsWith("\n")) {
            return string.substring(0, string.length() - 1);
        } else {
            return string;
        }
    }

    /**
     * function to get screen width
     *
     * @param context
     * @return the width resolution of screen
     */
    public static int getScreenWidth(final Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            return displayMetrics.widthPixels;
        } else {
            return 0;
        }
    }
}
