package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyEuro {

	private ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}
	
	/**
	 * Entering no coins should make the display report 0 minutes parking time.
	 */
	@Test
	public void shouldDisplay0() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 0;
		
		// Act
		// No action
		
		// Assert
		assertEquals("Should display 0 min for no coins", expectedParkingTime, ps.readDisplay());
	}	

	/**
	 * Entering 5 cents should make the display report 2 minutes parking time
	 */
	@Test
	public void shouldDisplay1MinFor1Cents() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 1;	// In minutes		
		int coinValue = 1;
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.FRACTION;
		
		// Act
		ps.addPayment(coinValue, coinCurrency, coinType);
		
		// Assert
		assertEquals("Should display 2 min for 1 cent", expectedParkingTime, ps.readDisplay());
	}
	
	@Test
	public void shouldDisplay120MinFor1EUR() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 120;	// In minutes		
		int firstCoinValue = 1;
		int seccondCoinValue = 2;
		
		Currency.ValidCurrency coinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType coinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(firstCoinValue, coinCurrency, coinType);
		ps.addPayment(seccondCoinValue, coinCurrency, coinType);
		
		// Assert
		assertEquals("Should display 120 min for 3 EUR", expectedParkingTime, ps.readDisplay());
	}
	
	
}
