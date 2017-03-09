package com.keda.webservice.server.sxmx.req;


import java.text.MessageFormat;
import java.util.List;

import org.apache.log4j.Logger;

import com.keda.webservice.server.req.WSRequest;
import com.keda.webservice.server.sxms.domian.SXMX_Case;

public class UploadAJXXInfoReq extends WSRequest {
	private static final Logger log = Logger.getLogger(UploadAJXXInfoReq.class);
	
	private List<SXMX_Case> cases;
	/** 案卷id */
	private String id;
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
	
	@Override
	protected void appendParamter(StringBuffer sb) throws Exception {
		sb.append("<id>").append(getMethod()).append("</id>");
		sb.append("<jcynumber>").append(getNo()).append("</jcynumber>");
	}

	@Override
	protected void appendChildParamter(StringBuffer sb) throws Exception {
		if(cases != null && cases.size() > 0) {
			for(int i=0;i<cases.size();i++) {
				SXMX_Case c = cases.get(i);
				if(c.getSstatus() != null && "1".equals(c.getSstatus())) {
					continue;
				}
				sb.append("<ajxx>");
					sb.append("<id>").append(c.getId()).append("</id>");
					if(c.getUuid() != null) {
						sb.append("<uuid>").append(c.getUuid()).append("</uuid>");
					} else {
						sb.append("<uuid>").append("").append("</uuid>");
					}
					sb.append("<creater_id>").append(c.getCreaterId()).append("</creater_id>");
					sb.append("<creatername>").append(c.getCreaterName()).append("</creatername>");
					if(c.getLevelId() != null) {
						sb.append("<level_id>").append(c.getLevelId()).append("</level_id>");
					} else {
						sb.append("<level_id>").append("").append("</level_id>");
					}
					if(c.getLevelName() != null) {
						sb.append("<levelname>").append(c.getLevelName()).append("</levelname>");
					} else {
						sb.append("<levelname>").append("").append("</levelname>");
					}
					if(c.getNumber() != null) {
						sb.append("<casenumber>").append(c.getNumber()).append("</casenumber>");
					} else {//暂定区域编号+案件id
						sb.append("<casenumber>")
						.append(MessageFormat.format("{0}{1}{2}", c.getAreaId() , "-", c.getId()))
						.append("</casenumber>");
					}
					sb.append("<name>").append(c.getName()).append("</name>");
					sb.append("<reason>").append(c.getReason()).append("</reason>");
					sb.append("<description>").append(c.getDescription()).append("</description>");
					if(c.getOccurTime() != null) {
						sb.append("<occurtime>").append(c.getOccurTime()).append("</occurtime>");
					} else {
						sb.append("<occurtime>").append("").append("</occurtime>");
					}
					sb.append("<createtime>").append(c.getCreateTime()).append("</createtime>");
					sb.append("<department>").append(c.getDepartment()).append("</department>");
					if(c.getAddress() != null) {
						sb.append("<address>").append(c.getAddress()).append("</address>");
					} else {
						sb.append("<address>").append("").append("</address>");
					}
					if(c.getAreaId() != null) {
						sb.append("<area_id>").append(c.getAreaId()).append("</area_id>");
					} else {
						sb.append("<area_id>").append("").append("</area_id>");
					}
					sb.append("<record_times>").append(c.getRecordTimes()).append("</record_times>");
					sb.append("<case_archive>").append(c.getCaseArchive()).append("</case_archive>");
					if(c.getPersons() != null) {
						sb.append("<persons>").append(c.getPersons()).append("</persons>");
					} else {
						sb.append("<persons>").append("").append("</persons>");
					}
					if(c.getAskers() != null) {
						sb.append("<askers>").append(c.getAskers()).append("</askers>");
					} else {
						sb.append("<askers>").append("").append("</askers>");
					}
				sb.append("</ajxx>");
				
				
			}
		}
		
		/*sb.append("<ajxx>");
		sb.append("<id>").append(getId()).append("</id>");
		sb.append("<uuid>").append(getUuid()).append("</uuid>");
		sb.append("<creater_id>").append(getCreaterId()).append("</creater_id>");
		sb.append("<creatername>").append(getCreaterName()).append("</creatername>");
		sb.append("<level_id>").append(getLevelId()).append("</level_id>");
		sb.append("<levelname>").append(getLevelName()).append("</levelname>");
		sb.append("<number>").append(getNumber()).append("</number>");
		sb.append("<name>").append(getName()).append("</name>");
		sb.append("<reason>").append(getReason()).append("</reason>");
		sb.append("<description>").append(getDescription()).append("</description>");
		sb.append("<occurtime>").append(getOccurTime()).append("</occurtime>");
		sb.append("<createtime>").append(getCreateTime()).append("</createtime>");
		sb.append("<department>").append(getDepartment()).append("</department>");
		sb.append("<address>").append(getAddress()).append("</address>");
		sb.append("<area_id>").append(getAreaId()).append("</area_id>");
		sb.append("<record_times>").append(getRecordTimes()).append("</record_times>");
		sb.append("<case_archive>").append(getCaseArchive()).append("</case_archive>");
		sb.append("<persons>").append(getPersons()).append("</persons>");
		sb.append("<askers>").append(getAskers()).append("</askers>");
		sb.append("</ajxx>");*/
	}

	@Override
	public String getMethod() {
		return "uploadAJXXInfo";
	}
	
	@Override
	public String setInfo(Object o) {
		try {
			if (o instanceof SXMX_Case) {
				SXMX_Case c = (SXMX_Case) o;
				this.wrapCase(c);
				log.info("[Wrap Case Success!]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据案件实体信息，包装案件请求信息
	 * @param c 案件实体信息
	 */
	private void wrapCase(SXMX_Case c) {
		if(c != null){
			this.setId(String.valueOf(c.getId()));
			this.setUuid(c.getUuid());
			this.setCreaterId(c.getCreaterId());
			this.setCreaterName(c.getCreaterName());
			this.setLevelId(c.getLevelId());
			this.setLevelName(c.getLevelName());
			this.setNumber(c.getNumber());
			this.setName(c.getName());
			this.setReason(c.getReason());
			this.setDescription(c.getDescription());
			this.setOccurTime(c.getOccurTime());
			this.setCreateTime(c.getCreateTime());
			this.setDepartment(c.getDepartment());
			this.setAddress(c.getAddress());
			this.setAreaId(c.getAreaId());
			this.setRecordTimes(c.getRecordTimes());
			this.setCaseArchive(c.getCaseArchive());
			this.setPersons(c.getPersons());
			this.setAskers(c.getAskers());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<SXMX_Case> getCases() {
		return cases;
	}

	public void setCases(List<SXMX_Case> cases) {
		this.cases = cases;
	}

}
