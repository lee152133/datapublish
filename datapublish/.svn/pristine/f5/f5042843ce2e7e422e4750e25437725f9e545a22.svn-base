package com.keda.webservice.server.res;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.keda.webservice.server.Exception.WSException;
import com.keda.webservice.server.utils.ToolsUtil;
import com.keda.webservice.server.utils.XMLUtil;

public abstract class WSResponse {
	private static final Logger log = Logger.getLogger(WSResponse.class);

	private String code;
	private String msg;
	/**
	 * 是否要base64解码
	 */
	private boolean isDecoder = true;

	/**
	 * 解析服务器返回的数据
	 * 
	 * @param xmlData
	 * @throws WSException
	 */
	public void parseXml(String xmlData) throws WSException {
		try {
			
			log.debug("接收 <= " + xmlData);
			
			Document doc = XMLUtil.parseXML(xmlData);
			Node resp = XMLUtil.getUniqueNode(doc, "result");
			
			Node codeNode = XMLUtil.getUniqueChildNode(resp, "errcode");
			this.code = codeNode.getTextContent();
			
			Node msgNode = XMLUtil.getUniqueChildNode(resp, "errmsg");
			this.msg = msgNode.getTextContent();
			
			log.debug("code:" + code + ";msg:" + msg);
			
			if (!ToolsUtil.isNull(code) && !code.equals("0"))
				throw new WSException(msg);
			
		} catch (Exception e) {
			throw new WSException(e.getMessage(), e);
		}
	}

	/**
	 * 字符串解码
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String decode(String str) throws Exception {
		if (str == null) {
			return str;
		} else {
			return ToolsUtil.decoderStr(str, isDecoder);
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isDecoder() {
		return isDecoder;
	}

	public void setDecoder(boolean isDecoder) {
		this.isDecoder = isDecoder;
	}

	public abstract void parseData(Node dataNode) throws Exception;

}
