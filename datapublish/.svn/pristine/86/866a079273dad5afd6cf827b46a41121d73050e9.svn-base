package com.keda.webservice.server.sxms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.keda.webservice.server.Exception.DBException;
import com.keda.webservice.server.sxms.domian.SXMX_Area;
import com.keda.webservice.server.sxms.domian.SXMX_Case;
import com.keda.webservice.server.sxms.domian.SXMX_Record;
import com.keda.webservice.server.sxms.domian.SXMX_RemoteRoom;
import com.keda.webservice.server.sxms.domian.SXMX_Room;
import com.keda.webservice.server.sxms.domian.SXMX_SYNC;


public class SXMS_PulishDataDaoImpl {

	private static Logger log = Logger.getLogger(SXMS_PulishDataDaoImpl.class);

	/** 数据库连接 */
	private Connection conn;


	/**
	 * 查询所有区域
	 * @param areaId 检察院编号
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public List<SXMX_Area> findAreaByAll() throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select a.id, a.p_id, a.platform_id, a.name, a.description, ");//
		sql.append(" a.l_v, a.r_v, a.node_type, a.create_time, a.area_status, a.number, a.aliases_name, areaNumber ");
		sql.append(" from is_areas a ");
		
		log.debug("待查询的sql语句" + sql.toString() + ";   查询条件是：");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = pstmt.executeQuery();
		
		List<SXMX_Area> list = new ArrayList<SXMX_Area>(0);
		
		while(rs.next()) {
			SXMX_Area area = new SXMX_Area();
			area.setId(rs.getString("id"));
			area.setP_id(rs.getString("p_id"));
			area.setPlatform_id(rs.getString("platform_id"));
			area.setName(rs.getString("name"));
			area.setDescription(rs.getString("description"));
			area.setL_v(rs.getString("l_v"));
			area.setR_v(rs.getString("r_v"));
			area.setName(rs.getString("node_type"));
			area.setCreate_time(rs.getString("create_time"));
			area.setArea_status(rs.getString("area_status"));
			area.setNumber(rs.getString("number"));
			area.setAliases_name(rs.getString("aliases_name"));
			area.setAreaNumber(rs.getString("areaNumber"));
			
			list.add(area);
		}
//		conn.commit();
		return list;
	}
	
	
	/**
	 * 查询所有审讯室信息
	 * @param areaId 检察院编号
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public List<SXMX_Room> findRoomByAll(String areaId) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select r.id, r.number, r.name, r.status, r.type_id, ");//1-5
		sql.append(" (select t.name from is_iroomtypes t where t.id=r.type_id) as type_name, ");//6
		sql.append(" r.device_ip, r.trialapply_id, r.area_id, ");//7-9
		sql.append(" (select a.name from is_areas a where a.id=r.area_id) as area_name, r.description, ");//10-11
		sql.append(" s.type as stype, s.name as sname, s.status as sstatus  ");
		
		sql.append(" from is_irooms r   ");
		
		//是否已经发送过的过滤条件
		sql.append(" left join is_sync_spzx s on r.id=s.name and s.type='ROOM'");

//		sql.append(" and s.status in(0,2) or s.status is null ");
		
		sql.append(" where r.area_id = ? ");

		//是否已经发送过的过滤条件
		sql.append(" and s.status in(0,2) or s.status is null ");
		
		log.debug("待查询的sql语句" + sql.toString().replace("?", areaId) + ";   查询条件是：areaId=" + areaId);
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, areaId);
		ResultSet rs = pstmt.executeQuery();
		
		List<SXMX_Room> list = new ArrayList<SXMX_Room>(0);
		
		while(rs.next()) {
			SXMX_Room room = new SXMX_Room();
			room.setId(rs.getLong("id"));
			room.setRoomNumber(rs.getString("number"));
			room.setName(rs.getString("name"));
			room.setStatus(rs.getString("status"));
			room.setTypeid(rs.getString("type_id"));
			room.setTypename(rs.getString("type_name"));
			room.setDeviceip(rs.getString("device_ip"));
			room.setRecordid(rs.getString("trialapply_id"));
			room.setAreaid(rs.getString("area_id"));
			room.setAreaname(rs.getString("area_name"));
			room.setDescription(rs.getString("description"));
			room.setStype(rs.getString("stype"));
			room.setSname(rs.getString("sname"));
			room.setSstatus(rs.getString("sstatus"));
			
			list.add(room);
		}
		//		conn.commit();
		return list;
	}
	

	
	/**
	 * 查询所有远程审讯室信息
	 * @param areaId 检察院编号
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public List<SXMX_RemoteRoom> findRemoteRoomByAll(String areaId) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		sql.append("  select r.id, r.name, r.remotetrial_id, r.trialapply_id,  ");//1-5
		sql.append("  d.area_id ,r.svr_ip,(select a.name from is_areas a where a.id=d.area_id) as area_name, r.des, ");
		sql.append(" s.type as stype, s.name as sname, s.status as sstatus  ");
		sql.append(" from is_remoteroom r  ");
		sql.append(" left join is_users u on r.creater_id = u.id ");
		sql.append(" left join is_departments d on u.dep_id = d.id ");
		
		//是否已经发送过的过滤条件
		sql.append(" left join is_sync_spzx s on r.id=s.name and s.type='REMOTEROOM' ");

//		sql.append(" and s.status in(0,2) or s.status is null ");
		
		sql.append(" where d.area_id = ? ");
		
		//是否已经发送过的过滤条件
		sql.append(" and s.status in(0, 2) or s.status is null ");
		
		log.debug("待查询的sql语句" + sql.toString().replace("?", areaId) + ";   查询条件是：areaId=" + areaId);
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, areaId);
		ResultSet rs = pstmt.executeQuery();
		
		List<SXMX_RemoteRoom> list = new ArrayList<SXMX_RemoteRoom>(0);
		
		while(rs.next()) {
			SXMX_RemoteRoom room = new SXMX_RemoteRoom();
			room.setId(rs.getLong("id"));
			room.setName(rs.getString("name"));
			room.setRemotetrialId(rs.getString("remotetrial_id"));
			room.setReocrd_id(rs.getString("trialapply_id"));
			if(room.getRemotetrialId() != null && !"".equals(room.getRemotetrialId())) {
				room.setStatus("1");
			} else {
				room.setStatus("0");
			}
			room.setDeviceip(rs.getString("svr_ip"));
			room.setAreaid(rs.getString("area_id"));
			room.setAreaname(rs.getString("area_name"));
			room.setDescription(rs.getString("des"));
			room.setStype(rs.getString("stype"));
			room.setSname(rs.getString("sname"));
			room.setSstatus(rs.getString("sstatus"));
			
			list.add(room);
		}
		//		conn.commit();
		return list;
	}
	
	
	
	
	/**
	 * 查询案卷
	 * @param areaId 检察院编号
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public List<SXMX_Case> findCaseByAll(String areaId) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		//INSERT INTO `inquestdb3`.`is_cases` (`id`, `uuid`, `creater_id`, `leader_id`, `department_id`, `level_id`, `number`, `name`, `address`, `reason`, `description`, `occur_time`, `create_time`, `modify_time`, `area_id`, `src_id`, `record_times`, `case_archive`, `is_delete`, `case_info_manager`, `archive_time`, `trial_time`, `burn_times`, `persons`, `askers`, `burners`) VALUES ('1', NULL, '1', NULL, '1', NULL, '20160119001', '测试测试哦', '测试测试哦', '测试测试哦', '测试测试哦', NULL, '2016-01-19 13:32:14', '2016-03-01 16:12:52', NULL, NULL, '50', '1', '0', NULL, '2016-01-29 14:49:58', '439508', NULL, '测试测试哦11231,测试测试哦1123,测试测试哦,测试测试,测试测试123,测试测试1230,测试测试1231,测试测试12311,测试测试12313,测试测,测试测1,测试测1哎,测试测1哎1,测试测1哎12,测试测1i', '测试测试哦1123', NULL);
		
		sql.append("select c.id, c.uuid, c.creater_id, (select username from is_users u where u.id=c.creater_id) as creater_name, ");//1-4
		sql.append(" (select name from is_caselevels l where c.level_id is not null and l.id=c.level_id) as level_name, ");//5
		sql.append(" c.number, c.name, c.reason, c.description, c.occur_time, c.create_time, ");//6-11
		sql.append(" d.name as department_name, ");//12(select name from is_departments d where d.id=c.department_id) as department_
		sql.append(" c.address, c.area_id, c.record_times, case_archive, c.persons, c.askers,");//13-18
		sql.append(" s.type as stype, s.name as sname, s.status as sstatus  ");
		sql.append(" from is_cases c ");
		sql.append(" left join is_departments d on d.id = c.department_id ");

		//是否已经发送过的过滤条件
		sql.append(" left join is_sync_spzx s on c.id=s.name and s.type='CASE' ");

//		sql.append(" and s.status in(0,2) or s.status is null ");
		
		sql.append(" where d.area_id = ? ");

		//是否已经发送过的过滤条件
		sql.append(" and s.status in(0,2) or s.status is null ");
		
		log.debug("待查询的sql语句" + sql.toString().replace("?", areaId) + ";   查询条件是：areaId=" + areaId);
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, areaId);
		ResultSet rs = pstmt.executeQuery();
		
		List<SXMX_Case> list = new ArrayList<SXMX_Case>(0);
		
		while(rs.next()) {
			SXMX_Case ca = new SXMX_Case();
			ca.setId(rs.getLong("id"));
			ca.setUuid(rs.getString("uuid"));
			ca.setCreaterId(rs.getString("creater_id"));
			ca.setCreaterName(rs.getString("creater_name"));
			ca.setLevelName(rs.getString("level_name"));
			ca.setNumber(rs.getString("number"));
			ca.setName(rs.getString("name"));
			ca.setReason(rs.getString("reason"));
			ca.setDescription(rs.getString("description"));
			ca.setOccurTime(rs.getString("occur_time"));
			ca.setCreateTime(rs.getString("create_time"));
			ca.setDepartment(rs.getString("department_name"));
			ca.setAddress(rs.getString("address"));
			ca.setAreaId(rs.getString("area_id"));
			ca.setRecordTimes(rs.getString("record_times"));
			ca.setCaseArchive(rs.getString("case_archive"));
			ca.setPersons(rs.getString("persons"));
			ca.setAskers(rs.getString("askers"));
			ca.setStype(rs.getString("stype"));
			ca.setSname(rs.getString("sname"));
			ca.setSstatus(rs.getString("sstatus"));
			
			list.add(ca);
		}
		//		conn.commit();
		return list;
	}
	
	/**
	 * 根据案件id查询笔录集合
	 * 
	 * @return
	 */
	public List<SXMX_Record> findRecordByAll(String areaId) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
//		sql.append("select id, asker, number, start_time, end_time, status, area_id, src_id, content, version, ");//1-10
//		sql.append(" record_times, record_type, device_id, burn_start_time, burn_end_time, is_delete, video_start_time, video_end_time, recorder, alias_status, ");//11-20
//		sql.append(" syn_mode, asktimes, ask_address, uuid, ftp_upload, ");//21-25
//		sql.append(" case_id, template_id, person_id, room_id, creater_id, burner_id, videoer_id ");//26-32
//		sql.append(" from is_records r where case_id=?");
		
