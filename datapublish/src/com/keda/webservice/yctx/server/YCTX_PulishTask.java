package com.keda.webservice.yctx.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.keda.webservice.server.Exception.DBException;
import com.keda.webservice.server.Exception.WSException;
import com.keda.webservice.server.res.UploadSXSInfoRes;
import com.keda.webservice.server.sxms.dao.SXMS_Mysql;
import com.keda.webservice.server.sxmx.req.UploadSXSInfoReq;
import com.keda.webservice.server.utils.AbstarctTask;
import com.keda.webservice.server.utils.TimeUtil;
import com.keda.webservice.server.webservice.v1.IWSClient;
import com.keda.webservice.server.yctx.dao.YCTX_PulishDataDaoImpl;
import com.keda.webservice.server.yctx.domian.YCTX_Department;
import com.keda.webservice.server.yctx.domian.YCTX_Room;
import com.keda.webservice.server.yctx.req.UploadYCTXRoominfoReq;

/**
 * sss
 */
public class YCTX_PulishTask extends AbstarctTask {
	
	private static Logger log = Logger.getLogger(YCTX_PulishTask.class);
	
	public static int TIME = 11;
	
	public static String deptId;
	
	public Object locked = new Object();
	public static int WAITTIME = 1000 * 6;
public static void main(String[] args) {
	try {
		//http://172.16.128.104/inquestweb/enremotelogin.jsp?username=root&password=123456&htmlName=spllNotree&puid=55010000000000000011100017100000&chnid=0&startTime=1481929200000&endTime=1481943600000
		System.out.println(TimeUtil.parserDateString("2016-12-17 11:00:00").getTime());
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
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
			
			if(todayEnd) {
				
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
	 * @param date
	 * @return
	 */
	private boolean isExecuteEnable(Calendar date){
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTimeInMillis());
		int hour = c.get(Calendar.HOUR_OF_DAY);
		return hour == TIME;
		
	}
	
	/**
	 * 推送数据任务
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	private synchronized void sync() throws DBException, SQLException, WSException, InterruptedException {
		
		Connection conn = SXMS_Mysql.getInstance().getConnection();
		YCTX_PulishDataDaoImpl pulishDao = new YCTX_PulishDataDaoImpl();
		conn.setAutoCommit(false);
		pulishDao.setConn(conn);
		try {
			
			synchronized (locked) {
				List<YCTX_Department> depts = findDept(pulishDao);
				for(YCTX_Department dept : depts) {
					String yctx_id = dept.getYctx_id();
					
					findRoom(pulishDao, yctx_id);
					
					
				}
			}
		} catch(Exception e) {
			log.error("sync error...", e);
		} finally {
			conn.close();
		}
	}
	
	/**
	 * 获取部门
	 * @param pulishDao
	 * @return
	 * @throws DBException
	 * @throws SQLException
	 */
	public static List<YCTX_Department> findDept(YCTX_PulishDataDaoImpl pulishDao) throws DBException, SQLException {
		List<YCTX_Department> depts = pulishDao.findDeptByAll();
		
		System.out.println("depts===>>> " + depts.size());
		return depts;
	}

	public static void findRoom(YCTX_PulishDataDaoImpl pulishDao, String deptId) throws DBException, SQLException, WSException {
		List<YCTX_Room> rooms = pulishDao.findRoomByAll(deptId);
		UploadYCTXRoominfoReq req = new UploadYCTXRoominfoReq();
		req.setNo(deptId);
		req.setRooms(rooms);
		
		System.out.println("deptId=>> " + deptId + "rooms===>>> " + rooms.size());
		
		String result = IWSClient.getInstance().doCommand(req);
		UploadSXSInfoRes res = new UploadSXSInfoRes();
//		res.parseXml(result);
		
	}
	

	/**
	 * 启动线程
	 */
	public void start() {
		
		super.setTimeout(30000);
		super.start();
	}

	/**
	 * 停止线程
	 */
	public void stop() {
		super.stop();
	}

	/**
	 * 重启同步任务
	 */
	public void restart() {
		super.notifyTask();
	}
	
	/**
	 * 设置视频你中心对接地址
	 * @param targetEndpoint
	 */
	public void setTargetEndpoint(String targetEndpoint) {
		IWSClient.getInstance().setTargetEndpoint(targetEndpoint);
	}
	
	
}
