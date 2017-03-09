package com.keda.webservice.server.sxms.domian;

import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;

import com.keda.webservice.server.utils.TimeUtil;

/**
 * @Company: 苏州科达
 * @Description: 笔录信息
 * @author： cuiqingqiang
 * @date：2016年8月23日 下午4:57:07
 */
public class SXMX_Record {

	/** 笔录id */
	private Long id;
	
	/** 审讯状态 1：审讯中；0：已审讯 */
	private String status;
	
	/** 案卷编号 */
	private String caseId;
	
	/** 案卷名称 */
	private String caseName;
	
	/** 笔录名称 */
	private String blName;
	/**
	 * 笔录number
	 */
	private String blNumber;
	
	/** 涉案人员名称 */
	private String personName;
	
	/** 关联的审讯室id */
	private String roomId;
	
	/** 审讯室名称 */
	private String roomName;
	
	/** 关联的远程审讯室id */
	private String remoteRoomId;
	
	/** 关联的远程审讯室名称 */
	private String remoteRoomName;
	
	/** 开始时间 */
	private String startTime;
	
	/** 结束时间 */
	private String endTime;
	
	/** 笔录文件名 */
	private String content;
	
	/** 创建者名称 */
	private String createrName;
	
	/** 刻录人名称 */
	private String burnerName;
	
	/** 刻录开始时间 */
	private String burnStartTime;
	
	/** 刻录结束时间 */
	private String burnEndTime;
	
	/** 录像人名称 */
	private String videoerName;
	
	/** 录像开始时间 */
	private String videoStartTime;
	
	/** 录像结束时间 */
	private String videoEndTime;
	
	/** 录像来源 */
	private String source;
	
	/** 录像类型 */
	private String recordType;
	
	/** 记录员 */
	private String recorder;
	
	/** 第几次笔录 */
	private String recordTimes;
	
	/** 关联的审讯主机FullId */
	private String deviceId;
	
	/** 修改平台录像名称状态:-1：修改失败,0：未修改,1：修改成功 */
	private String aliasStatus;
	
	/** 被讯问人次数 */
	private String askTimes;
	
	/** 审讯时的审讯主机截图路径 */
	private String imgPath;
	
	/** 笔录内容（文本字符串,UTF-8编码）当状态为1时 */
	private String blnr;
	
	/** 笔录内容（文件BASE64编码）当状态为0时,只有审讯完之后才传 */
	private String blnrDoc;
	
	/** 视频直播（实时）设备id */
	private String zbDevId;
	
	/** 视频直播（实时）设备名称 */
	private String zbDeviceName;
	
	/** 视频直播（实时）设备类型 */
	private String zbDevType;
	
	/** 视频直播（实时）备注 */
	private String zbRemark;
	
	/** 视频直播（录像）设备id */
	private String dbDevId;
	
	/** 视频直播（录像）设备名称 */
	private String dbDeviceName;
	
	/** 视频直播（录像）设备类型 */
	private String dbDevType;
	
	/** 视频直播（录像）备注 */
	private String dbRemark;
	
	/**
	 * 区域id
	 */
	private String areaId;
	
	/**
	 * 区域别名
	 */
	private String aliasesName;
	private String stype;
	private String sname;
	private String sstatus;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getBlName() {
		return blName;
	}

	public void setBlName(String blName) {
		this.blName = blName;
	}

	public String getBlNumber() {
		return blNumber;
	}

