package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import controllayer.*;

/**
 * Inspired by the book: Flexible, Reliable Software Henrik Bærbak Christensen:
 * Flexible, Reliable Software. Taylor and Francis Group, LLC 2010
 */

public class TestIllegalCoin {

	ControlPayStation ps;

	/** Fixture for pay station testing. */
	@Before
	public void setUp() {
		ps = new ControlPayStation();
	}

	/**
	 * Verify that illegal coins are rejected.
	 */
	
	// Norwegian coin
	@Test(expected = IllegalCoinException.class)
	public void shouldDisplay120MinFor1NKKand2EUR() throws IllegalCoinException {
		
		// Arrange
		int nokCoinValue = 2;
		
		Currency.ValidCurrency nokCoinCurrency = Currency.ValidCurrency.NOK;
		Currency.ValidCoinType nokCoinType = Currency.ValidCoinType.INTEGER;
		
		// Act
		ps.addPayment(nokCoinValue, nokCoinCurrency, nokCoinType);
	}
	
	// unknown Euro coin value
	@Test(expected = IllegalCoinException.class)
	public void shouldRejectIllegalEuroCoin() throws IllegalCoinException {
		// Arrange
				int eurCoinValue = 5;
				
				Currency.ValidCurrency eurCoinCurrency = Currency.ValidCurrency.EURO;
				Currency.ValidCoinType eurCoinType = Currency.ValidCoinType.INTEGER;
				
				// Act
				ps.addPayment(eurCoinValue, eurCoinCurrency, eurCoinType);
	}
}
