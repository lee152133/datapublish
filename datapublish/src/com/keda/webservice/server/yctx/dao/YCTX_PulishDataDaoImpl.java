package com.keda.webservice.server.yctx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.keda.webservice.server.Exception.DBException;
import com.keda.webservice.server.yctx.domian.YCTX_Arraignment;
import com.keda.webservice.server.yctx.domian.YCTX_Department;
import com.keda.webservice.server.yctx.domian.YCTX_Room;


public class YCTX_PulishDataDaoImpl {

	private static Logger log = Logger.getLogger(YCTX_PulishDataDaoImpl.class);

	/** 数据库连接 */
	private Connection conn;

	/**
	 * 查询所有部门
	 * @param areaId 检察院编号
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public List<YCTX_Department> findDeptByAll() throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		sql.append("select d.yctx_id, d.yctx_departmentCode, d.yctx_departmentName,  ");
		sql.append("   d.yctx_parentDepartmentId, d.yctx_grade, d.yctx_departmentAddr,  ");
		sql.append("   d.yctx_departmentPhone, d.yctx_departmentstatus  ");
		sql.append("   from yctx_department d ");
		
		log.debug("待查询的sql语句" + sql.toString() + ";   查询条件是：");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		ResultSet rs = pstmt.executeQuery();
		
		List<YCTX_Department> list = new ArrayList<YCTX_Department>(0);
		
		while(rs.next()) {
			YCTX_Department dept = new YCTX_Department();
			dept.setYctx_id(rs.getString("id"));
			dept.setYctx_departmentCode(rs.getString("yctx_departmentCode"));
			dept.setYctx_departmentName(rs.getString("yctx_departmentName"));
			dept.setYctx_parentDepartmentId(rs.getString("yctx_parentDepartmentId"));
			dept.setYctx_grade(rs.getString("yctx_grade"));
			dept.setYctx_departmentAddr(rs.getString("yctx_departmentAddr"));
			dept.setYctx_departmentPhone(rs.getString("yctx_departmentPhone"));
			dept.setYctx_departmentstatus(rs.getString("yctx_departmentstatus"));
			
			list.add(dept);
		}
		conn.commit();
		return list;
	}
	
	/**
	 * 查询所有提讯室信息
	 * @param deptId 检察院编号
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public List<YCTX_Room> findRoomByAll(String deptId) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select r.yctx_id, r.yctx_name, r.yctx_departmentId, ");//1-3
		sql.append(" r.yctx_enable, r.yctx_deviceType, r.yctx_vcrAddr, r.yctx_ipAddr, ");//4-7
		sql.append(" r.yctx_mcurecname, r.yctx_rmstatus, r.yctx_mtType  ");//8-10
		sql.append("    from yctx_arraignmentroom r ");
		sql.append("where r.yctx_departmentId = ? ");
		
		log.debug("待查询的sql语句" + sql.toString() + ";   查询条件是：");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, deptId);
		ResultSet rs = pstmt.executeQuery();
		
		List<YCTX_Room> list = new ArrayList<YCTX_Room>(0);
		
		while(rs.next()) {
			YCTX_Room room = new YCTX_Room();
			room.setYctx_id(rs.getString("id"));
			room.setYctx_name(rs.getString("yctx_name"));
			room.setYctx_departmentId(rs.getString("yctx_departmentId"));
			room.setYctx_enable(rs.getString("yctx_enable"));
			room.setYctx_deviceType(rs.getString("yctx_deviceType"));
			room.setYctx_vcrAddr(rs.getString("yctx_vcrAddr"));
			room.setYctx_ipAddr(rs.getString("yctx_ipAddr"));
			room.setYctx_mcurecname(rs.getString("yctx_mcurecname"));
			room.setYctx_rmstatus(rs.getString("yctx_rmstatus"));
			room.setYctx_mtType(rs.getString("yctx_mtType"));
			
			list.add(room);
		}
		conn.commit();
		return list;
	}
	
	/**
	 * 查询提讯信息
	 * @param areaId 检察院编号
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public List<YCTX_Arraignment> findCaseByAll(String deptId) throws DBException, SQLException {
		Connection conn = getConn();
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.yctx_id, a.yctx_arrProinfor , a.yctx_interedPerson,  ");//1-3
		sql.append(" a.yctx_interUserOne, a.yctx_interUserTwo, a.yctx_lawCase,");//4-6
		sql.append(" a.yctx_commitDateTime, a.yctx_departmentId, a.yctx_status, ");//7-9
		sql.append(" a.yctx_vediofilepath, a.yctx_broadcastfilepath,a.yctx_meetingtype, ");//10-12
		sql.append(" a.yctx_interUserThree, a.yctx_interUserFour, a.yctx_parentId ");//13-15
		
		sql.append("    from yctx_arraignment a ");
		sql.append("where a.yctx_departmentId = ? ");
		
		
		log.debug("待查询的sql语句" + sql.toString() + ";   查询条件是：");
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, deptId);
		ResultSet rs = pstmt.executeQuery();
		
		List<YCTX_Arraignment> list = new ArrayList<YCTX_Arraignment>(0);
		
		while(rs.next()) {
			YCTX_Arraignment arr = new YCTX_Arraignment();
			arr.setYctx_id(rs.getString("yctx_id"));
			
			list.add(arr);
		}
		conn.commit();
		return list;
	}
	

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
