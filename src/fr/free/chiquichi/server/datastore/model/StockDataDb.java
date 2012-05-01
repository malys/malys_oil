package fr.free.chiquichi.server.datastore.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class StockDataDb{

	// Cle unique pour le datastore
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Date date;

	@Persistent
	private double open;

	@Persistent
	private double high;

	@Persistent
	private double low;

	@Persistent
	private double close;

	@Persistent
	private long volume;

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

		// On date la clé sur la date
		key = KeyFactory.createKey(StockDataDb.class.getSimpleName(), date
				.getTime());
	}

	public Key getKey() {
		return key;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

}