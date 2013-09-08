package fr.free.chiquichi.client;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gchart.client.GChart;
import com.googlecode.gchart.client.GChart.SymbolType;

import fr.free.chiquichi.client.model.StockData;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class StockPrediction implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */

	private final StockServiceAsync stockService = GWT
			.create(StockService.class);

	private ArrayList<StockData> list;

	private void getDataStock() {

		stockService.stockServer(new AsyncCallback<ArrayList<StockData>>() {

			public void onFailure(Throwable caught) {
				// **//
				list = new ArrayList<StockData>();

			}

			public void onSuccess(ArrayList<StockData> result) {
				list = result;
				processList(list);

			}
		});

	}

	/* Générer le graphique */
	private void processList(ArrayList<StockData> list) {

		Integer i = 0;

		GChart chart = new GChart();

		chart.setChartTitle("<b>Oil Price Chart</b>");
		chart.setChartSize(640, 400);
		chart.addCurve();
		// solid, 2px thick, 1px resolution, connecting lines:
		chart.getCurve().getSymbol().setSymbolType(SymbolType.LINE);
		chart.getCurve().getSymbol().setFillThickness(2);
		chart.getCurve().getSymbol().setFillSpacing(1);
		// Make center-fill of square point markers same color as line:
		chart.getCurve()
				.getSymbol()
				.setBackgroundColor(
						chart.getCurve().getSymbol().getBorderColor());

		chart.getCurve().setLegendLabel("Price");
		chart.getXAxis().setAxisLabel("Day(s)");
		chart.getYAxis().setAxisLabel("Euro");

		if (list != null) {
			Iterator<StockData> iter = list.iterator();
			while (iter.hasNext()) {
				StockData stockData = (StockData) iter.next();

				chart.getCurve().addPoint(i, stockData.getClose());
				i++;

			}

		}

		RootPanel.get().add(chart);
		chart.update();

	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		// RootPanel.get().add(new Login());
		getDataStock();

	}
}