	public void setBlNumber(String blNumber) {
		this.blNumber = blNumber;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRemoteRoomId() {
		return remoteRoomId;
	}

	public void setRemoteRoomId(String remoteRoomId) {
		this.remoteRoomId = remoteRoomId;
	}

	public String getRemoteRoomName() {
		return remoteRoomName;
	}

	public void setRemoteRoomName(String remoteRoomName) {
		this.remoteRoomName = remoteRoomName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

	public String getBurnerName() {
		return burnerName;
	}

	public void setBurnerName(String burnerName) {
		this.burnerName = burnerName;
	}

	public String getBurnStartTime() {
		return burnStartTime;
	}

	public void setBurnStartTime(String burnStartTime) {
		this.burnStartTime = burnStartTime;
	}

	public String getBurnEndTime() {
		return burnEndTime;
	}

	public void setBurnEndTime(String burnEndTime) {
		this.burnEndTime = burnEndTime;
	}

	public String getVideoerName() {
		return videoerName;
	}

	public void setVideoerName(String videoerName) {
		this.videoerName = videoerName;
	}

	public String getVideoStartTime() {
		return videoStartTime;
	}

	public void setVideoStartTime(String videoStartTime) {
		this.videoStartTime = videoStartTime;
	}

	public String getVideoEndTime() {
		return videoEndTime;
	}

	public void setVideoEndTime(String videoEndTime) {
		this.videoEndTime = videoEndTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getRecordTimes() {
		return recordTimes;
	}

	public void setRecordTimes(String recordTimes) {
		this.recordTimes = recordTimes;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getAliasStatus() {
		return aliasStatus;
	}

	public void setAliasStatus(String aliasStatus) {
		this.aliasStatus = aliasStatus;
	}

	public String getAskTimes() {
		return askTimes;
	}

	public void setAskTimes(String askTimes) {
		this.askTimes = askTimes;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getBlnr() {
		return blnr;
	}

	public void setBlnr(String blnr) {
		this.blnr = blnr;
	}

	public String getBlnrDoc() {
		return blnrDoc;
	}

	public void setBlnrDoc(String blnrDoc) {
		this.blnrDoc = blnrDoc;
	}

	public String getZbDevId() {
		return zbDevId;
	}

	public void setZbDevId(String zbDevId) {
		this.zbDevId = zbDevId;
	}

	public String getZbDeviceName() {
		return zbDeviceName;
	}

	public void setZbDeviceName(String zbDeviceName) {
		this.zbDeviceName = zbDeviceName;
	}

	public String getZbDevType() {
		return zbDevType;
	}

	public void setZbDevType(String zbDevType) {
		this.zbDevType = zbDevType;
	}

	public String getZbRemark() {
		return zbRemark;
	}

	public void setZbRemark(String zbRemark) {
		this.zbRemark = zbRemark;
	}

	public String getDbDevId() {
		return dbDevId;
	}

	public void setDbDevId(String dbDevId) {
		this.dbDevId = dbDevId;
	}

	public String getDbDeviceName() {
		return dbDeviceName;
	}

	public void setDbDeviceName(String dbDeviceName) {
		this.dbDeviceName = dbDeviceName;
	}

	public String getDbDevType() {
		return dbDevType;
	}

	public void setDbDevType(String dbDevType) {
		this.dbDevType = dbDevType;
	}

	public String getDbRemark() {
		return dbRemark;
	}

	public void setDbRemark(String dbRemark) {
		this.dbRemark = dbRemark;
	}
	

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAliasesName() {
		return aliasesName;
	}

	public void setAliasesName(String aliasesName) {
		this.aliasesName = aliasesName;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSstatus() {
		return sstatus;
	}

	public void setSstatus(String sstatus) {
		this.sstatus = sstatus;
	}

	public String getPath() {
		String area = this.getAliasesName();
		Calendar c = Calendar.getInstance();
		Timestamp t = TimeUtil.valueOfTimestamp(this.getStartTime());
		if(t == null){
			this.setStartTime(TimeUtil.getTimestamp(new Timestamp(System.currentTimeMillis())));
		}
		c.setTimeInMillis(TimeUtil.valueOfTimestamp(this.getStartTime()).getTime());
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH) +1;
		int d = c.get(Calendar.DAY_OF_MONTH);
		String path = area + File.separator + y +File.separator +m+File.separator +d+File.separator;
		return path ;
	}
	
}
