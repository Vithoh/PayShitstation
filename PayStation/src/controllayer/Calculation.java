package controllayer;

import modellayer.PPrice;

public class Calculation {
	public static double GetCoinValueInCent(double coinValue, Currency.ValidCurrency currency,
			Currency.ValidCoinType coinType) {
		double valueInCent = 0;

		if (currency == Currency.ValidCurrency.DKK) {
			PPrice nowPrice = new PPrice();
			valueInCent = getDkkCoinValueInCent(coinValue, coinType, nowPrice);
		} else {
			valueInCent = getEuroCoinValueInCent(coinValue, coinType);
		}

		return valueInCent;
	}

	public static int GetTimeBoughtInMinutes(double accumulatedAmountInCent, PPrice usePrice) {
		int timeBoughtInMinutes = 0;

		double timeBoughtInSeconds = accumulatedAmountInCent * usePrice.getParkingPrice();
		timeBoughtInMinutes = (int) ((timeBoughtInSeconds + 59) / 60);

		return timeBoughtInMinutes;
	}

	public static double getEuroCoinValueInCent(double coinValue, Currency.ValidCoinType coinType) {
		double valueInCent = 0;

		if (coinType == Currency.ValidCoinType.INTEGER) {
			valueInCent = coinValue * 100;
		} else {
			valueInCent = coinValue;
		}

		return valueInCent;
	}

	public static double getDkkCoinValueInCent(double coinValue, Currency.ValidCoinType coinType, PPrice price) {
		double valueInCent = 0;

		if (coinType == Currency.ValidCoinType.INTEGER) {
			valueInCent = (coinValue * 100) / price.getExchangeEuroDkk();
		} else {
			valueInCent = coinValue / price.getExchangeEuroDkk();
		}

		return valueInCent;
	}	
}