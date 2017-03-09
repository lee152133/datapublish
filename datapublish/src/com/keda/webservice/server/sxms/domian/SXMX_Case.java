package com.keda.webservice.server.sxms.domian;

/**
 * @Company: 苏州科达
 * @Description: 案卷信息
 * @author： cuiqingqiang
 * @date：2016年8月23日 下午3:21:52
 */
public class SXMX_Case {

	/** 案卷id */
	private Long id;
	/** 案卷UUID */
	private String uuid;
	/** 创建人ID */
	private String createrId;
	/** 创建人名称 */
	private String createrName;
	/** 案卷级别 */
	private String levelId;
	/** 案卷级别名称 */
	private String levelName;
	/** 案卷编号 */
	private String number;
	/** 案件名称 */
	private String name;
	/** 案由 */
	private String reason;
	/** 案件描述 */
	private String description;
	/** 案发时间yyyy-MM-dd hh:mm:ss */
	private String occurTime;
	/** 创建时间yyyy-MM-dd hh:mm:ss */
	private String createTime;
	/** 审讯部门 */
	private String department;
	/** 案发地点 */
	private String address;
	/** 区域Id */
	private String areaId;
	/** 笔录次数 */
	private String recordTimes;
	/** 案卷归档 */
	private String caseArchive;
	/** 全部涉案人员 */
	private String persons;
	/** 全部审讯人员 */
	private String askers;
	private String stype;
	private String sname;
	private String sstatus;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getCreaterId() {
		return createrId;
	}
	
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
	
	public String getCreaterName() {
		return createrName;
	}
	
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	
	public String getLevelId() {
		return levelId;
	}
	
	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
	
	public String getLevelName() {
		return levelName;
	}
	
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getOccurTime() {
		return occurTime;
	}
	
	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAreaId() {
		return areaId;
	}
	
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
	public String getRecordTimes() {
		return recordTimes;
	}
	
	public void setRecordTimes(String recordTimes) {
		this.recordTimes = recordTimes;
	}
	
	public String getCaseArchive() {
		return caseArchive;
	}
	
	public void setCaseArchive(String caseArchive) {
		this.caseArchive = caseArchive;
	}
	
	public String getPersons() {
		return persons;
	}
	
	public void setPersons(String persons) {
		this.persons = persons;
	}
	
	public String getAskers() {
		return askers;
	}
	
	public void setAskers(String askers) {
		this.askers = askers;
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
}
