package fr.free.chiquichi.client.model;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Domain model for stock data
 * 
 * @author Lars Vogel
 */

public class StockData implements IsSerializable {

	private Date date;

	private double close;

	/* Contructeur doit etre tj present */
	public StockData() {
		super();
	}

	public StockData(Date date, double close) {
		this.close = close;
		this.date = date;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;

	}

}