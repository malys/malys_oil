package fr.free.chiquichi.server.cron;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.free.chiquichi.server.datastore.PMF;
import fr.free.chiquichi.server.datastore.model.StockDataDb;

/**
 * Purgte
 * 
 * @author sylvain
 * 
 */
@SuppressWarnings("serial")
public class PredictionCleanCronServlet extends HttpServlet {
	private static final Logger _logger = Logger
			.getLogger(PredictionCleanCronServlet.class.getName());

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(StockDataDb.class);
		query.setFilter("date< dateParam");
		query.declareParameters("Date dateParam");

		Calendar calend = Calendar.getInstance();
		calend.add(Calendar.YEAR, -1);
		Date date = calend.getTime();
		query.declareImports("import java.util.Date");

		_logger.info("Suppression avant la date " + date.toString());

		query.deletePersistentAll(date);

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
