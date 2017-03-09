package com.keda.webservice.server.sxms.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 数据库帐户配置。获取/设置数据库用户名、密码。
 *
 */
public class MysqlUtil {
	private static Logger log = Logger.getLogger(MysqlUtil.class);
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 存放数据源密码的配置文件名称
	 */
	private static String DB_PWD_FILE = "/var/ftphome/SXMS/inquestdata/inquestcfg.properties";

	//默认配置。在spring-hibernate.xml中的默认配置。
	private static String defIP;
	private static int defPort;
	private static String defDbName = "kedachat";
	private static String defDbUsername;
	private static String defDbPassword;
	
	//用户配置。在kctcfg.properties中的自定义配置，用于用户修改数据库连接用户名或密码的情况。
	private static String ip;
	private static Integer port;
	private static String dbUsername;
	private static String dbPassword;
	
	private static boolean isInit = false;
	
	public static void updateUserAndPassword(DBConfig config) throws Exception{
		File file = new File(DB_PWD_FILE);
		updateDatabaseConfig(file, config);
	}

	private static void updateDatabaseConfig(File outFile, DBConfig config) throws IOException {

		String ip = config.getIp();
		int port = config.getPort();
		String url = buildURL(ip, port);
		String oldUsername = config.getOldUsername();
		String oldPassword = config.getOldPassword();
		String newUsername = config.getUsername();
		String newPassword = config.getPassword();
		
		log.debug("database url : " + url);
		
		PropertiesFile properties = new PropertiesFile();
		properties = loadConfigFromDefaultFile(properties);
		if(outFile.exists()){
			properties.load(outFile.getAbsolutePath());
		}else{
			outFile.createNewFile();
		}

		properties.setProperty("jdbc-0.proxool.driver-url", url);
		properties.setProperty("jdbc-0.user", newUsername);
		properties.setProperty("jdbc-0.password", newPassword);

		properties.setProperty("datasource.ip", ip);
		properties.setProperty("datasource.port", port);
		properties.setProperty("datasource.username", newUsername);
		properties.setProperty("datasource.password", newPassword);
		properties.save(outFile);
		
		//记录日志(日志可能不能成功记录在数据库中，所以输出到控制台)
		String msg = new Date() + "" + com.keda.webservice.server.sxms.dao.MysqlUtil.class + " : 数据库配置变更：url=" + url;
		System.out.println(msg);
		log.debug(msg);
	}

	private static String buildURL(String ip, int port){
		StringBuffer sb = new StringBuffer();
		sb.append("jdbc:mysql://");
		sb.append(ip);
		sb.append(":");
		sb.append(port);
		sb.append("/inquestdb3?characterEncoding=utf8");
		return sb.toString();
	}
	
