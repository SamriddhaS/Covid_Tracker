package com.example.covid_19tracker.others;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefSingleton {

    private static SharedPreferences preferences ;


    // Keys For saving api sorting data
    public static final String KEY_TOTAL_CASE = "TOTAL_CASE";
    public static final String KEY_ACTIVE_CASE = "ACTIVE_CASE";
    public static final String KEY_TODAY_CASE = "TODAY_CASE";
    public static final String KEY_RECOVERED = "RECOVERED";
    public static final String KEY_TOTAL_DEATHS = "TOTAL_DEATHS";
    public static final String KEY_TODAY_DEATHS = "TODAY_DEATHS";
    public static final String KEY_A_Z = "A_Z";
    public static final String API_SORT_KEY = "API_SORTING_INFO" ;

    public SharedPrefSingleton() {

    }

    public static void init(Context context){
        if (preferences==null){
            preferences = context.getSharedPreferences(context.getPackageName(),Activity.MODE_PRIVATE);
        }
        if (preferences!=null && !preferences.contains(KEY_ACTIVE_CASE)
                && !preferences.contains(KEY_RECOVERED)
                && !preferences.contains(KEY_TODAY_CASE)
                && !preferences.contains(KEY_TOTAL_CASE)
                && !preferences.contains(KEY_TODAY_DEATHS)
                && !preferences.contains(KEY_TOTAL_DEATHS)){

            insertApiSortData();
        }

    }

    public static void insertApiSortData(){


        SharedPreferences.Editor editor = preferences.edit() ;
        editor.putString(KEY_TOTAL_CASE, "cases") ;
        editor.putString(KEY_ACTIVE_CASE, "active") ;
        editor.putString(KEY_TODAY_CASE, "todayCases") ;
        editor.putString(KEY_RECOVERED, "recovered") ;
        editor.putString(KEY_TOTAL_DEATHS, "deaths") ;
        editor.putString(KEY_TODAY_DEATHS, "todayDeaths") ;
        editor.putString(KEY_A_Z, null) ;

        editor.commit() ;

    }

    public static void write(String key,String value){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String read(String key,String defValue){
        return preferences.getString(key,defValue) ;

    }


}
