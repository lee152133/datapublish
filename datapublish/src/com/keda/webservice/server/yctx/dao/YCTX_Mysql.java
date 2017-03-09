package com.keda.webservice.server.yctx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.keda.webservice.server.utils.LogCacheUtil;


public class YCTX_Mysql {

	public static String ip = "127.0.0.1";
	public static int port = 3306;
	public static String dbname = "yctx";
	public static String username = "root";
	public static String password = "kdc";
	public static String charset = "utf-8";

	private static void init() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver").newInstance();

		} catch (Exception e) {
			e.printStackTrace();
			LogCacheUtil.getInstance().put("加载数据库方言出错[error]");
		}
	}

	static {


		init();
	}

	private static YCTX_Mysql mysql = new YCTX_Mysql();

	public static YCTX_Mysql getInstance() {
		return mysql;
	}

	public Connection getConnection() {
		String url = getURL();
		Connection con = null;
		try {

			LogCacheUtil.getInstance().put("【yctx】正在连接数据库:", ip, ":", port);

			con = DriverManager.getConnection(url);

			LogCacheUtil.getInstance().put("【yctx】数据库连接 [finished]...", ip, ":", port);

		} catch (SQLException e) {
			e.printStackTrace();
			LogCacheUtil.getInstance().put("【yctx】数据库连接异常[error]");
		}
		return con;
	}

	private String getURL() {
		// "jdbc:mysql://localhost:3306/ias100t?user=root&password=123456";
		StringBuffer sb = new StringBuffer();
		sb.append("jdbc:mysql://");
		sb.append(ip);
		sb.append(":");
		sb.append(port);
		sb.append("/");
		sb.append(dbname);
		sb.append("?user=");
		sb.append(username);
		sb.append("&password=");
		sb.append(password);
		sb.append("&useUnicode=true&characterEncoding=");
		sb.append(charset);

		return sb.toString();
	}

}
