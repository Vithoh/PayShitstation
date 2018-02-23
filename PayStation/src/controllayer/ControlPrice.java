package controllayer;

import databaselayer.DatabaseLayerException;
import databaselayer.DbPPrice;
import databaselayer.DatabasePPrice;
import modellayer.PPrice;

public class ControlPrice {
	
	public PPrice getPriceRemote(int zoneId) throws DatabaseLayerException {
		
		// Get price from Parkingsystem DB
		DbPPrice dbPrice = new DatabasePPrice();
		PPrice readPrice = dbPrice.getPriceByZoneId(zoneId);
		//
		return readPrice;
	}
	public boolean updatePrice(int zoneId, int price) throws DatabaseLayerException {
		
		int insertedKey = -1;
		
		// Get price from Parkingsystem DB
		DatabasePPrice dbPrice = new DatabasePPrice();
		insertedKey = dbPrice.insertPriceByZoneId(zoneId, price);
		
		return insertedKey > -1;
	}

}
