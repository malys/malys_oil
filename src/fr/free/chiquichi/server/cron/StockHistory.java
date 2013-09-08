package fr.free.chiquichi.server.cron;

import java.util.ArrayList;

import fr.free.chiquichi.server.datastore.model.StockDataDb;

public class StockHistory {
	ArrayList<StockDataDb> m_stockHistory;

	public StockHistory() {
		m_stockHistory = new ArrayList<StockDataDb>();
	}

	public void add(StockDataDb data) {
		m_stockHistory.add(data);
	}
}