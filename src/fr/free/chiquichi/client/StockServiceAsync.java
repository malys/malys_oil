package fr.free.chiquichi.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.free.chiquichi.client.model.StockData;

/**
 * The async counterpart of <code>StockService</code>.
 */
public interface StockServiceAsync {
	void stockServer(AsyncCallback<ArrayList<StockData>> callback);

	void login(String login, String password, AsyncCallback<String> callback);

}
