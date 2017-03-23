package com.keda.webservice.server.sxms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.keda.webservice.server.utils.LogCacheUtil;

/**
 * 测试提交1
 * 描述：TODO 
 */
public class SXMS_Mysql {

	public static String ip = "10.10.11.45";
	public static int port = 3060;
	public static String dbname = "inquestdb3";
	public static String username = "admin";
	public static String password = "adminkdm";
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

	private static SXMS_Mysql mysql = new SXMS_Mysql();

	public static SXMS_Mysql getInstance() {
		return mysql;
	}

	public Connection getConnection() {
		String url = getURL();
		Connection con = null;
		try {

			LogCacheUtil.getInstance().put("【sxms】正在连接数据库:", ip, ":", port);

			con = DriverManager.getConnection(url);

			LogCacheUtil.getInstance().put("【sxms】数据库连接 [finished]...", ip, ":", port);

		} catch (SQLException e) {
			e.printStackTrace();
			LogCacheUtil.getInstance().put("【sxms】数据库连接异常[error]");
		}
		return con;
	}

	private String getURL() {
		// "jdbc:mysql://localhost:3306/ias100t?user=root&password=123456";
		
		String ip = MysqlUtil.getIP();
		ip = ip != null && !"".equals(ip) ? ip : "127.0.0.1";
		
		int port = MysqlUtil.getPort();
		port = port != 0 ? port : 3306;
		
		String u = MysqlUtil.getUsername();
		u = u != null && !"".equals(u) ? u : "root";
		
		String p = MysqlUtil.getPassword();
		p = p != null && !"".equals(p) ? p : "123456";
		
		StringBuffer sb = new StringBuffer();
		sb.append("jdbc:mysql://");
		sb.append(ip);
		sb.append(":");
		sb.append(port);
		sb.append("/");
		sb.append(dbname);
		sb.append("?user=");
		sb.append(u);
		sb.append("&password=");
		sb.append(p);
		sb.append("&useUnicode=true&characterEncoding=");
		sb.append(charset);

		return sb.toString();
	}
	
	

}
