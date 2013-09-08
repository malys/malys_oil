package fr.free.chiquichi.server.cron;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.free.chiquichi.server.datastore.PMF;
import fr.free.chiquichi.server.datastore.model.StockDataDb;

/**
 * Download data from stock
 * 
 * @author sylvain
 * 
 */
@SuppressWarnings("serial")
public class YahooCronServlet extends HttpServlet {
	private static final Logger _logger = Logger
			.getLogger(YahooCronServlet.class.getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		StockDataDb stockData = null;

		try {
			// Put your logic here
			// BEGIN
			PersistenceManager pm = PMF.get().getPersistenceManager();
			List<StockDataDb> list = GetStockHistory.getInstance()
					.updateHistory("CLJ10.NYM");
			Iterator<StockDataDb> iter = list.iterator();
			while (iter.hasNext()) {
				stockData = (StockDataDb) iter.next();

				pm.makePersistent(stockData);

				_logger.info(YahooCronServlet.class.getName() + ": Ajout de "
						+ stockData.getDate().toString() + " "
						+ stockData.getClose());
				
				stockData = null;

			}

			pm.close();

		} catch (Exception ex) {
			_logger.severe(YahooCronServlet.class.getName() + ": "
					+ ex.getMessage());
		}

		_logger.info(YahooCronServlet.class.getName()
				+ ": Cron Job has been executed");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
