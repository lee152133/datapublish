package com.keda.webservice.server.utils;

import java.util.UUID;

public class ToolsUtil {

	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		if(str == null || str.trim().length() <= 0)
			return true;
		return false;
	}
	
	/**
	 * 字符串编码
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encoderStr(String str, boolean isEncoder) throws Exception{
		if(isEncoder)
			return BASE64Util.encodeBASE64(str);
		else 
			return str;
	}
	
	
	/**
	 * 字符串解码
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String decoderStr(String str, boolean isDecoder) throws Exception{
		if(isDecoder)
			return BASE64Util.decodeBASE64(str);
		else 
			return str;
	}
	
	public static void main(String[] args) {
		try {
			String s = ToolsUtil.decoderStr("5LiK6K+J5Lq6Ouael+i0pOadsDvooqvkuIror4nkuro65YyX5Lqs6YeR6ZO25bu654mp5Lia5pyN5Yqh5pyJ6ZmQ5YWs5Y+45Y2X5a6B5YiG5YWs5Y+4", true);
			
//			String s10 =  ToolsUtil.decoderStr("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request><AHDM>MTE5NzAwMDAyMDQxNzM0</AHDM><FYDM>QTUw</FYDM><ZT>MA==</ZT></Request>",true);
			
			System.out.println(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到全球唯一UUID
	 * @return 全球唯一UUID
	 */
	public static String getUUID(){ 
		String s = UUID.randomUUID().toString(); 
		//去掉"-"符号 
		return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);
	}
	

}
