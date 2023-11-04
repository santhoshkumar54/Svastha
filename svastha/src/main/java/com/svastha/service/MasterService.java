package com.svastha.service;

import com.svastha.entity.LiveStock;
import com.svastha.entity.MasterTools;
import com.svastha.entity.Users;

public interface MasterService {

	public void addTools(MasterTools tools);

	public void addLiveStocks(LiveStock liveStock);

	public void addToolsFromOthers(String tools, Users u);

	public void addLiveStocksFromOthers(String liveStock, Users u);

}
