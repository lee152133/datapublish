package com.keda.webservice.server.webservice.v1;

import org.apache.axis2.AxisFault;
import org.apache.log4j.Logger;

import com.keda.webservice.server.Exception.WSException;
import com.keda.webservice.server.req.WSRequest;
import com.keda.webservice.server.sxmx.req.UploadSXSInfoReq;
import com.keda.webservice.server.webservice.v1.SpzxoutServiceImplServiceStub.DoCommandE;
import com.keda.webservice.server.webservice.v1.SpzxoutServiceImplServiceStub.DoCommandResponseE;

public class IWSClient{
	
	private static final Logger log = Logger.getLogger(IWSClient.class);
	
	private static IWSClient instance;
	
	private static String targetEndpoint = "http://172.16.128.132:8125/jkzx/ws/spzx";
	
	private IWSClient(){}
	
	public static IWSClient getInstance(){
		if(instance == null){
			instance = new IWSClient();
		}
		return instance;
	}
	
	private SpzxoutServiceImplServiceStub service;

	private SpzxoutServiceImplServiceStub getStub() throws AxisFault{
		if(service == null){
			service = new SpzxoutServiceImplServiceStub(targetEndpoint);
			service._getServiceClient().getOptions().setTimeOutInMilliSeconds(15000);
		}
		return service;
	}
	
	public void setTargetEndpoint(String targetEndpoint) {
		IWSClient.targetEndpoint = targetEndpoint;
		service = null;
	}

	/**
	 * 调用docommand方法
	 * @param request
	 * @return
	 * @throws WSException
	 */
	public String doCommand(WSRequest request) throws WSException {
		
		try {
			String xml = request.getXmlRequest();
			if(!"uploadBLXXInfo".equals(request.getMethod())) {//笔录以文件流方式传递 写日志影响效率
				log.debug("【发送】 ==> " + xml);
			}
			SpzxoutServiceImplServiceStub.DoCommand req = new SpzxoutServiceImplServiceStub.DoCommand();
			req.setXml(xml);
			
			DoCommandE reqE = new DoCommandE();
			reqE.setDoCommand(req);
			
			String result;
			
			try{
				SpzxoutServiceImplServiceStub stub = getStub();
				DoCommandResponseE respE = stub.doCommand(reqE);
				result = respE.getDoCommandResponse().get_return();
			}catch(Exception e){
				throw new WSException(e.getMessage(), e);
			}
			if(!"uploadBLXXInfo".equals(request.getMethod())) {//笔录以文件流方式传递 写日志影响效率
				log.debug("【接收】 <== " + result);
			}
			
			return result;
		} catch (Exception e) {
			log.error("doCommand error:", e);
			throw new WSException(e);
		}
	
	}
	
	
	public static void main(String[] args) {
		try {
			
			UploadSXSInfoReq req = new UploadSXSInfoReq();
			String result = IWSClient.getInstance().doCommand(req);
			System.out.println(result);
			 // 使用RPC方式调用WebService  
//	        RPCServiceClient serviceClient = new RPCServiceClient();  
//	        Options options = serviceClient.getOptions();  
//	        // 指定调用WebService的URL  
//	        EndpointReference targetEPR = new EndpointReference(  
//	                "http://172.16.128.132:8125/jkzx/ws/spzx");  
//	        options.setTo(targetEPR);  
//	        // 调用方法的参数值  
//	        Object[] entryArgs = new Object[] {1, 2};  
//	        // 调用方法返回值的数据类型的Class对象  
//	        Class[] classes = new Class[] { float.class };  
//	        // 调用方法名及WSDL文件的命名空间  
//	        // 命名空间是http://localhost:8080/axis2/services/CalculateService?wsdl中wsdl:definitions标签targetNamespace属性  
//	        QName opName = new QName("http://server.webservice.keda.com", "DoCommand");  
//	        // 执行方法获取返回值  
//	        // 没有返回值的方法使用serviceClient.invokeRobust(opName, entryArgs)  
//	        Object result = serviceClient.invokeBlocking(opName, entryArgs, classes)[0];  
//	        System.out.println(result);  
	        // out: 3.0  
	        
		} catch (Exception e) {
			System.out.println("连接失败");
			e.printStackTrace();
		}
	}
}
