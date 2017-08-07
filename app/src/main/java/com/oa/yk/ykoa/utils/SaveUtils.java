package com.oa.yk.ykoa.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author:szm
 * @data:2016/4/8
 * @description:
 * @return:
 */
public class SaveUtils {
	public static final String USER_INFO_BEAN_TAG = "user";

	public static final String JSON_TAG_UID = "uid";

	public static final String JSON_TAG_NICK_NAME = "nickname";

	public static final String JSON_TAG_TRUE_NAME = "truename";

	public static final String JSON_TAG_PHONE = "phone";

	public static final String JSON_TAG_STATUS = "status";

	public static final String JSON_TAG_SOUR_STATUS = "sour_status";

	public static final String JSON_TAG_INTRODUCTION = "introduction";

	public static final String JSON_TAG_CHECKING_IN= "checking_in";
	public static final String JSON_TAG_ROLE= "role";

	public static final String JSON_TAG_AVATAR= "avatar";

	public static final String JSON_TAG_ACCESS_TOKEN = "access_token";

	public static final String JSON_TAG_TOKEN_EXPIRY = "token_expiry";

	public static final String JSON_TAG_HX_ID = "hx_id";

	public static final String JSON_TAG_PARSE_ID = "parse_id";
	public static final String JSON_TAG_INFO_STATUS = "info_status";

	public static final String JSON_TAG_FIRSTINDEX = "first";
	public static final String JSON_TAG_SECINDEX = "sec";
	public static final String JSON_TAG_THRINDEX = "thr";
	public static final String JSON_TAG_FOURINDEX = "four";
	public static final String JSON_TAG_FIVEINDEX = "five";
	public static final String JSON_TAG_SIXSTINDEX = "six";
	/**
	 * 保存账户信息
	 * 
	 * @param context
	 * 
	 */
	public static void setPreferencesDate(Context context, String key,
                                          String value) {
		SharedPreferences settings = context.getSharedPreferences(key, 0);
		SharedPreferences.Editor localEditor = settings.edit();
		localEditor.putString(key, value);
		localEditor.commit();
	}

	/**
	 * 获取账户信息
	 * 
	 * @param context
	 * @param key
	 * 
	 */
	public static String getPreferencesDate(Context context, String key) {
		SharedPreferences settings = context.getSharedPreferences(key, 0);
		String str = settings.getString(key, "");
		return str;
	}

	/**
	 * 清除账户信息
	 * 
	 * @param context
	 * 
	 */
	public static void cleanSharedDate(Context context, String ShareName) {
		SharedPreferences settings = context.getSharedPreferences(ShareName, 0);
		SharedPreferences.Editor localEditor = settings.edit();
		localEditor.clear().commit();
	}
}
