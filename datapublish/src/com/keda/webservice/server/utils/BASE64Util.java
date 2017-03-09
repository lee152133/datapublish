package com.keda.webservice.server.utils;

import org.apache.axiom.util.base64.Base64Utils;

/**
 * Base64编解码
 * @author liyunwei
 *
 */
public class BASE64Util {
	
	/**
	 * 用Base64编码
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String encodeBASE64(String str){
		if(ToolsUtil.isNull(str))
			return "";
		
		byte[] bytes = str.getBytes();
		return Base64Utils.encode(bytes);
	}
	
	/**
	 * 用Base64解码
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String decodeBASE64(String str){
		if(str == null)
			return "";
		
		byte[] bytes = Base64Utils.decode(str);
		return new String(bytes);
	}


	public static void main(String[] args) {
		String s = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48UmVzcG9uc2U+PFJlc3VsdD48Q29kZT5NQT09PC9Db2RlPjxNc2c+PC9Nc2c+PC9SZXN1bHQ+PERhdGEgQ291bnQ9Ik1RPT0iID48WlpKRz48Rlk+U3pBdzwvRlk+PEpHQlM+TVRnd01qSTBNREF3PC9KR0JTPjxNQz41cldMNksrVjZZT282WmVvPC9NQz48RkJNPjwvRkJNPjxKQz41cldMNksrVjZZT288L0pDPjxMWFI+NXAyTzVwK1E8L0xYUj48TFhESD5NVFU0TVRJek5EVTJOemc9PC9MWERIPjxEWj41Ym0vNktXLzZhdVk2Wm1pZU9hbHZIbmxycVE9PC9EWj48UENGVD5NQT09PC9QQ0ZUPjwvWlpKRz48L0RhdGE+PC9SZXNwb25zZT4=";
		String ss = "5oG25q+S55qE5aSa5bGC5qyhdiDlvJflhbDlhYvmlq/lsIbmlL7lvIDkuobmiYvmnLrlsJbls7Dml7bliLvntK/orqHlj5HnlJ/nuqDnurforr7nq4vogIPngrnpmYTov5Horr7nq4sNCg==";
		String sss= "NA==";
		try {
			System.out.println(decodeBASE64(sss));
			
//			String ssss = "MjAxNC0wMy0xNCAwMDowMDowMA";
//			MjAxNC0wMy0xNCAwMDowMDowMA==</QSSJ><JZSJ>MjAxNC0wMy0xNCAyMzo1OTo1OQ==
//			System.out.println(encodeBASE64(ssss));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
