package com.keda.webservice.sxmx.server;

/**
 * 自定义配置类，用于接收主系统的自定义配置
 * aaa
 * @author root
 * 
 */
public class SXMSConfig {
	/**
	 * 0:以案卷审讯次数显示 1:被讯问人次数
	 */
	private int is_show_caseperson_times;

	public int getIs_show_caseperson_times() {
		return is_show_caseperson_times;
	}

	public void setIs_show_caseperson_times(int is_show_caseperson_times) {
		this.is_show_caseperson_times = is_show_caseperson_times;
	}

}
