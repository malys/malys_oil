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

import fr.free.chiquichi.server.datastore.model.StockDataDb;

public class GetStockHistory {

	private static GetStockHistory instance;

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

	/***
	 * Extract Oil Price from Web Page
	 * 
	 * @return
	 */
	public Double getPrice() {
		Double result = 0.0;

		try {
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
			int index = -1;
			int index2 = -1;

			String price = "";
			String tag = "Le Cours du baril de p";
			String tag2 = "en euros";

			while ((inputLine = in.readLine()) != null) {
				index = inputLine.toUpperCase().indexOf(tag.toUpperCase());

				if (index > -1) {
					index2 = inputLine.toUpperCase().indexOf(
							tag2.toUpperCase(), index + 1);

				}

				if (index > -1 && index2 > -1) {

					price = extractValue(inputLine, index2);
					if (!price.equals("")) {

						result = Double.valueOf(price);
						break;
					}

				} else {
					// System.out.println(inputLine);
				}
			}
			in.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		System.out.println("Price detected " + result);
		return result;

	}

	public ArrayList<StockDataDb> updateHistory() {

		try {

			double tmp = getPrice();

			if (tmp > 0.0) {

				Calendar day = Calendar.getInstance();

				StockDataDb data = new StockDataDb();
				data.setClose(tmp);

				data.setDate(day.getTime());
				history.add(data);
			}

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

			return line.substring(deb + 6, end).trim();
		} catch (Exception e) {
			return "";
		}

	}
}
