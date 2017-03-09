package com.keda.webservice.server.yctx.req;

import java.util.List;

import com.keda.webservice.server.req.WSRequest;
import com.keda.webservice.server.yctx.domian.YCTX_Arraignment;
import com.keda.webservice.server.yctx.domian.YCTX_Room;

public class UploadYCTXArraignmentInfoReq extends WSRequest {
	/** 信息id */
	private String id;
	/** 提讯名称 */
	private String name;
	/** 提讯状态，1：未提交，2:已提交或正在审批，3：被驳回，4：已删除，5：未开始，6:已过期，7:已完成，8：正在进行中，9：已被取消 */
	private String status;
	/** 提讯提交时间 */
	private String commitDateTime;
	/** 本地讯问室 */
	private String localArrRoom;
	/** 远端讯问室 */
	private String remoteArrRoom;
	/** 提讯开始时间 */
	private String startTime;
	/** 提讯结束时间 */
	private String endTime;
	/** 提讯人员 */
	private String txrenyuan;
	/** 笔录内容，当提讯完再传（文件BASE64编码） */
	private String blnrdoc;
	/**
	 * 录像名称
	 */
	private String filename;
	
	private List<YCTX_Arraignment> arraignments;
	
	@Override
	protected void appendParamter(StringBuffer sb) throws Exception {
		sb.append("<id>").append(getMethod()).append("</id>");
		sb.append("<jcynumber>").append(getNo()).append("</jcynumber>");
	}

	@Override
	protected void appendChildParamter(StringBuffer sb) throws Exception {
		if(arraignments != null && arraignments.size() > 0) {
			for(int i=0;i<arraignments.size();i++) {
				YCTX_Arraignment a = arraignments.get(i);
				
			}
		}
	}

	@Override
	public String getMethod() {
		
		return null;
	}

	@Override
	public String setInfo(Object o) {
		return "uploadYCTXInfo";
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

	public String getCommitDateTime() {
		return commitDateTime;
	}

	public void setCommitDateTime(String commitDateTime) {
		this.commitDateTime = commitDateTime;
	}

	public String getLocalArrRoom() {
		return localArrRoom;
	}

	public void setLocalArrRoom(String localArrRoom) {
		this.localArrRoom = localArrRoom;
	}

	public String getRemoteArrRoom() {
		return remoteArrRoom;
	}

	public void setRemoteArrRoom(String remoteArrRoom) {
		this.remoteArrRoom = remoteArrRoom;
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

	public String getTxrenyuan() {
		return txrenyuan;
	}

	public void setTxrenyuan(String txrenyuan) {
		this.txrenyuan = txrenyuan;
	}

	public String getBlnrdoc() {
		return blnrdoc;
	}

	public void setBlnrdoc(String blnrdoc) {
		this.blnrdoc = blnrdoc;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<YCTX_Arraignment> getArraignments() {
		return arraignments;
	}

	public void setArraignments(List<YCTX_Arraignment> arraignments) {
		this.arraignments = arraignments;
	}

}
