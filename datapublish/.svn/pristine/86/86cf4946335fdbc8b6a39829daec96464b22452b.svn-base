package com.keda.webservice.server.res;

import java.util.List;

import org.w3c.dom.Node;

import com.keda.webservice.server.sxms.dao.SXMS_PulishDataDaoImpl;
import com.keda.webservice.server.sxms.domian.SXMX_Record;
import com.keda.webservice.server.sxms.domian.SXMX_SYNC;

public class UploadBLXXInfoRes extends WSResponse {

	@Override
	public void parseData(Node dataNode) throws Exception {
		
	}

	public void saveOrUpdateRecords(SXMS_PulishDataDaoImpl pulishDao, List<SXMX_Record> records, String code) throws Exception {
		if(records != null && records.size() > 0) {
			for(SXMX_Record rd : records) {
				SXMX_SYNC sync = pulishDao.findSyncByName("RECORD", String.valueOf(rd.getId()));
				if(sync == null) {
					pulishDao.saveSyncStatus("RECORD", String.valueOf(rd.getId()), "0".equals(code) ? "1" : "2");
				} else {
					if(!"1".equals(rd.getSstatus())) {
						pulishDao.updateSyncStatus("RECORD", String.valueOf(rd.getId()), "0".equals(code) ? "1" : "2");
					}
				}
			}
		}
	}

}
