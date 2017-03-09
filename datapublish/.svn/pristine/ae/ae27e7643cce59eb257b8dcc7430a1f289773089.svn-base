package com.keda.webservice.server.req;

import org.apache.log4j.Logger;

import com.keda.webservice.server.Exception.DataException;
import com.keda.webservice.server.utils.ToolsUtil;

/**
 * 请求的基类
 * 
 * 
 */
public abstract class WSRequest {
	private static final Logger log = Logger.getLogger(WSRequest.class);
	private String no;

	/**
	 * 是否要用base64编码
	 */
	private boolean base64Enable = true;

	/**
	 * 拼接xml参数
	 * 
	 * @throws DataException
	 * @throws Exception
	 */
	public String getXmlRequest() throws DataException {

		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			sb.append("<data>");
			//添加data节点参数
			appendParamter(sb);
			//添加sxsxx节点参数
			appendChildParamter(sb);
			sb.append("</data>");
			String xml = sb.toString();// encoded();
			return xml;
		} catch (Exception e) {
			log.debug("getXmlRequest()", e);
			throw new DataException("请求参数错误", e);
		}

	}

	/**
	 * 添加参数至StringBuffer
	 */
	protected abstract void appendParamter(StringBuffer sb) throws Exception;

	/**
	 * 添加参数至StringBuffer子节点
	 * 
	 * @param sb
	 * @throws Exception
	 */
	protected abstract void appendChildParamter(StringBuffer sb)
			throws Exception;

	/**
	 * 获取接口方法名称
	 * 
	 * @return
	 */
	public abstract String getMethod();
	
	/**
	 * 设置所需信息
	 * @return
	 */
	public abstract String setInfo(Object o);

	/**
	 * 字符串编码
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String encoded(String str) throws Exception {
		return ToolsUtil.encoderStr(str, base64Enable);
	}

	//
	// /**
	// * 返回请求处理类
	// * @return
	// */
	// public abstract Class<? extends WSResponse> getResponseType();

	public boolean isBase64Enable() {
		return base64Enable;
	}

	public void setBase64Enable(boolean base64Enable) {
		this.base64Enable = base64Enable;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}
