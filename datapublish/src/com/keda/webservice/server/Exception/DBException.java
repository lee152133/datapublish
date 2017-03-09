package com.keda.webservice.server.Exception;

/**
 * 描述数据库操作异常。在WEB项目中，一般DAO层的实现类可能会抛出此方法
 * @author TaoPeng
 * @referenceBy 基本所有的WEB工程都使用了此类
 */
public class DBException extends Exception {
	private static final long serialVersionUID = 1L;
	public static String desc = "inner.DBException.java.shujukufashenyichangqinglianxiguanliyuan";

	public DBException() {	}
	
	public DBException(String msg) {
		super(msg);
	}
	
	public DBException(Exception e){
		super(e);
	}
	public DBException(Throwable cause){
		super(cause);
	}
	public DBException(String message, Throwable throwable){
		super(message, throwable);
	}
}
