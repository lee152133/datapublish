package com.keda.webservice.server;

import org.apache.log4j.Logger;

/**
 * 审讯数据推送-->>视频中心
 * @author liyunwei
 *
 */
public class SPZXMain {

	private static final Logger log = Logger.getLogger(SPZXMain.class);
	
	/**
	 * 审讯数据推送-->>视频中心接口版本：第一版。
	 */
	public static final String V1 = "V1";
	
	/**
	 * 当前模块是否启用。
	 */
	private static boolean enable = false;

	/**
	 * 接口版本。只能为以下值：{@link #V1}
	 */
	private static String version = V1;
	
	/**
	 * 同步周期:单位毫秒
	 */
	private static long cycle = 60 * 60 * 1000;
	
	/**
	 * 本地数据库的密码
	 */
	private static String mysqlPassword = "123456";
	
	/**
	 * 从审判系统同步排期时，如果排期在本地已存在，是否更新。true(默认)更新,false不更新。
	 */
	private static boolean updateTrial = true;
	
	/**
	 * 默认的书记员帐号。
	 * 从通达海同步的排期，可能没有指定书记员。但TSMS必须要书记员，所以指定一个默认的书记员帐号。默认为root
	 */
	private static String defaultSjyUsername = "root";
	

	
	/**
	 * 开始
	 */
	public static void start() {

		
	}

	/**
	 * 重启任务 
	 */
	public static void restart(){
		
	}
	
	/**
	 * 停止
	 */
	public static void stop() {
		
	}

	public static void notifyTask(){
		
	}
	
	public static String getMysqlPassword() {
		return mysqlPassword;
	}

	public static void setMysqlPassword(String mysqlPassword) {
		SPZXMain.mysqlPassword = mysqlPassword;
	}

	public static boolean isEnable() {
		return enable;
	}

	public static String getVersion() {
		return version;
	}

	/**
	 * 设置接口版本。
	 * @param version 接口版本。只能为以下值：{@link #V1}、{@link #V2}
	 */
	public static void setVersion(String version) {
		SPZXMain.version = version;
	}

	
}
