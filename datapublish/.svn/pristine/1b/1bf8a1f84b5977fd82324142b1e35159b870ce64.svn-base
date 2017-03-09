package com.keda.webservice.server;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class test {

	public static void main(String[] args) throws AxisFault {

		String xml = " <?xml version='1.0' encoding='utf-8'?>"
				+ "<data><id>uploadSXSInfo</id>" + "<number>1</number>"
				+ "<sxsxx>" + "<id>1</id>" + "<number>1号</number>"
				+ "<name>ff</name>" + "<status>1</status>"
				+ "<type_id>1</type_id>" + "<typename>gg</typename>"
				+ "<device_ip>123456</device_ip>" + "<record_id>1</record_id>"
				+ "<area_id>1</area_id>" + "<areaname>hhh</areaname>"
				+ "<description>jjjjjj</description>" + "</sxsxx>";

		Class[] returnType = new Class[] { String.class };

		Object[] obj = new Object[] { xml };
		QName qname = new QName("http://server.webservice.keda.com", "DoCommand");

		RPCServiceClient client = createRPCServiceClient("http://172.16.128.132:8125/jkzx/ws/spzx?wsdl");
		Object[] response = client.invokeBlocking(qname, obj, returnType);

		if (response == null || response.length == 0) {
			try {
				throw new Exception("没有获得请求结果。");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println((String) response[0]);
		}

	}

	private static RPCServiceClient createRPCServiceClient(String string) throws AxisFault {
		RPCServiceClient serviceClient = new RPCServiceClient();
		Options options = serviceClient.getOptions();
		// 指定调用 WebService的 URL
		EndpointReference targetEPR = new EndpointReference(string);
		options.setTo(targetEPR);
		return serviceClient;
	}
}
