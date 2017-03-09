package com.keda.webservice.server.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 解析相关xml工具类
 * @author hsb
 *
 */
public class XMLUtil {
	private static Logger log = Logger.getLogger(XMLUtil.class);
	/**
	 * 将xml字符串解析成Document
	 * @param xml
	 * @return
	 */
	public static Document parseXML(String xml){
		
		StringReader sr = new StringReader(xml);
		InputSource is = new InputSource(sr);
		
		Document dom = null;
		try {
			DocumentBuilderFactory ch = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = ch.newDocumentBuilder() ;
			dom = db.parse(is) ;
		}catch(Exception e){
			log.error("字符串不是有效的xml格式：" + xml, e);
		}
		return dom;
	}
	
	/**
	 * 返回文档中指定名称的节点,
	 * @param dom 文档
	 * @param tagName 字点名称
	 * @return　如果有多个同名的节点，返回首先找到的节点；如果没有定样的节点则返回null。
	 */
	public static Node getUniqueNode(Document dom, String tagName){
		NodeList list = dom.getElementsByTagName(tagName);
		
		int l = list.getLength();
		return l < 0 ? null : list.item(0);
	}
	
	/**
	 * 返回节点的指定子节点。
	 * @param parent 父节点
	 * @param tagName 子节点的名称
	 * @return 如果存在多个定样的节点，返回第一个；如果不存在定样的节点，返回null;
	 */
	public static Node getUniqueChildNode(Node parent, String tagName){
		NodeList chlds = parent.getChildNodes();
		
		for(int j2 = 0 ; j2 < chlds.getLength(); j2 ++){
			
			Node node = chlds.item(j2);
			String name = node.getNodeName();
			if(name != null && name.equals(tagName)){
				return node;
			}
			
		}
		//log.error("没有");
		return null;
	}
	/**
	 * 返回节点的指定子节点列表。
	 * @param parent 父节点
	 * @param tagName 子节点的名称
	 * @return 
	 */
	public static List<Node> getChildNodes(Node parent, String tagName){
		NodeList chlds = parent.getChildNodes();
		
		List<Node> list = new ArrayList<Node>(chlds.getLength());
		
		for(int j2 = 0 ; j2 < chlds.getLength(); j2 ++){
			
			Node node = chlds.item(j2);
			String name = node.getNodeName();
			if(name != null && name.equals(tagName)){
				list.add(node);
			}
			
		}
		
		return list;
	}
	
	/**
	 * 返回节点的属性值
	 * @param node　节点
	 * @param attribute　属性名称
	 * @return
	 */
	public static String getAttribute(Node node, String attributeName){
		NamedNodeMap attrs = node.getAttributes();
		Node attrNode = attrs.getNamedItem(attributeName);
		return attrNode != null ? attrNode.getNodeValue() : null;
	}
	
	public static int getIntAttribute(Node node, String attributeName){
		String v = getAttribute(node, attributeName);
		return v!= null ? Integer.parseInt(v) : 0;
	}
	
	public static long getLongAttribute(Node node, String attributeName){
		String v = getAttribute(node, attributeName);
		return v!= null ? Long.parseLong(v) : 0l;
	}
	
	public static String getChildNodeContent(Node parent, String tagName){
		Node node = getUniqueChildNode(parent, tagName);
		return node == null ? null : node.getTextContent();
	}
	public static int getIntChidNodeContent(Node parent, String tagName){
		Node node = getUniqueChildNode(parent, tagName);
		return node == null ? 0 : Integer.parseInt(node.getTextContent());
	}
	public static long getLongChidNodeContent(Node parent, String tagName){
		Node node = getUniqueChildNode(parent, tagName);
		return node == null ? 0l : Long.parseLong(node.getTextContent());
	}
}
