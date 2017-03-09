package com.keda.webservice.server.sxmx.req;


import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.keda.webservice.server.req.WSRequest;
import com.keda.webservice.server.sxms.domian.SXMX_Record;
import com.keda.webservice.server.utils.FileUtil;
import com.keda.webservice.sxmx.server.SXMS_PulishTask;

public class UploadBLXXInfoReq extends WSRequest {

	private static final Logger log = Logger.getLogger(UploadBLXXInfoReq.class);
	
	private static String RECORDPATH = "/var/ftphome/SXMS/inquestdata/record/";
	
	/** 笔录id */
	private String id;
	
	/** 审讯状态 1：审讯中；0：已审讯 */
	private String status;
	
	/** 案卷编号 */
	private String caseId;
	
	/** 案卷名称 */
	private String caseName;
	
	/** 笔录名称 */
	private String blName;
	
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
	
	
	private List<SXMX_Record> records;
	
	
	@Override
	protected void appendParamter(StringBuffer sb) throws Exception {
		sb.append("<id>").append(getMethod()).append("</id>");
		sb.append("<jcynumber>").append(getNo()).append("</jcynumber>");
	}

	@Override
	protected void appendChildParamter(StringBuffer sb) throws Exception {
		if(records != null && records.size() > 0) {
			for(int i=0;i<records.size();i++) {
				SXMX_Record r = records.get(i);
				if(r.getSstatus() != null && "1".equals(r.getSstatus())) {
					continue;
				}
				sb.append("<blxx>");
					sb.append("<id>").append(r.getId()).append("</id>");
					sb.append("<status>").append(r.getStatus()).append("</status>");
					sb.append("<case_id>").append(r.getCaseId()).append("</case_id>");
					sb.append("<casename>").append(r.getCaseName()).append("</casename>");
					sb.append("<blname>").append(r.getBlName()).append("</blname>");
					if(r.getBlNumber() != null) {
						sb.append("<blnumber>").append("").append("</blnumber>");
					} else {
						sb.append("<blnumber>").append(MessageFormat.format("{0}{1}{2}", r.getAreaId() , "-", r.getId())).append("</blnumber>");
					}
					sb.append("<person_name>").append(r.getPersonName()).append("</person_name>");
					if(r.getRoomId() != null) {
						sb.append("<room_id>").append(r.getRoomId()).append("</room_id>");
					} else {
						sb.append("<room_id>").append("").append("</room_id>");
					}
					sb.append("<roomname>").append(r.getRoomName()).append("</roomname>");
					if(r.getRemoteRoomId() != null) {
						sb.append("<remoteroom_id>").append(r.getRemoteRoomId()).append("</remoteroom_id>");
					} else {
						sb.append("<remoteroom_id>").append("").append("</remoteroom_id>");
					}
					if(r.getRemoteRoomName() != null) {
						sb.append("<remoteroomname>").append(r.getRemoteRoomName()).append("</remoteroomname>");
					} else {
						sb.append("<remoteroomname>").append("").append("</remoteroomname>");
					}
					sb.append("<start_time>").append(r.getStartTime()).append("</start_time>");
					sb.append("<end_time>").append(r.getEndTime()).append("</end_time>");
					sb.append("<content>").append(r.getContent()).append("</content>");
					sb.append("<creatername>").append(r.getCreaterName()).append("</creatername>");
					if(r.getBurnerName() != null) {
						sb.append("<burnername>").append(r.getBurnerName()).append("</burnername>");
					} else {
						sb.append("<burnername>").append("").append("</burnername>");
					}
					if(r.getBurnStartTime() != null) {
						sb.append("<burn_start_time>").append(r.getBurnStartTime()).append("</burn_start_time>");
					} else {
						sb.append("<burn_start_time>").append("").append("</burn_start_time>");
					}
					if(r.getBurnEndTime() != null) {
						sb.append("<burn_end_time>").append(r.getBurnEndTime()).append("</burn_end_time>");
					} else {
						sb.append("<burn_end_time>").append("").append("</burn_end_time>");
					}
					if(r.getVideoerName() != null) {
						sb.append("<videoername>").append(r.getVideoerName()).append("</videoername>");
					} else {
						sb.append("<videoername>").append("").append("</videoername>");
					}
					if(r.getVideoStartTime() != null) {
						sb.append("<video_start_time>").append(r.getVideoStartTime()).append("</video_start_time>");
					} else {
						sb.append("<video_start_time>").append("").append("</video_start_time>");
					}
					if(r.getVideoEndTime() != null) {
						sb.append("<video_end_time>").append(r.getVideoEndTime()).append("</video_end_time>");
					} else {
						sb.append("<video_end_time>").append("").append("</video_end_time>");
					}
					sb.append("<source>").append(r.getSource()).append("</source>");
					sb.append("<record_type>").append(r.getRecordType()).append("</record_type>");
					sb.append("<recorder>").append(r.getRecorder()).append("</recorder>");
					
					int times = SXMS_PulishTask.getInstance().getConfig().getIs_show_caseperson_times();
					if(times == 0) {//0:以案卷审讯次数显示 1:被讯问人次数
						sb.append("<record_times>").append(r.getRecordTimes()).append("</record_times>");
					} else {
						sb.append("<record_times>").append(r.getAskTimes()).append("</record_times>");
					}
					sb.append("<device_id>").append(r.getDeviceId()).append("</device_id>");
					sb.append("<alias_status>").append(r.getAliasStatus()).append("</alias_status>");
					sb.append("<asktimes>").append(r.getAskTimes()).append("</asktimes>");
					if(r.getImgPath() != null) {
						sb.append("<imgpath>").append(r.getImgPath()).append("</imgpath>");
					} else {
						sb.append("<imgpath>").append("").append("</imgpath>");
					}
					
					//64位编码解决
					String path = RECORDPATH + r.getPath() + r.getContent();
					File file = new File(path);
					if(file.exists()) {
						String blIo = FileUtil.parseFile(path);
						sb.append("<blnr>").append(blIo != null && !"".equals(blIo) ? blIo : "").append("</blnr>");
					} else {
						sb.append("<blnr>").append("").append("</blnr>");
					}
					
					sb.append("<blnrdoc>").append(r.getBlnrDoc()).append("</blnrdoc>");
					sb.append("<videozb>");
						sb.append("<dev_id>").append(r.getZbDevId()).append("</dev_id>");
						sb.append("<devicename>").append(r.getZbDeviceName()).append("</devicename>");
						sb.append("<dev_type>").append(r.getZbDevType()).append("</dev_type>");
						sb.append("<remark>").append(r.getZbRemark()).append("</remark>");
					sb.append("</videozb>");
					sb.append("<videodb>");
						sb.append("<dev_id>").append(r.getDbDevId()).append("</dev_id>");
						sb.append("<devicename>").append(r.getDbDeviceName()).append("</devicename>");
						sb.append("<dev_type>").append(r.getDbDevType()).append("</dev_type>");
						sb.append("<remark>").append(r.getDbRemark()).append("</remark>");
					sb.append("</videodb>");
				sb.append("</blxx>");
			}
		}
	}

