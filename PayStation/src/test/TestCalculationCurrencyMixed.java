package test;

import org.junit.*;
import static org.junit.Assert.*;

import controllayer.*;
import modellayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestCalculationCurrencyMixed {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	@Test
	public void shouldDisplay46MinFor1DKKand1EUR() throws IllegalCoinException {
		
		// Arrange
		int expectedParkingTime = 46;	// In minutes
		int dkkCoinValue = 1;
		int eurCoinValue = 1;
		
		Currency.ValidCurrency dkkCoinCurrency = Currency.ValidCurrency.DKK;
		Currency.ValidCoinType dkkCoinType = Currency.ValidCoinType.INTEGER;
		
		Currency.ValidCurrency eurCoinCurrency = Currency.ValidCurrency.EURO;
		Currency.ValidCoinType eurCoinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(dkkCoinValue, dkkCoinCurrency, dkkCoinType);
		ps.addPayment(eurCoinValue, eurCoinCurrency, eurCoinType);
		
		// Assert
		assertEquals("Should display 46 min for 1 DKK and 1 EUR", expectedParkingTime, ps.readDisplay());
	}

	
	/** Fixture for pay station testing. */
	@After
	public void cleanUp() {
		ps.setReady();
	}
	
}
