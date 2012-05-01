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
public class GasolinaDataDb {

	// Cle unique pour le datastore
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private Date date;

	@Persistent
	private double value;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;

		// On date la clé sur la date
		key = KeyFactory.createKey(GasolinaDataDb.class.getSimpleName(),
				date.getTime());
	}

	public Key getKey() {
		return key;
	}

}