package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import controllayer.ControlPrice;
import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import databaselayer.DatabasePBuy;
import databaselayer.DatabasePPrice;
import modellayer.PPrice;

public class TestUpdatePrice {
	
	ControlPrice pc;

	@Before
	public void setUp() {
		pc = new ControlPrice();
	}
	
	@Test
	public void updatePrice() {
		
		int zoneId = 2;
		int price = 99;
		boolean success = false;
		
		
		// Act
		
		try {
			success = pc.updatePrice(zoneId, price);
		} catch (DatabaseLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert
		assertTrue("Price was updated!", success);
		
	}

	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
}
