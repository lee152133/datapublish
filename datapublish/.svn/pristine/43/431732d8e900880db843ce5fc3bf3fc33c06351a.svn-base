package com.keda.webservice.sxmx.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.keda.webservice.server.Exception.DBException;
import com.keda.webservice.server.Exception.WSException;
import com.keda.webservice.server.res.UploadAJXXInfoRes;
import com.keda.webservice.server.res.UploadBLXXInfoRes;
import com.keda.webservice.server.res.UploadRemoteSXSInfoRes;
import com.keda.webservice.server.res.UploadSXSInfoRes;
import com.keda.webservice.server.sxms.dao.SXMS_Mysql;
import com.keda.webservice.server.sxms.dao.SXMS_PulishDataDaoImpl;
import com.keda.webservice.server.sxms.domian.SXMX_Area;
import com.keda.webservice.server.sxms.domian.SXMX_Case;
import com.keda.webservice.server.sxms.domian.SXMX_Record;
import com.keda.webservice.server.sxms.domian.SXMX_RemoteRoom;
import com.keda.webservice.server.sxms.domian.SXMX_Room;
import com.keda.webservice.server.sxmx.req.UploadAJXXInfoReq;
import com.keda.webservice.server.sxmx.req.UploadBLXXInfoReq;
import com.keda.webservice.server.sxmx.req.UploadRemoteSXSInfoReq;
import com.keda.webservice.server.sxmx.req.UploadSXSInfoReq;
import com.keda.webservice.server.utils.AbstarctTask;
import com.keda.webservice.server.webservice.v1.IWSClient;

public class SXMS_PulishTask extends AbstarctTask {

	/** 实例对象 **/
	private static SXMS_PulishTask instance;

	private SXMS_PulishTask() {

	}

	/**
	 * 返回服务工厂实例
	 * 
	 * @return
	 */
	public synchronized static SXMS_PulishTask getInstance() {
		if (instance == null) {
			instance = new SXMS_PulishTask();
		}

		return instance;
	}

	private static Logger log = Logger.getLogger(SXMS_PulishTask.class);

	public static int TIME = 11;

	public static String areaId;
	/**
	 * 0执行全部 1执行本地审讯室 2执行远程审讯室 3执行案件 4执行笔录
	 */
	public static int executeReq = 0;

	public static int ALL = 0;
	public static int ROOM = 1;
	public static int REMOTEROOM = 2;
	public static int CASE = 3;
	public static int RECORD = 4;

	public Object locked = new Object();
	/**
	 * 是否启动线程
	 */
	public boolean worked;
	
	/**
	 * 同步审讯室/案件/笔录间隔
	 */
	public static int SYNCWAITTIME = 1000 * 6;// * 5;
	/**
	 * 主任务等待时间
	 */
	public static int WAITTIME = 1000 * 60 * 30;

	/**
	 * 外部系统自定义配置类
	 */
	public SXMSConfig config;

	@Override
	public void run() {
		try {
			doWork();
		} catch (Exception e) {
			log.error("pulishTask run...", e);
		}
	}

	public void doWork() {
		try {
			Calendar now = Calendar.getInstance();
			boolean todayEnd = isExecuteEnable(now);

			if (todayEnd) {

				sync();

				log.debug("【SXMS-DATA_PULISH】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>推送审讯信息[finished]...");

			} else {
				log.debug("未到推送数据时间...");
			}

		} catch (DBException e) {
			log.error("pulishTask doWork DBException...", e);
		} catch (SQLException e) {
			log.error("pulishTask doWork SQLException...", e);
		} catch (WSException e) {
			log.error("pulishTask doWork WSException...", e);
		} catch (InterruptedException e) {
			log.error("pulishTask doWork InterruptedException...", e);
		} finally {
		}
	}

