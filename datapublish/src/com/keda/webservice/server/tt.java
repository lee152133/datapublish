package com.keda.webservice.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.keda.webservice.server.Exception.DBException;
import com.keda.webservice.server.sxms.dao.SXMS_Mysql;
import com.keda.webservice.server.sxms.dao.SXMS_PulishDataDaoImpl;
import com.keda.webservice.server.sxms.domian.SXMX_Area;
import com.keda.webservice.server.sxms.domian.SXMX_Case;
import com.keda.webservice.server.sxms.domian.SXMX_Record;
import com.keda.webservice.server.sxms.domian.SXMX_Room;
import com.keda.webservice.server.sxmx.req.UploadAJXXInfoReq;
import com.keda.webservice.server.sxmx.req.UploadBLXXInfoReq;
import com.keda.webservice.server.sxmx.req.UploadSXSInfoReq;
/**
 * tt.java要拿到web工程中运行，在此工程运行缺少jar包。或者引用需要jar包也可已运行
 * @author root
 *
 */
public class tt {
	public static void main(String[] args) {
		try {
			
			Connection conn = SXMS_Mysql.getInstance().getConnection();
			SXMS_PulishDataDaoImpl pulishDao = new SXMS_PulishDataDaoImpl();
			conn.setAutoCommit(false);
			pulishDao.setConn(conn);
			
			List<SXMX_Area> areas = findArea(pulishDao);
			for(SXMX_Area area : areas) {
				
				areaId = area.getId();
				 
				findRoom(pulishDao);
				
				findCase(pulishDao);
				
				findRecord(pulishDao);
			}
			
			
			
//			String result = SXMSWSClient.getInstance().doCommand(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String areaId = "";

	public static List<SXMX_Area> findArea(SXMS_PulishDataDaoImpl pulishDao) throws DBException, SQLException {
		List<SXMX_Area> areas = pulishDao.findAreaByAll();
		
		System.out.println("areas===>>> " + areas.size());
		return areas;
	}
	
	public static void findCase(SXMS_PulishDataDaoImpl pulishDao) throws DBException, SQLException {
		List<SXMX_Case> cases = pulishDao.findCaseByAll(areaId);
		UploadAJXXInfoReq req = new UploadAJXXInfoReq();
		req.setCases(cases);
		
		System.out.println("areaId=>> " + areaId + "cases===>>> " + cases.size());
	}

	public static void findRoom(SXMS_PulishDataDaoImpl pulishDao) throws DBException, SQLException {
		List<SXMX_Room> rooms = pulishDao.findRoomByAll(areaId);
		UploadSXSInfoReq req = new UploadSXSInfoReq();
		req.setRooms(rooms);
		
		System.out.println("areaId=>> " + areaId + "rooms===>>> " + rooms.size());
	}
	public static void findRecord(SXMS_PulishDataDaoImpl pulishDao) throws DBException, SQLException {
		List<SXMX_Record> records = pulishDao.findRecordByAll(areaId);
		UploadBLXXInfoReq req = new UploadBLXXInfoReq();
		req.setRecords(records);
		
		System.out.println("areaId=>> " + areaId + "records===>>> " + records.size());
	}
	
}
