package com.keda.webservice.server.yctx.req;

import java.util.List;

import com.keda.webservice.server.req.WSRequest;
import com.keda.webservice.server.yctx.domian.YCTX_Room;

public class UploadYCTXRoominfoReq extends WSRequest {
	/**房间id*/
	private String id;
	/**房间名称*/
	private String name;
	/**讯问室使用状态，1：未使用，2：使用，3：网络不通*/
	private String status;
	/**讯问室设备类型，1：终端，2：MCU*/
	private String devicetype;
	/**讯问室ip地址*/
	private String ipaddress;
	/**终端类型 H800 H900*/
	private String mttype;
	
	private List<YCTX_Room> rooms;
	
	@Override
	protected void appendParamter(StringBuffer sb) throws Exception {
		sb.append("<id>").append(getMethod()).append("</id>");
		sb.append("<jcynumber>").append(getNo()).append("</jcynumber>");
	}

	@Override
	protected void appendChildParamter(StringBuffer sb) throws Exception {
		if(rooms != null && rooms.size() > 0) {
			for(int i=0;i<rooms.size();i++) {
				YCTX_Room r = rooms.get(i);
				sb.append("<txroominfo>");
					sb.append("<id>").append(r.getYctx_id()).append("</id>");
					sb.append("<name>").append(r.getYctx_name()).append("</name>");
					sb.append("<status>").append(r.getYctx_rmstatus()).append("</status>");
					sb.append("<devicetype>").append(r.getYctx_deviceType()).append("</devicetype>");
					sb.append("<mttype>").append(r.getYctx_mtType()).append("</mttype>");
				sb.append("</txroominfo>");
			}
		}
	}

	@Override
	public String getMethod() {
		return "uploadYCTXRoom";
	}

	@Override
	public String setInfo(Object o) {
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getMttype() {
		return mttype;
	}

	public void setMttype(String mttype) {
		this.mttype = mttype;
	}

	public List<YCTX_Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<YCTX_Room> rooms) {
		this.rooms = rooms;
	}

}