		sql.append("select r.id, r.status, r.case_id, c.name as case_name, ");//1-4(select c.name from is_cases c where c.id=r.case_id) as case_name,
		sql.append(" r.content as blname, (select p.name from is_casepersons p where p.id=r.person_id) as person_name, ");//5-6
		sql.append(" r.room_id, (select m.name from is_irooms m where m.id=r.room_id) as room_name, ");//7-8
		sql.append(" r.remoteroom_id, (select m.name from is_remoteroom m where m.id=r.remoteroom_id) as remote_room_name, ");//9-10
		sql.append(" r.start_time, r.end_time, r.content, (select u.username from is_users u where u.id=r.creater_id) as creater_name, ");//11-14
		sql.append(" (select u.username from is_users u where u.id=r.burner_id) as burner_name, r.burn_start_time, r.burn_end_time, ");//15-17
		sql.append(" (select u.username from is_users u where u.id=r.videoer_id) as videoer_name, r.video_start_time, r.video_end_time, ");//18-20
		sql.append(" r.source, r.record_type, r.recorder, r.record_times, r.device_id, r.alias_status, r.asktimes, r.imgpath, ");//21-28
		//TODO: 笔录内容、视频内容、视频直播、视频点播待确定
		sql.append(" r.syn_mode, r.asker, r.ask_address, r.uuid, r.ftp_upload, ");//29-33
		sql.append(" r.template_id, r.person_id, r.creater_id, r.burner_id, r.videoer_id, ");//34-38
		sql.append(" a.aliases_name, a.id as areaId, ");
		sql.append(" s.type as stype, s.name as sname, s.status as sstatus  ");
		
