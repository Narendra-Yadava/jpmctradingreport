package jpmc.tradereport.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represents the price details of an instruction
 * 
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:58:53 PM
 *
 */
public class DataRowDetail {
	// In which currency
	private final Currency currency;

	// The foreign exchange rate with respect to USD that was agreed
	private final BigDecimal agreedFx;

	// Number of shares to be bought or sold
	private final int units;

	// The price per unit
	private final BigDecimal pricePerUnit;

	// The total trade amount in USD
	private final BigDecimal tradeAmount;

	public DataRowDetail(Currency currency, BigDecimal agreedFx, int units, BigDecimal pricePerUnit) {
		this.currency = currency;
		this.agreedFx = agreedFx;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.tradeAmount = calculateAmount(this);
	}

	private static BigDecimal calculateAmount(DataRowDetail ins) {
		return ins.getPricePerUnit().multiply(BigDecimal.valueOf(ins.getUnits())).multiply(ins.getAgreedFx());
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public int getUnits() {
		return units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}

	public Currency getCurrency() {
		return currency;
	}
}
