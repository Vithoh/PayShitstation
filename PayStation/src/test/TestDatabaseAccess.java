package test;

import static org.junit.Assert.*;

import org.junit.*;
import java.time.LocalDate;

//import controllayer.ControlPayStation;
//import controllayer.Currency;
//import controllayer.IPayStation;
//import controllayer.IReceipt;
//import controllayer.IllegalCoinException;

import databaselayer.*;
import modellayer.*;
import controllayer.*;

//import static org.junit.Assert.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik B�rbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestDatabaseAccess {
	
	DBConnection con = DBConnection.getInstance();
	static PBuy tempPBuy;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		con = DBConnection.getInstance();
	}
	
	
	@Test
	public void wasConnected() {
		assertNotNull("Connected - connection cannot be null", con);
		
		
		DBConnection.closeConnection();
		boolean wasNullified = DBConnection.instanceIsNull();
		assertTrue("Disconnected - instance set to null", wasNullified);
		
		con = DBConnection.getInstance();
		assertNotNull("Connected - connection cannot be null", con);		
		
	}
	
	
	@Test
	public void wasInsertedBuy() {
		// Arrange
		int tempId = 0;
		LocalDate timeNow = java.time.LocalDate.now();
		int parkingDuration = 40;
		double payedCentAmount = 100;
		tempPBuy = new PBuy(timeNow, parkingDuration, payedCentAmount);
		PPayStation pStat = new PPayStation(1, "P-423E");
		tempPBuy.setAssociatedPaystation(pStat);
		DatabasePBuy dbPbuy = new DatabasePBuy();
		
		
		// Act
		try {
			tempId = dbPbuy.insertParkingBuy(tempPBuy);
			tempPBuy.setId(tempId);
		} catch (DatabaseLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert
		assertTrue("Inserted new buy", true);
	}	
	
	
	@Test
	public void wasRetrievedPriceDatabaselayer() {
		// Arrange
		PPrice foundPrice = null;
		int pZoneId = 2;
		DatabasePPrice dbPrice = new DatabasePPrice();
		
		
		// Act
		try {
			foundPrice = dbPrice.getPriceByZoneId(pZoneId);
		} catch (DatabaseLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Assert
		assertEquals("Price was found", 25, foundPrice.getParkingPrice());
		
	}
	
	
	@Test
	public void wasRetrievedPriceControllayer() {

		// Arrange
		PPrice foundPrice = null;
		int pZoneId = 2;
		ControlPrice controlPrice = new ControlPrice();
		// Act
		try {
			foundPrice = controlPrice.getPriceRemote(pZoneId);
		} catch (DatabaseLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Assert
		assertEquals("Price was found", 25, foundPrice.getParkingPrice());
		
	}	
	
	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
	
	
	@AfterClass
	public static void cleanUpWhenFinish() {
		
		// Arrange
		DatabasePBuy dbPbuy = new DatabasePBuy();
		int numDeleted = 0;
		
		// Act
		try {
			numDeleted = dbPbuy.deleteParkingBuy(tempPBuy);
		} catch(Exception ex) { 
			System.out.println("Error: " + ex.getMessage());
		} finally {
			DBConnection.closeConnection();
		}
		// Assert
		assertEquals("One row deleted", 1, numDeleted );
	}	

}
