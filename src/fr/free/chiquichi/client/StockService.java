package fr.free.chiquichi.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.free.chiquichi.client.model.StockData;

/**
 * The client side stub for the RPC service.
 * 
 * @gwt.typeArgs <fr.free.chiquichi.client.model.StockData>
 */
@RemoteServiceRelativePath("stock")
public interface StockService extends RemoteService {
	ArrayList<StockData> stockServer();

	String login(String login, String password);
}