	//加载默认的配置文件 classpath:proxool.properties
	private static PropertiesFile loadConfigFromDefaultFile(PropertiesFile properties) throws IOException{
		InputStream is = MysqlUtil.class.getClassLoader().getResourceAsStream("proxool.properties");
		properties = new PropertiesFile();
		properties.load(is);
		return properties;
		
	}
	public static class DBConfig{
		private String ip = "127.0.0.1";
		private int port = 3306;
		private String username;
		private String password;
		private String oldUsername;
		private String oldPassword;
		public String getIp() {
			return ip;
		}
		public int getPort() {
			return port;
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		public void setPort(int port) {
			this.port = port;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getOldUsername() {
			return oldUsername;
		}
		public String getOldPassword() {
			return oldPassword;
		}
		public void setOldUsername(String oldUsername) {
			this.oldUsername = oldUsername;
		}
		public void setOldPassword(String oldPassword) {
			this.oldPassword = oldPassword;
		}
		
	}
	
	/**
	 * 初始化
	 */
	public static void init(){
		if(!isInit){
			load();
			isInit = true;
		}
	}
	
	private static void tryInit(){
		if(!isInit){
			init();
		}
	}
	
	/**
	 * 从文本中加载数据库配置。
	 */
	private static void load(){
		/*
		 * 本方法内，后读取的配置会覆盖先读取的配置。
		 * 在TSMS V1.0中，配置文件固定为“/root/kctwebdata/kctcfg.properties”,
		 * 在TSMS V2.0中，配置文件固定为“/opt/ftphome/TSMS/kctwebdata/kctcfg.properties”
		 */
		
		//加载1.0版本的配置文件（兼容以前版本）
		String fileV1 = "/root/kctwebdata/kctcfg.properties";
		File f1 = new File(fileV1);
		loadConfigFile(f1);
		
		//加载标准版的配置文件
		File f2 = new File(DB_PWD_FILE);// “kctwebdata/kctcfg.properties”
		loadConfigFile(f2);
	}
	/**
	 * 读取数据库配置文件
	 * @param file
	 */
	private static void loadConfigFile(File file){
		
		if(!file.exists()){
			log.debug("文件不存在:" + file);
			return;
		}
		
		PropertiesFile pFile = new PropertiesFile();
		try {
			log.debug("load db config:" + file);
			pFile.load(file.getAbsolutePath());
			String ip = pFile.getProperty("datasource.ip");
			String _port = pFile.getProperty("datasource.port");
			String u = pFile.getProperty("datasource.username");
			String p = pFile.getProperty("datasource.password");
			int port = 0;
			if(_port != null && _port.trim().length() > 0){
				port = Integer.parseInt(_port);
			}
			
			if(ip != null && ip.trim().length() > 0){
				MysqlUtil.ip = ip;
			}
			if(port > 0){
				MysqlUtil.port = port;
			}
			
			if(u != null && u.trim().length() > 0){
				MysqlUtil.dbUsername = u;
			}
			
			if(p != null){
				MysqlUtil.dbPassword = p;
			}
			
		} catch (IOException e) {
			log.error(" load jdbcPath.properties fail : jdbcFile= " + file, e);
		}
	}


	/**
	 * 更新连接数据库的密码。更新后会重写文件“kctwebdata/kctcfg.properties“
	 * @param oldPasswd
	 * @param newPwd
	 * @throws Exception
	 */
	public static void updatePassword(String username, String oldPassword, String newPassword) throws Exception{
		updateUserAndPassword(username,oldPassword, username, newPassword );
	}

	/**
	 * 更新连接数据库的用户名和密码。更新后会重写文件“kctwebdata/kctcfg.properties“
	 * @param oldUsername
	 * @param oldPassword
	 * @param newUsername
	 * @param newPassword
	 * @throws Exception
	 */
	public static void updateUserAndPassword(String oldUsername, String oldPassword, String newUsername, String newPassword) throws Exception{
		File file = new File(DB_PWD_FILE);// kctwebdata/kctcfg.properties
		updateUserAndPassword0(file, oldUsername, oldPassword, newUsername, newPassword);
	}
	
	/**
	 * 更新连接数据库的用户名和密码。更新后会重写指定的配置文件file.
	 * @param file 更新的目标文件
	 * @param oldUsername 原用户名
	 * @param oldPassword 原密码
	 * @param newUsername 新用户名
	 * @param newPassword 新密码
	 * @throws Exception
	 */
	private static void updateUserAndPassword0(File file, String oldUsername, String oldPassword, String newUsername, String newPassword) throws Exception{
		
		String oldU = null;
		String oldP = null;
		
		PropertiesFile kctcfg = new PropertiesFile();
		if(file.exists()){
			kctcfg.load(file.getAbsolutePath());
			oldU = kctcfg.getProperty("datasource.username");
			oldP = kctcfg.getProperty("datasource.password");
		}
		
		oldU = oldU == null ? dbUsername : oldU;
		oldP = oldP == null ? dbPassword : oldP;
		
		if(!oldU.equals(oldUsername)){
			throw new Exception("原帐户不正确。");
		}
		if(!oldPassword.equals(oldP)){
			throw new Exception("原密码不正确。");
		}

		dbUsername = newUsername;
		dbPassword = newPassword;
		
		kctcfg.setProperty("datasource.username", newUsername);
		kctcfg.setProperty("datasource.password", newPassword);
		kctcfg.save(file);
		
	}

	/**
	 * 获取数据库服务器的地址
	 * @return
	 */
	public static String getIP(){
		tryInit();
		return ip != null ? ip : defIP;
	}
	
	/**
	 * 获取数据库服务器的端口
	 * @return
	 */
	public static int getPort(){
		tryInit();
		return port != null ? port : defPort;
	}
	/**
	 * 获取数据库连接字符串。返回的字符串符合JDBC规范，例如：jdbc:mysql://localhost:3306/kctdb1?autoReconnect=true&autoReconnectForPools=true&useunicode=true&characterEncoding=utf8
	 * @return
	 */
	public static String getURL(){
		return getURL("utf8");
	}
	public static String getURL(String charset){
		tryInit();
		String ip = getIP();
		int port = getPort();
		return getMysqlDBURL(ip, port, charset);
	}

	/**
	 * 获取指定的数据库连接字符串（仅支持Mysql）。
	 * @param ip 数据库服务器地址
	 * @param port 数据库服务器端口
	 * @return 返回连接字符串，不包括用户名和密码
	 */
	public static String getMysqlDBURL(String ip, int port){
		return getMysqlDBURL(ip, port, "utf8");
	}
	public static String getMysqlDBURL(String ip, int port, String charset){
		if(charset == null){
			charset = "utf8";
		}
		String url = "jdbc:mysql://{0}:{1}/{2}?autoReconnect=true&autoReconnectForPools=true&useunicode=true&characterEncoding={3}";
		url = MessageFormat.format(url, ip, String.valueOf(port), defDbName, charset);
		return url;
	}
	
	/**
	 * 返回数据库用户名
	 * @return
	 */
	public static String getUsername() {
		tryInit();
		return dbUsername != null ? dbUsername : defDbUsername;
	}

	/**
	 * 返回数据库密码。
	 * @return
	 */
	public static String getPassword() {
		tryInit();
		return dbPassword != null ? dbPassword : defDbPassword;
	}

	public static void setDefaultIP(String ip){
		MysqlUtil.defIP = ip;
	}
	
	public static void setDefaultPort(int port){
		MysqlUtil.defPort = port;
	}
	/**
	 * 设置默认的数据库连接用户名
	 * @param username
	 */
	public static void setDefaultUsername(String username) {
		MysqlUtil.defDbUsername = username;
	}

	/**
	 * 设置默认的数据库连接密码
	 * @param password
	 */
	public static void setDefaultPassword(String password) {
		MysqlUtil.defDbPassword = password;
	}

	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		String url = getURL();
		String u = getUsername();
		String p = getPassword();
		log.debug("KEDACHAT 链接数据库信息URL:"+url+"，，，，用户名"+u+",,,,密码"+p);
		Connection conn = DriverManager.getConnection(url, u, p);
		return conn;
	}

	public static void setDbConfigFile(String file){
		DB_PWD_FILE = file;
	}
	
	public static void main(String[] args) {
		try {
//			updatePassword("123456", "1234567");
//			updateUserAndPassword("root2", "123456", "root1", "123456");
			load();
			System.out.println(getUsername());
			System.out.println(getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class PropertiesFile{
	
	private Properties properties = new Properties();
	
	private String filePath;
	
	public void load(String filePath) throws IOException {
		this.filePath = filePath;
		InputStream in = new FileInputStream(filePath);
		this.properties.load(in);
		in.close();
	}

	public void load(InputStream inputStream) throws IOException {
		this.properties.load(inputStream);
	}
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public void setProperty(String key, String value){
		properties.setProperty(key, value);
	}
	
	public void setProperty(String key, int value){
		properties.setProperty(key, String.valueOf(value));
	}
	/**
	 * 保存配置属性
	 * @throws IOException
	 */
	public void save() throws IOException{
		if(filePath != null){
			this.save(filePath);
		}
	}

	public void save(String file) throws IOException {
		File f = new File(file);
		this.save(f);
	}

	public void save(File file) throws IOException {
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream out = new FileOutputStream(file);
		properties.store(out, null);
		out.close();
	}
}