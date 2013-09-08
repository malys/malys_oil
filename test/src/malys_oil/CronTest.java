package malys_oil;

import org.junit.Test;

import fr.free.chiquichi.server.cron.GetStockHistory;

public class CronTest {

	@Test
	public void downloadPrice() {

		Double result = GetStockHistory.getInstance().getPrice();

		org.junit.Assert.assertTrue(result > 0.0);

		System.out.println("Result " + result + " €");
	}

}
