package com.example.twitsplit.utils;

public class Constant {
    public final static String IS_LOGIN = "IS_LOGIN";

    //Hardcode to check login
    public final static String USERNAME = "user@gmail.com";
    public final static String PASSWORD = "123";

    public final static int MAX_CHARACTER = 50;

    //Format: "1/2 " - Just use to split when: 2 <= part <= 9
    public final static int MIN_PART_INDICATOR_LENGTH = 4;

    public final static int MAX_LENGTH_CAN_ADD = MAX_CHARACTER - MIN_PART_INDICATOR_LENGTH;
}