		sql.append(" from is_records r ");
		
		sql.append(" left join is_cases c on r.case_id = c.id ");
		sql.append(" left join is_departments d on c.department_id = d.id ");
		sql.append(" left join is_areas a on d.area_id = a.id ");

		//是否已经发送过的过滤条件
		sql.append(" left join is_sync_spzx s on r.id=s.name and s.type='RECORD' ");

//		sql.append(" and s.status in(0,2) or s.status is null ");
		
		sql.append(" where d.area_id = ? ");

		//是否已经发送过的过滤条件
		sql.append(" and s.status in(0,2) or s.status is null ");
		
		
		log.debug("待查询的sql语句 " + sql.toString().replace("?", areaId) + ";    查询条件是：areaId=" + areaId);
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, areaId);
		ResultSet rs = pstmt.executeQuery();
		
		List<SXMX_Record> list = new ArrayList<SXMX_Record>(0);
	
		while(rs.next()) {
			SXMX_Record record = new SXMX_Record();
			
//			Case ca = new Case();
//			ca.setId(caseId);
//			record.setCases(ca);
			
			record.setId(Long.valueOf(rs.getInt("id")));
			record.setStatus(rs.getString("status"));
			record.setCaseId(rs.getString("case_id"));
			record.setCaseName(rs.getString("case_name"));
			record.setBlName(rs.getString("blname"));
			record.setPersonName(rs.getString("person_name"));
			record.setRoomId(rs.getString("room_id"));
			record.setRoomName(rs.getString("room_name"));
			record.setRemoteRoomId(rs.getString("remoteroom_id"));
			record.setRemoteRoomName(rs.getString("remote_room_name"));
			record.setStartTime(rs.getString("start_time"));
			record.setEndTime(rs.getString("end_time"));
			record.setContent(rs.getString("content"));
			record.setCreaterName(rs.getString("creater_name"));
			record.setBurnerName(rs.getString("burner_name"));
			record.setBurnStartTime(rs.getString("burn_start_time"));
			record.setBurnEndTime(rs.getString("burn_end_time"));
			record.setVideoerName(rs.getString("videoer_name"));
			record.setVideoStartTime(rs.getString("video_start_time"));
			record.setVideoEndTime(rs.getString("video_end_time"));
			record.setSource(rs.getString("source"));
			record.setRecordType(rs.getString("record_type"));
			record.setRecorder(rs.getString("recorder"));
			record.setRecordTimes(rs.getString("record_times"));
			record.setDeviceId(rs.getString("device_id"));
			record.setAliasStatus(rs.getString("alias_status"));
			record.setAskTimes(rs.getString("asktimes"));
			record.setImgPath(rs.getString("imgpath"));
			record.setAliasesName(rs.getString("aliases_name"));
			record.setAreaId(rs.getString("areaId"));
			record.setStype(rs.getString("stype"));
			record.setSname(rs.getString("sname"));
			record.setSstatus(rs.getString("sstatus"));
//			record.setAsker(rs.getString("asker"));
//			record.setNumber(rs.getString("number"));
//			record.setStartTime(rs.getTimestamp("start_time"));
//			record.setEndTime(rs.getTimestamp("end_time"));
//			record.setStatus(rs.getInt("status"));
//			record.setAreaId(rs.getString("area_id"));
//			record.setSrcId(rs.getString("src_id"));
//			record.setContent(rs.getString("content"));
//			record.setVersion(rs.getLong("version"));
//
//			record.setRecordTimes(rs.getInt("record_times"));
//			record.setRecordType(rs.getInt("record_type"));
//			record.setDeviceId(rs.getString("device_id"));
//			record.setBurnStartTime(rs.getTimestamp("burn_start_time"));
//			record.setBurnEndTime(rs.getTimestamp("burn_end_time"));
//			record.setIsDelete(rs.getInt("is_delete"));
//			record.setVideoStartTime(rs.getTimestamp("video_start_time"));
//			record.setVideoEndTime(rs.getTimestamp("video_end_time"));
//			record.setRecorder(rs.getString("recorder"));
//			record.setAliasStatus(rs.getInt("alias_status"));
//			// syn_mode, asktimes, ask_address, uuid, ftp_upload, 
//			record.setSynMode(rs.getInt("syn_mode"));
//			record.setAsktimes(rs.getInt("asktimes"));
//			record.setAskAddress(rs.getString("ask_address"));
//			record.setUuid(rs.getString("uuid"));
//			record.setFtpUpload(rs.getBoolean("ftp_upload"));
			// case_id, template_id, person_id, room_id, creater_id, burner_id, videoer_id
			
			//TODO: 以下内容暂时注释，待确认后优化
//			Long templateId = rs.getLong("template_id");
//			Long personId = rs.getLong("person_id");
//			
//			{
//				Long roomId = rs.getLong("room_id");
//				Room r = new Room();
//				r.setId(roomId);
//				{// select * from is_devices d left join is_iroomdev r on d.id=r.dev_id where r.room_id = 1;
//					String roomSql = "select d.id as id, d.name as name, d.dev_type as dev_type, d.dev_videoid as dev_videoid, d.dev_id as dev_id, d.remark as remark, d.area_id as area_id, d.src_id as src_id from is_devices d left join is_iroomdev r on d.id=r.dev_id where r.room_id = ?";
//					PreparedStatement rpstmt = conn.prepareStatement(roomSql, Statement.RETURN_GENERATED_KEYS);
//					rpstmt.setLong(1, roomId);
//					ResultSet ars = rpstmt.executeQuery();
//					
//					Set<Device> devices = new HashSet<Device>(0);
//					while(ars.next()) {
//						Device device = new Device();
//						device.setId(ars.getInt("id"));
//						device.setName(ars.getString("name"));
//						device.setDeviceType(ars.getInt("dev_type"));
//						device.setVideoId(ars.getInt("dev_videoid"));
//						device.setDeviceId(ars.getString("dev_id"));
//						device.setRemark(ars.getString("remark"));
//						device.setAreaid(ars.getString("area_id"));
//						device.setSrcid(ars.getInt("src_id"));
//						
//						devices.add(device);
//					}
//					r.setDevices(devices);
//				}
////				record.setRoom(r);
//			}
//			
//			Long createrId = rs.getLong("creater_id");
//			Long burnerId = rs.getLong("burner_id");
//			Long videoerId = rs.getLong("videoer_id");
			
			list.add(record);
		}
		//		conn.commit();
		return list;
	}


	/**
	 * 查询同步状态表 is_sync_spzx
	 * @param areaId 检察院编号
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public SXMX_SYNC findSyncByName(String type, String name) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select s.name, s.status from is_sync_spzx s where s.type = ? and s.name = ? ");//
		
		log.debug("待查询的sql语句" + sql.toString() + ";   查询条件是：" + name);
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, type);
		pstmt.setString(2, name);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<SXMX_SYNC> list = new ArrayList<SXMX_SYNC>(0);
		
		while(rs.next()) {
			SXMX_SYNC s = new SXMX_SYNC();
			s.setName(rs.getString("name"));
			s.setStatus(rs.getString("status"));
			
			list.add(s);
		}
		//		conn.commit();
		
		SXMX_SYNC sync = null;
		if(list != null && list.size() > 0) {
			sync = list.get(0);
		}
		return sync;
	}
	
	/**
	 * 根据同步类型保存同步状态
	 */
	public void saveSyncStatus(String type, String name, String status) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" insert into is_sync_spzx (type, name, status) values(?, ?, ?) ");//
		
		log.debug("待查询的sql语句" + sql.toString() + ";   查询条件是：name=" + name + " ; status=" + status + ";  type=" + type);
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, type);
		pstmt.setString(2, name);
		pstmt.setString(3, status);
		
		pstmt.executeUpdate();

		//		conn.commit();
		
	}
	
	/**
	 * 根据同步类型修改同步状态
	 * @param name
	 * @param status
	 * @throws DBException
	 * @throws SQLException
	 */
	public void updateSyncStatus(String type, String name, String status) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" update is_sync_spzx set status = ? where name = ? and type = ? ");//
		
		log.debug("待查询的sql语句" + sql.toString() + ";   查询条件是：name=" + name + " ; status=" + status + ";   type=" + type);
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, status);
		pstmt.setString(2, name);
		pstmt.setString(3, type);
		
		pstmt.executeUpdate();

//		conn.commit();
		
	}
	
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
