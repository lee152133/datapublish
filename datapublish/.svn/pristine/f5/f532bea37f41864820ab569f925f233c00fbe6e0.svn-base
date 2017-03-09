package com.keda.webservice.server.res;

import java.util.List;

import org.w3c.dom.Node;

import com.keda.webservice.server.sxms.dao.SXMS_PulishDataDaoImpl;
import com.keda.webservice.server.sxms.domian.SXMX_Case;
import com.keda.webservice.server.sxms.domian.SXMX_SYNC;

public class UploadAJXXInfoRes extends WSResponse {

	@Override
	public void parseData(Node dataNode) throws Exception {

	}

	public void saveOrUpdateCases(SXMS_PulishDataDaoImpl pulishDao, List<SXMX_Case> cases, String code) throws Exception {
		if(cases != null && cases.size() > 0) {
			for(SXMX_Case ca : cases) {
				SXMX_SYNC sync = pulishDao.findSyncByName("CASE", String.valueOf(ca.getId()));
				if(sync == null) {
					pulishDao.saveSyncStatus("CASE", String.valueOf(ca.getId()), "0".equals(code) ? "1" : "2");
				} else {
					if(!"1".equals(ca.getSstatus())) {
						pulishDao.updateSyncStatus("CASE", String.valueOf(ca.getId()), "0".equals(code) ? "1" : "2");
					}
				}
			}
		}
	}
}
