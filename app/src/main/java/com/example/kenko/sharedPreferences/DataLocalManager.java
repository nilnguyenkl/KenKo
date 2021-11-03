package com.example.kenko.sharedPreferences;

import android.content.Context;

public class DataLocalManager {
    private static final String FIRST_INSTALL = "FIRST_INSTALL";
    private static final String EMAIL = "EMAIL";
    private static DataLocalManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFirstInstall(boolean isFirst){
        DataLocalManager.getInstance().mySharedPreferences.putBooleanValue(FIRST_INSTALL,isFirst);
    }

    public static boolean getFirstInstall(){
        return DataLocalManager.getInstance().mySharedPreferences.getBooleanValue(FIRST_INSTALL);
    }

    public static void setStringEmail(String email){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(EMAIL,email);
    }

    public static String getStringEmail(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(EMAIL);
    }

    public static void setStringIdCource(String email){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(EMAIL,email);
    }

    public static String getStringIdCource(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(EMAIL);
    }
}
