package com.keda.webservice.server.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 提供日志缓存功能
 * @author TaoPeng
 *
 */
public class LogCacheUtil {

	private static final Logger log = Logger.getLogger(LogCacheUtil.class);
	
	private static LogCacheUtil instance;
	public static LogCacheUtil getInstance(){
		if(instance == null ){
			instance = new LogCacheUtil();
		}
		return instance;
	}
	
	private static int cacheSize = 2000;
	private static LinkedList<String> cache = new LinkedList<String>();
	
	
	/**
	 * 存入日志
	 * @param message
	 */
	public synchronized void put(String message){
		
		log.debug(message);
		
		checkCacheSize();
		
		message = p(message);
		
		cache.addLast(message);
		
	}

	private String p(String message){
		StringBuffer sb = new StringBuffer();
		sb.append(getNow());
		sb.append("\t");
		sb.append(message);
		return sb.toString();
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static String getNow(){
		return sdf.format(new Date());
	}
	
	/**
	 * 存入日志
	 * @param message 多个字符串将被拼接在一起
	 */
	public synchronized void put(Object ... message){
		StringBuffer sb = new StringBuffer();
		for(Object msg : message){
			sb.append(msg);
		}
		put(sb.toString());
	}
	/**
	 * 存入日志
	 * @param message 多个字符串将被拼接在一起
	 */
	public synchronized void put(String message, Throwable cause){
		
		log.error(message, cause);
		
		StringBuffer sb = new StringBuffer();
		sb.append(message);
		sb.append(cause);
		put(sb.toString());
	}
	/**
	 * 获取所有日志
	 * @return
	 */
	public synchronized List<String> getAll(){
		ArrayList<String> list = new ArrayList<String>(cache.size());
		list.addAll(cache);
		return list;
	}
	
	private void checkCacheSize(){
		if(cache.size() > cacheSize){
			cache.removeFirst();
		}
	}
	
	public synchronized void clear(){
		cache.clear();
	}
}