	/**
	 * 在指定时间是否可以执行推送数据
	 * 
	 * @param date
	 * @return
	 */
	private boolean isExecuteEnable(Calendar date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTimeInMillis());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		return true;// hour == TIME;

	}

	/**
	 * 推送数据任务
	 * 
	 * @throws InterruptedException
	 * @throws Exception
	 */
	private synchronized void sync() throws DBException, SQLException,
			WSException, InterruptedException {

		Connection conn = SXMS_Mysql.getInstance().getConnection();
		SXMS_PulishDataDaoImpl pulishDao = new SXMS_PulishDataDaoImpl();
		conn.setAutoCommit(false);
		pulishDao.setConn(conn);
		try {
			synchronized (locked) {
				List<SXMX_Area> areas = findArea(pulishDao);
				for (SXMX_Area area : areas) {

					switch (executeReq) {
					case 1:// 1执行本地审讯室
						findRoom(pulishDao, area);
						// locked.wait(SYNCWAITTIME);
						break;
					case 2:// 2执行远程审讯室
						findRemoteRoom(pulishDao, area);
						// locked.wait(SYNCWAITTIME);
						break;
					case 3:// 3执行案件
						findCase(pulishDao, area);
						// locked.wait(SYNCWAITTIME);
						break;
					case 4:// 4执行笔录
						findRecord(pulishDao, area);
						// locked.wait(SYNCWAITTIME);
						break;
					default:// 0执行全部
						findRoom(pulishDao, area);
						// locked.wait(SYNCWAITTIME);
						findRemoteRoom(pulishDao, area);
						// locked.wait(SYNCWAITTIME);
						findCase(pulishDao, area);
						// locked.wait(SYNCWAITTIME);
						findRecord(pulishDao, area);
						// locked.wait(SYNCWAITTIME);

						break;
					}

				}
				executeReq = 0;
			}
		} catch (Exception e) {
			conn.rollback();
			log.error("sync error...", e);
		} finally {
			conn.commit();
			conn.close();
		}
	}

	public static List<SXMX_Area> findArea(SXMS_PulishDataDaoImpl pulishDao)
			throws DBException, SQLException {
		List<SXMX_Area> areas = pulishDao.findAreaByAll();

		log.debug("areas===>>> " + areas.size());
		return areas;
	}

	public static void findRoom(SXMS_PulishDataDaoImpl pulishDao, SXMX_Area area)
			throws Exception {
		List<SXMX_Room> rooms = pulishDao.findRoomByAll(area.getId());
		List<SXMX_Room> sendRooms = new ArrayList<SXMX_Room>(0);
		if (rooms != null && rooms.size() > 0) {
			for(SXMX_Room room : rooms) {
				if(room.getSstatus() == null) {
					sendRooms.add(room);
				}
				if(!"1".equals(room.getSstatus())) {
					sendRooms.add(room);
				}
			}
			if(sendRooms.size() > 0) {
				UploadSXSInfoReq req = new UploadSXSInfoReq();
				log.debug("sendRooms areaNumber=>> " + area.getAreaNumber());
				req.setNo(area.getAreaNumber());
				req.setRooms(sendRooms);
	
				log.debug("areaId=>> " + area.getId() + "sendRooms===>>> " + sendRooms.size());
				String result = IWSClient.getInstance().doCommand(req);
				UploadSXSInfoRes res = new UploadSXSInfoRes();
				res.parseXml(result);
				res.saveOrUpdateRooms(pulishDao, sendRooms, res.getCode());
			}
		}

	}

	/**
	 * 发送远程审讯室
	 * 
	 * @param pulishDao
	 * @param area
	 * @throws Exception
	 */
	public static void findRemoteRoom(SXMS_PulishDataDaoImpl pulishDao,
			SXMX_Area area) throws Exception {
		List<SXMX_RemoteRoom> rooms = pulishDao.findRemoteRoomByAll(area.getId());
		List<SXMX_RemoteRoom> sendRooms = new ArrayList<SXMX_RemoteRoom>(0);
		if (rooms != null && rooms.size() > 0) {
			for(SXMX_RemoteRoom room : rooms) {
				if(room.getSstatus() == null) {
					sendRooms.add(room);
				}
				if(!"1".equals(room.getSstatus())) {
					sendRooms.add(room);
				}
			}
			if(sendRooms.size() > 0) {
				UploadRemoteSXSInfoReq req = new UploadRemoteSXSInfoReq();
				log.debug("sendRooms areaNumber=>> " + area.getAreaNumber());
				req.setNo(area.getAreaNumber());
				req.setRooms(sendRooms);
	
				log.debug("areaId=>> " + area.getId() + "sendRooms===>>> " + sendRooms.size());
				String result = IWSClient.getInstance().doCommand(req);
				UploadRemoteSXSInfoRes res = new UploadRemoteSXSInfoRes();
				res.parseXml(result);
				res.saveOrUpdateRemoteRooms(pulishDao, sendRooms, res.getCode());
			}
		}

	}

	public static void findCase(SXMS_PulishDataDaoImpl pulishDao, SXMX_Area area)
			throws Exception {
		List<SXMX_Case> cases = pulishDao.findCaseByAll(area.getId());
		List<SXMX_Case> sendCases = new ArrayList<SXMX_Case>(0);
		if (cases != null && cases.size() > 0) {
			for(SXMX_Case ca : cases) {
				if(ca.getSstatus() == null) {
					sendCases.add(ca);
				}
				if(!"1".equals(ca.getSstatus())) {
					sendCases.add(ca);
				}
			}
			if(sendCases.size() > 0) {
				UploadAJXXInfoReq req = new UploadAJXXInfoReq();
				log.debug("sendCases areaNumber=>> " + area.getAreaNumber());
				req.setNo(area.getAreaNumber());
				req.setCases(sendCases);
	
				log.debug("areaId=>> " + area.getId() + "sendCases===>>> " + sendCases.size());
	
				String result = IWSClient.getInstance().doCommand(req);
				UploadAJXXInfoRes res = new UploadAJXXInfoRes();
				res.parseXml(result);
				res.saveOrUpdateCases(pulishDao, sendCases, res.getCode());
			}
		}

	}

	public static void findRecord(SXMS_PulishDataDaoImpl pulishDao,
			SXMX_Area area) throws Exception {
		List<SXMX_Record> records = pulishDao.findRecordByAll(area.getId());
		List<SXMX_Record> sendRecords = new ArrayList<SXMX_Record>(0);
		if (records != null && records.size() > 0) {
			for(SXMX_Record re : records) {
				if(re.getSstatus() == null) {
					sendRecords.add(re);
				}
				if(!"1".equals(re.getSstatus())) {
					sendRecords.add(re);
				}
			}
			if(sendRecords.size() > 0) {
				UploadBLXXInfoReq req = new UploadBLXXInfoReq();
				log.debug("sendRecords areaNumber=>> " + area.getAreaNumber());
				req.setNo(area.getAreaNumber());
				req.setRecords(sendRecords);
	
				log.debug("areaId=>> " + area.getId() + "sendRecords===>>> " + sendRecords.size());
	
				String result = IWSClient.getInstance().doCommand(req);
				UploadBLXXInfoRes res = new UploadBLXXInfoRes();
				res.parseXml(result);
				res.saveOrUpdateRecords(pulishDao, sendRecords, res.getCode());
			}
		}

	}

	/**
	 * 启动线程
	 */
	public void start() {
		worked = true;
		super.setTimeout(WAITTIME);
		super.start();
	}

	/**
	 * 停止线程
	 */
	public void stop() {
		worked = false;
		super.stop();
	}

	/**
	 * 重启同步任务
	 */
	public void restart() {
		worked = true;
		super.notifyTask();
	}
	/**
	 * 重新启动对接线程，没启动则启动
	 */
	public void reStartTask() {
		if(worked) {
			restart();
		} else {
			start();
		}
	}

	public void restart(int status) {
		worked = true;
		executeReq = status;
		super.notifyTask();
	}

	public void update(String type, String id) throws Exception {
		Connection conn = SXMS_Mysql.getInstance().getConnection();
		SXMS_PulishDataDaoImpl pulishDao = new SXMS_PulishDataDaoImpl();
		conn.setAutoCommit(false);
		pulishDao.setConn(conn);
		pulishDao.updateSyncStatus(type, id, "2");
		conn.close();
	}

	/**
	 * 设置视频你中心对接地址
	 * 
	 * @param targetEndpoint
	 */
	public void setTargetEndpoint(String targetEndpoint) {
		IWSClient.getInstance().setTargetEndpoint(targetEndpoint);
	}

	public SXMSConfig getConfig() {
		return config;
	}

	public void setConfig(SXMSConfig config) {
		this.config = config;
	}

	public boolean isWorked() {
		return worked;
	}

	public void setWorked(boolean worked) {
		this.worked = worked;
	}

}
