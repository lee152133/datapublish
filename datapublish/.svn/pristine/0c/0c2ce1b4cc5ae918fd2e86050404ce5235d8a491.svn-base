package com.keda.webservice.server.utils;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.keda.webservice.server.Exception.WSException;


/**
 * webservice 连接的客户端
 *
 */
public class WSUtil {
	private WSUtil(){
		
	}
//	/**
//	 * webservice的服务地址
//	 */
//	private String target;//http://172.16.128.122:8080/WSTest/services/CourtService?wsdl
//
//	private String namespace; //http://kedacom.com
//	
//	private WSClient(String target, String namespace){
//		this.target = target;
//		this.namespace = namespace;
//	}
//	
//	/**
//	 * 获取服务器连接对象
//	 * @param target 服务器路径
//	 * @param namespace 资源相对路径
//	 * @return
//	 */
//	public static WSClient getClient(String target, String namespace){
//		WSClient client = new WSClient(target, namespace);
//		return client;
//	}
	
	
	/**
	 * 向服务器发送请求
	 * @param medthod 接口方法名称
	 * @param args 参数列表
	 * @return
	 * @throws AxisFault
	 * @throws WSException
	 */
	public static String doRequest(String target, String namespace, String method, Object[] args) throws AxisFault, WSException{
		
		// 指定 getGreeting 方法返回值的数据类 型的 Class 对象
		@SuppressWarnings("rawtypes")
		Class[] returnType = new Class[] {String.class};
		QName qname = new QName(namespace, method);
		
		RPCServiceClient client = createRPCServiceClient(target);
		Object[] response = client.invokeBlocking(qname, args, returnType);
		
		if(response == null || response.length == 0){
			throw new WSException("没有获得请求结果。");
		}else{
			return (String)response[0];
		}
	}

	private static RPCServiceClient createRPCServiceClient(String target) throws AxisFault{
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		// 指定调用 WebService 的 URL
		EndpointReference targetEPR = new EndpointReference(target);
		options.setTo(targetEPR);
		return serviceClient;
	}
	
}
