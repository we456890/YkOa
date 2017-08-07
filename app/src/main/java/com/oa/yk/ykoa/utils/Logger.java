package com.oa.yk.ykoa.utils;




import com.oa.yk.ykoa.BuildConfig;

import cn.memedai.log.LogHelper;

/**
 * Logger
 * log统一输出类
 *
 **/
public class Logger {

	/**
	 * log的开关
	 */
	private static boolean isDebug = true;

	/**
	 * log的输出tag
	 */
	private static String TAG = "stone";


	/**
	 * <setTAG>
	 * 设置log的TAG
	 *
	 * @param tAG
	 **/
	public static void setTAG(String tAG) {
		TAG = tAG;
	}

	/**
	 * <i>
	 * 正常调试log输出
	 *
	 * @param msg msg log的内容
	 **/
	public static void i(String msg) {
		if (isDebug) {
			LogHelper.i(msg);
		}
	}

	/**
	 * <e>
	 * 异常log输出
	 *
	 * @param msg log的内容
	 **/
	public static void e(String msg) {
		if (isDebug) {
			LogHelper.e(msg);
		}
	}

	public static void d(String msg) {
		if (isDebug) {
			LogHelper.d(msg);
		}
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			LogHelper.d(msg);
		}
	}

	/**
	 * <w>
	 *
	 * @param msg
	 */
	public static void w(String msg) {
		if (isDebug) {
			LogHelper.w(msg);
		}
	}

	/**
	 * <e>
	 * 异常log输出
	 *
	 * @param msg log的内容
	 * @param tr  异常信息
	 **/
	public static void e(String msg, Throwable tr) {
		if (isDebug) {
			LogHelper.e(tr,msg);
		}
	}

	/**
	 * 打印json信息
	 * @param msg
	 */
	public static void json(String msg){
		if (isDebug) {
			LogHelper.json(msg);
		}
	}
	/**
	 * 打印xml信息
	 * @param msg
	 */
	public static void xml(String msg){
		if (isDebug) {
			LogHelper.xml(msg);
		}
	}
}
