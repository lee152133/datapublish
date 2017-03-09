package com.keda.webservice.server.sxmx.req;

import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.keda.webservice.server.req.WSRequest;
import com.keda.webservice.server.sxms.domian.SXMX_Room;

public class UploadSXSInfoReq extends WSRequest {
	private static final Logger log = Logger.getLogger(UploadAJXXInfoReq.class);
	/** 审讯室id */
	private String id;
	/** 审讯室编号（暂定为区域id+审讯室名称） */
	private String roomNumber;
	/** 审讯室name */
	private String name;
	/** 审讯室状态 */
	private String status;
	/** 审讯室类型id */
	private String typeid;
	/** 审讯室类型名称 */
	private String typename;
	/** 审讯室ip */
	private String deviceip;
	/** 审讯室笔录id */
	private String recordid;
	/** 审讯室区域id */
	private String areaid;
	/** 审讯室区域名称 */
	private String areaname;
	/** 审讯室描述 */
	private String description;
	
	
	private List<SXMX_Room> rooms;

	@Override
	protected void appendChildParamter(StringBuffer sb) throws Exception {
		if(rooms != null && rooms.size() > 0) {
			for(int i=0;i<rooms.size();i++) {
				SXMX_Room r = rooms.get(i);
				if(r.getSstatus() != null && "1".equals(r.getSstatus())) {
					continue;
				}
				
				sb.append("<sxsxx>");
					sb.append("<id>").append(r.getId()).append("</id>");
					if(r.getRoomNumber() != null && !"".equals(r.getRoomNumber())) {
						sb.append("<roomNumber>").append(r.getRoomNumber()).append("</roomNumber>");
					} else {//暂定区域编号+审讯室id
						sb.append("<roomNumber>")
						.append(MessageFormat.format("{0}{1}{2}", r.getAreaid() , "-", r.getId()))
						.append("</roomNumber>");
					}
					sb.append("<name>").append(r.getName()).append("</name>");
					sb.append("<status>").append(r.getStatus()).append("</status>");
					sb.append("<typeid>").append(r.getTypeid()).append("</typeid>");
					sb.append("<typename>").append(r.getTypename()).append("</typename>");
					if (r.getDeviceip() != null) {
						sb.append(MessageFormat.format("<deviceip>{0}</deviceip>", r.getDeviceip()));
					} else {
						sb.append(MessageFormat.format("<deviceip>{0}</deviceip>", ""));
					}
					if (r.getRecordid() != null) {
						sb.append(MessageFormat.format("<recordid>{0}</recordid>", r.getRecordid()));
					} else {
						sb.append(MessageFormat.format("<recordid>{0}</recordid>", ""));
					}
					sb.append("<areaid>").append(r.getAreaid()).append("</areaid>");
					sb.append("<areaname>").append(r.getAreaname()).append("</areaname>");
					if (r.getDescription() != null) {
						sb.append(MessageFormat.format("<description>{0}</description>", r.getDescription()));
					} else {
						sb.append(MessageFormat.format("<description>{0}</description>", ""));
					}
				sb.append("</sxsxx>");
			}
		}

	}

	@Override
	protected void appendParamter(StringBuffer sb) throws Exception {
		sb.append("<id>").append(getMethod()).append("</id>");
		sb.append("<jcynumber>").append(getNo()).append("</jcynumber>");
	}

	@Override
	public String getMethod() {
		return "uploadSXSInfo";
	}

	@Override
	public String setInfo(Object o) {
		try {
			if (o instanceof SXMX_Room) {
				SXMX_Room r = (SXMX_Room) o;
				this.wrapRoom(r);
				log.info("[Wrap Room Success!]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据讯问室实体信息，包装讯问室请求信息
	 * @param r 讯问室实体信息
	 */
	private void wrapRoom(SXMX_Room r) {
		if(r != null){
			this.setId(String.valueOf(r.getId()));
			this.setRoomNumber(r.getRoomNumber());
			this.setName(r.getName());
			this.setStatus(r.getStatus());
			this.setTypeid(r.getTypeid());
			this.setTypename(r.getTypename());
			this.setDeviceip(r.getDeviceip());
			this.setRecordid(r.getRecordid());
			this.setAreaid(r.getAreaid());
			this.setAreaname(r.getAreaname());
			this.setDescription(r.getDescription());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
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

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getDeviceip() {
		return deviceip;
	}

	public void setDeviceip(String deviceip) {
		this.deviceip = deviceip;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SXMX_Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<SXMX_Room> rooms) {
		this.rooms = rooms;
	}
}