	@Override
	public String getMethod() {
		return "uploadBLXXInfo";
	}

	@Override
	public String setInfo(Object o) {
		try {
			if (o instanceof SXMX_Record) {
				SXMX_Record r = (SXMX_Record) o;
				this.wrapRecord(r);
				log.info("[Wrap Record Success!]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据笔录实体信息，包装笔录请求信息
	 * @param r 笔录实体类
	 */
	private void wrapRecord(SXMX_Record r) {
		if(r != null){
			this.setId(String.valueOf(r.getId()));
			this.setStatus(r.getStatus());
			this.setCaseId(r.getCaseId());
			this.setCaseName(r.getCaseName());
			this.setBlName(r.getBlName());
			this.setPersonName(r.getPersonName());
			this.setRoomId(r.getRoomId());
			this.setRoomName(r.getRoomName());
			this.setRemoteRoomId(r.getRemoteRoomId());
			this.setRemoteRoomName(r.getRemoteRoomName());
			this.setStartTime(r.getStartTime());
			this.setEndTime(r.getEndTime());
			this.setContent(r.getContent());
			this.setCreaterName(r.getCreaterName());
			this.setBurnerName(r.getBurnerName());
			this.setBurnStartTime(r.getBurnStartTime());
			this.setBurnEndTime(r.getBurnEndTime());
			this.setVideoerName(r.getVideoerName());
			this.setVideoStartTime(r.getVideoStartTime());
			this.setVideoEndTime(r.getVideoEndTime());
			this.setSource(r.getSource());
			this.setRecordType(r.getRecordType());
			this.setRecorder(r.getRecorder());
			this.setRecordTimes(r.getRecordTimes());
			this.setDbDevId(r.getDbDevId());
			this.setAliasStatus(r.getAliasStatus());
			this.setAskTimes(r.getAskTimes());
			this.setImgPath(r.getImgPath());
			this.setBlnr(r.getBlnr());
			this.setBlnrDoc(r.getBlnrDoc());
			this.setDeviceId(r.getDeviceId());
			this.setDbDeviceName(r.getDbDeviceName());
			this.setDbDevType(r.getDbDevType());
			this.setDbRemark(r.getDbRemark());
			this.setZbDevId(r.getZbDevId());
			this.setZbDeviceName(r.getZbDeviceName());
			this.setZbDevType(r.getZbDevType());
			this.setZbRemark(r.getZbRemark());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public static Logger getLog() {
		return log;
	}

	public List<SXMX_Record> getRecords() {
		return records;
	}

	public void setRecords(List<SXMX_Record> records) {
		this.records = records;
	}
}
