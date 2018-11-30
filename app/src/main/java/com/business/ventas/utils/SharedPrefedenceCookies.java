package com.business.ventas.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.HashSet;

public class SharedPrefedenceCookies {
    private static final String PREF_COOKIES = "PREF_COOKIES";
    private Context context;

    public SharedPrefedenceCookies(Context context) {
        this.context = context;
    }

    private SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void guardarCokkies(HashSet<String> cookies) {
        SharedPreferences.Editor memes = PreferenceManager.getDefaultSharedPreferences(context).edit();
        memes.putStringSet(PREF_COOKIES, cookies).apply();
        memes.commit();
    }

    public AppHashSet<String> getCookies() {
        return new AppHashSet<>(this.getPreferences().getStringSet(PREF_COOKIES, new HashSet<String>()));
    }

    public interface Cookiestring {
        //String get
    }

}
