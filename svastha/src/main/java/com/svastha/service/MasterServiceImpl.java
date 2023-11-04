package com.svastha.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.svastha.entity.LiveStock;
import com.svastha.entity.MasterTools;
import com.svastha.entity.Users;
import com.svastha.repository.LiveStockRepository;
import com.svastha.repository.MasterToolsRepository;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private LiveStockRepository liveStockDao;

	@Autowired
	private MasterToolsRepository toolsDao;

	@Override
	public void addTools(MasterTools tools) {
		toolsDao.save(tools);
	}

	@Override
	public void addLiveStocks(LiveStock liveStock) {
		liveStockDao.save(liveStock);
	}

	@Override
	public void addToolsFromOthers(String tools, Users u) {
		MasterTools tool = new MasterTools();
		tool.setName(tools);
		tool.setCreatedDt(getCurrentTimeStamp());
		tool.setCreatedBy(u);
		toolsDao.save(tool);
	}

	@Override
	public void addLiveStocksFromOthers(String liveStock, Users u) {
		LiveStock l = new LiveStock();
		l.setName(liveStock);
		l.setCreatedDt(getCurrentTimeStamp());
		l.setCreatedBy(u);
		liveStockDao.save(l);
	}

	public Timestamp getCurrentTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}

}
