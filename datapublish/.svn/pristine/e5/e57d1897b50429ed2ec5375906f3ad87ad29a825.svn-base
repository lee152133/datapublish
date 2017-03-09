package com.keda.webservice.server.sxms.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * mysql C3P0数据连接池
 * 
 * @author root
 * 
 */
public class C3P0FactoryDatasource {
	private static Logger log = Logger.getLogger(C3P0FactoryDatasource.class);

	public static String ip = "10.10.11.45";
	public static int port = 3060;
	public static String dbname = "inquestdb3";
	public static String username = "admin";
	public static String password = "adminkdm";
	public static String charset = "utf-8";
	public static int maxpoolsize = 200;
	public static int minpoolsize = 10;
	public static int timeout = 3000;
	
	public C3P0FactoryDatasource() {

	}
	private static ComboPooledDataSource ds = null;
	static {
		try {
			
			ip = MysqlUtil.getIP();
			ip = ip != null && !"".equals(ip) ? ip : "127.0.0.1";
			
			port = MysqlUtil.getPort();
			port = port != 0 ? port : 3060;
			
			username = MysqlUtil.getUsername();
			username = username != null && !"".equals(username) ? username : "admin";
			
			password = MysqlUtil.getPassword();
			password = password != null && !"".equals(password) ? password : "adminkdm";
			
			//=========================================================================
			
			ds = new ComboPooledDataSource();
			// 设置JDBC的Driver类
			ds.setDriverClass("com.mysql.jdbc.Driver"); // 参数由 Config 类根据配置文件读取
			// 设置JDBC的URL
			ds.setJdbcUrl(MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", ip, String.valueOf(port), dbname));
			// 设置数据库的登录用户名
			ds.setUser(username);
			// 设置数据库的登录用户密码
			ds.setPassword(password);
			// 设置连接池的最大连接数
			ds.setMaxPoolSize(maxpoolsize);
			// 设置连接池的最小连接数
			ds.setMinPoolSize(minpoolsize);
			// 连接关闭时默认将所有未提交的操作回滚。默认为false；
			ds.setAutoCommitOnClose(false);
			// 当连接池中的连接用完时，C3P0一次性创建新连接的数目2
			ds.setAcquireIncrement(2);
			// acquireRetryAttempts：定义在从数据库获取新连接失败后重复尝试获取的次数，默认为30秒
			ds.setAcquireRetryAttempts(30);
			// 两次连接中间隔时间，单位毫秒，默认为1000;
			ds.setAcquireRetryDelay(timeout);
			

		} catch (PropertyVetoException e) {
			log.error("C3P0初始化连接池失败", e);
			System.out.println("C3P0初始化连接池失败:" + e);
		}
	}

	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			log.error("C3P0获取连接池失败", e1);
			System.out.println("C3P0获取连接池失败:" + e1);
		}
		return con;
	}

	/**
	 * 关闭连接
	 * @param con
	 * @param stms
	 * @param rs
	 */
	public static void close(Connection con, Statement stms, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				log.error("rs error... 数据库close失败", e);
			}
		}
		if (stms != null) {
			try {
				stms.close();
				stms = null;
			} catch (SQLException e) {
				log.error("stms error... 数据库close失败", e);
			}
		}
		if (con != null) {
			try {
				con.close();
				con = null;
			} catch (SQLException e) {
				log.error("con error... 数据库close失败", e);
			}
		}
	}
	
	/**
	 * 回滚事务
	 * @param con
	 */
	public static void rollback(Connection con){
		try {
			con.rollback();
		} catch (SQLException e) {
			log.error("数据库rollback失败", e);
		}
	}

	public static void main(String[] args) {
		System.out.println(MessageFormat.format("jdbc:mysql://{0}:{1}/{2}", ip, String.valueOf(port), dbname));
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// 连接数据库
		Connection conn = C3P0FactoryDatasource.getConnection();
		PreparedStatement statement = null;
		String sql = "update spzx_rooms set recordid=?,status=?  where number=? and name = ?";
		try {
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, 488);
			statement.setInt(2, 1);
			statement.setString(3, "530101");
			statement.setString(4, "多少分城市达到");
			statement.execute();
			conn.commit();
		} catch (SQLException e) {
			log.error("数据异常", e);
		} finally {
			C3P0FactoryDatasource.close(conn, statement, null);
		}

	}

}
