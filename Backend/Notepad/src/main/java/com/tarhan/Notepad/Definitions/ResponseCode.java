package com.tarhan.Notepad.Definitions;

public class ResponseCode {
    public static final String USER_ADDED = "USR000";
    public static final String USER_EXIST = "USR001";
    public static final String USER_DELETED = "USR002";
    public static final String USER_UPDATED = "USR003";
    public static final String USER_ADD_ERROR = "USR004";
    public static final String USER_DELETE_ERROR = "USR005";
    public static final String USER_UPDATE_ERROR = "USR006";
    public static final String USER_NOT_FOUND = "USR007";

    public static final String NOTE_ADDED = "NTE000";
    public static final String NOTE_EXIST = "NTE001";
    public static final String NOTE_DELETED = "NTE002";
    public static final String NOTE_UPDATED = "NTE003";
    public static final String NOTE_ERROR = "NTE004";

    public static final String INVALID_PASSWORD_LENGTH= "PSW001";
    public static final String INVALID_PASSWORD_FORMAT= "PSW002";
    public static final String NULL_PASSWORD= "PSW003";
    public static final String PASSWORD_OK= "PSW004";
    public static final String PASSWORD_ERROR= "PSW005";

    public static final String INVALID_USERNAME_LENGTH= "UNM001";
    public static final String INVALID_USERNAME_FORMAT= "UNM002";
    public static final String NULL_USERNAME= "UNM003";
    public static final String USERNAME_OK= "UNM004";
    public static final String USERNAME_EXIST= "UNM005";

}
