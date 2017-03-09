package com.keda.webservice.server.res;

import java.util.List;

import org.w3c.dom.Node;

import com.keda.webservice.server.sxms.dao.SXMS_PulishDataDaoImpl;
import com.keda.webservice.server.sxms.domian.SXMX_Room;
import com.keda.webservice.server.sxms.domian.SXMX_SYNC;

public class UploadSXSInfoRes extends WSResponse {

	@Override
	public void parseData(Node dataNode) throws Exception {
		
	}
	
	/**
	 * 保存已同步的审讯室 1是成功，2是失败
	 * @param pulishDao
	 * @param rooms
	 * @param code
	 * @throws Exception
	 */
	public void saveOrUpdateRooms(SXMS_PulishDataDaoImpl pulishDao, List<SXMX_Room> rooms, String code) throws Exception {
		if(rooms != null && rooms.size() > 0) {
			for(SXMX_Room room : rooms) {
				SXMX_SYNC sync = pulishDao.findSyncByName("ROOM", String.valueOf(room.getId()));
				if(sync == null) {
					pulishDao.saveSyncStatus("ROOM", String.valueOf(room.getId()), "0".equals(code) ? "1" : "2");
				} else {
					if(!"1".equals(room.getSstatus())) {
						pulishDao.updateSyncStatus("ROOM", String.valueOf(room.getId()), "0".equals(code) ? "1" : "2");
					}
				}
			}
		}
	}

}
