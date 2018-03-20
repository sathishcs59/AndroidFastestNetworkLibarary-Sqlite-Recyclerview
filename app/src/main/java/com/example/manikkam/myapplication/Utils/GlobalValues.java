package com.example.manikkam.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class GlobalValues {
    String preference = "test";
    Context activity;

    public GlobalValues(Context activity) {
        this.activity = activity;
    }

    public String getString(String s) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return preferences.getString(s, "");
    }

    public int getInt(String value) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return preferences.getInt(value, 0);
    }

    public float getFloat(String value) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return preferences.getFloat(value, 0);
    }

    public boolean getBoolean(String value) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return preferences.getBoolean(value, false);
    }

    public void put(String name, String value) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.commit();
    }
    public void put(String name, float value) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(name, value);
        editor.commit();
    }
    public void put(String name, boolean value) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }
    public void put(String name, int value) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(name, value);
        editor.commit();
    }
    public boolean remove(String name) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if(has(name)) {
            editor.remove(name);
            editor.commit();
            return true;
        }
        else
            return false;
    }


    public void clear()
    {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean has(String name) {
        SharedPreferences preferences = activity.getSharedPreferences(preference, Context.MODE_PRIVATE);
        if (preferences.contains(name)) {
            return true;
        } else
            return false;
    }
}