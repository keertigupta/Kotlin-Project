package connect.com.credr.connect.database;

import android.content.Context;
import android.content.SharedPreferences;

import connect.com.credr.connect.utils.Logger;

/**
 * Created by Shivam Jaiswal on 24/03/18.
 */

public class PreferenceManager {

    public static final String USER_LOC_IDS = "pref_user_locids";
    public static final String USER_FCM_TOKEN = "user_fcmtoken";
    public static final String APP_VERSION = "app_version";
    public static final String FORCE_UPDATE = "force_update";
    public static final String IS_USER_ACTIVE = "is_user-active";
    public static final String CITY_ID = "cityId";
    public static final String DISABLE_UPDATE_DIALOG = "force_update_disable";
    public static final String USER_TYPE = "user_type";
    private static PreferenceManager mInstance = null;
    private final String PREFERENCE_NAME = "credr.connect";
    private final String USER_DETAILS = "user_details";
    private final String SETECTED_TAB_POS = "SETECTED_TAB_POS";
    private final String USER_ROLE_BASED_PERMISSIONS = "pref_user_roles";
    private SharedPreferences mPreference;
    public static final String USER_ID = "USER_ID";

    public static final String ACCESS_KEY = "ACCESS_KEY";
    public static final String SECRET_KEY = "SECRET_KEY";


    public static final String LAT = "LAT";
    public static final String LANG = "LANG";

    private PreferenceManager(Context c) {
        mPreference = c.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }


    public static PreferenceManager instance(Context context) {
        if (mInstance == null)
            mInstance = new PreferenceManager(context);

        return mInstance;
    }


    public void setUser(String name, String userId, String mobileNumber, String userStatus, String userRoleId) {
        String data = name + "|" + userId + "|" + mobileNumber + "|" + userStatus + "|" + userRoleId;
        Logger.Companion.e("saveUserInfo", data);
        mPreference.edit()
                .putString(USER_DETAILS, data)
                .apply();
    }

    public  void setCurrentItem(int position){
        mPreference.edit()
                .putInt(SETECTED_TAB_POS,position)
                .apply();
    }

    public int getCurrentItem(){
        return  mPreference.getInt(SETECTED_TAB_POS,0);
    }
    public String[] getUser() {
        String[] userData;

        userData = mPreference.getString(USER_DETAILS, "").split("\\|");

        return userData;
    }

    public void set(String key, int value) {
        Logger.Companion.e(key, "==>>> " + value);
        mPreference.edit().putInt(key, value)
                .apply();
    }
    public void set(String key, String value) {
        Logger.Companion.e(key, "==>>> " + value);
        mPreference.edit().putString(key, value)
                .apply();
    }

    public void set(String key, boolean value) {
//        Logger.Companion.e(key,"==>>> "+value);
        mPreference.edit().putBoolean(key, value)
                .apply();
    }

    public String get(String key, String defaultValue) {

        return mPreference.getString(key, defaultValue);
    }

    public boolean get(String key, boolean defaultValue) {

        return mPreference.getBoolean(key, defaultValue);
    }


    /**
     * This method is used mainly for Logout purpose
     * Clear all stored data here
     */
    public void clearUserSession() {
        mPreference.edit().clear().apply();
    }

    /**
     * Save user role based list received during login
     * @param jsonResponseData
     */
    public void saveUserRoles(String jsonResponseData) {
        mPreference.edit()
                .putString(USER_ROLE_BASED_PERMISSIONS, jsonResponseData)
                .apply();
    }

    public String getUserRoles() {
        return mPreference.getString(USER_ROLE_BASED_PERMISSIONS, null);
    }



}
