package fr.free.chiquichi.server;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.free.chiquichi.client.StockService;
import fr.free.chiquichi.client.model.StockData;
import fr.free.chiquichi.server.cron.YahooCronServlet;
import fr.free.chiquichi.server.datastore.PMF;
import fr.free.chiquichi.server.datastore.model.StockDataDb;

/**
 * The server side implementation of the RPC service.
 */

public class StockServiceImpl extends RemoteServiceServlet implements
		StockService {

	/**
	 * 
	 */

	private static final Logger log = Logger.getLogger(YahooCronServlet.class
			.getName());
	private static final long serialVersionUID = 549032966901162548L;

	@SuppressWarnings("unchecked")
	public ArrayList<StockData> stockServer() {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -30);
		Date dateParam = cal.getTime();

		ArrayList<StockData> list = new ArrayList<StockData>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(StockDataDb.class);
		query.declareImports("import java.util.Date");

		query.setFilter("date>= dateParam");
		query.setOrdering("date");
		query.declareParameters("Date dateParam");

		// query.setRange(0, 14);

		List<StockDataDb> results = (List<StockDataDb>) query
				.execute(dateParam);
		Iterator<StockDataDb> iter = results.iterator();
		while (iter.hasNext()) {
			StockDataDb stockData = (StockDataDb) iter.next();
			list.add(new StockData(stockData.getDate(), stockData.getClose()));

			log.info("Donnée envoyée du serveur" + stockData.getDate());

		}

		return list;
	}

	public String login(String login, String password) {
		if (login.equals("malys") && password.equals("malys"))
			return "Login successful";
		else
			return "Wrong login/password";
	}
}
