package fr.free.chiquichi.server.cron;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Logger;

import fr.free.chiquichi.server.datastore.model.StockDataDb;

public class GetStockHistory {

	private static GetStockHistory instance;
	private static final Logger logger = Logger
			.getLogger(YahooCronServlet.class.getName());

	SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

	NumberFormat nf = NumberFormat.getInstance(Locale.US);

	NumberFormat pf = NumberFormat.getInstance(Locale.US);

	StockHistory history = new StockHistory();

	final int oneDay = (60 * 60 * 24);

	public static GetStockHistory getInstance() {
		if (instance == null) {
			instance = new GetStockHistory();
		}
		return instance;
	}

	public ArrayList<StockDataDb> updateHistory(String company) {
		/*
		 * http://ichart.finance.yahoo.com/table.csv?s=$symbol&d=($monthEnd-1)&e=
		 * $dayEnd
		 * &f=$yearEnd&g=d&a=($monthStart-1)&b=$dayStart&c=$yearStart&ignore
		 * =.csv
		 */

		/*
		 * http://download.finance.yahoo.com/d/quotes.csv?s=CLJ10.NYM&f=sl1d1t1c1ohgv
		 * &e=.csv
		 */
		/*
		 * StringBuffer url = new StringBuffer(
		 * "http://download.finance.yahoo.com/d/quotes.csv" + "?s=");
		 * url.append(company); //url.append("&f=sl1d1t1c1ohgv&e=.csv");
		 * 
		 * url.append("&a=" + (Calendar.getInstance().get(Calendar.MONTH) ));
		 * url.append("&b=" + (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
		 * -10)); url.append("&c=" + Calendar.getInstance().get(Calendar.YEAR));
		 * url.append("&d=" + (Calendar.getInstance().get(Calendar.MONTH)));
		 * url.append("&e=" +
		 * (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));
		 * url.append("&f=" + Calendar.getInstance().get(Calendar.YEAR));
		 */

		try {
			/*
			 * HttpClient client = new HttpClient();
			 * client.getHostConfiguration().setProxy("proxy", 8080); // Delete
			 * // this Line // if you // don't use // a proxy
			 * client.getHttpConnectionManager
			 * ().getParams().setConnectionTimeout( 5000);
			 * System.out.println(url.toString()); HttpMethod method = new
			 * GetMethod(url.toString()); method.setFollowRedirects(true);
			 * client.executeMethod(method);
			 */
			String url = "http://prixdubaril.com/";

			URLConnection urlConnection = new URL(url.toString())
					.openConnection();
			urlConnection.setUseCaches(false);
			urlConnection.connect();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			// The first line is the header, ignoring
			String inputLine = null;
			;
			int index = 0;
			int index2 = 0;
			String price = "";

			while ((inputLine = in.readLine()) != null) {
				/*
				 * "CLJ10.NYM",79.69,"3/15/2010","2:19pm",-1.55,81.13,83.16,80.57
				 * ,0
				 */
				/*
				 * if (inputLine.startsWith("<")) continue; String[] item =
				 * inputLine.split(","); if (item.length < 6) continue;
				 */
				index = inputLine.indexOf(">Le Cours du baril de");
				index2 = inputLine.indexOf("euro");

				if (index > -1 && index2 > -1 ) {

					price = extractValue(inputLine, index);
					if (!price.equals("")) {

						Calendar day = Calendar.getInstance();

						StockDataDb data = new StockDataDb();

						data.setClose(Double.valueOf(price));

						data.setDate(day.getTime());
						history.add(data);
						break;
					}

				}
			}
			in.close();
			return history.m_stockHistory;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	/**
	 * Extrait le prix du petrole
	 * 
	 * @param line
	 * @param initIndex
	 * @return
	 */
	private String extractValue(String line, Integer initIndex) {
		// Le Cours du baril de pétrole en dollars</span>:<span
		// style="background-color: #333333; color: #ffffff;"><span> 80.23
		// </span>
		try {
			int deb = line.indexOf("<span>", initIndex);
			int end = line.indexOf("</span>", deb);

			return line.substring(deb+6, end).trim();
		} catch (Exception e) {
			return "";
		}

	}
}
