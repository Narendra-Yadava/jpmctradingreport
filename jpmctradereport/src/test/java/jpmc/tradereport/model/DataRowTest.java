package jpmc.tradereport.model;

import org.junit.Test;

import jpmc.tradereport.model.DataRow;
import jpmc.tradereport.model.DataRowDetail;
import jpmc.tradereport.model.TradeAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.Assert.assertEquals;

/**
 * This class is used to test transaction data
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:57:49 PM
 *
 */
public class DataRowTest {

    @Test
    public void testTradeAmountCalc() throws Exception {
        final BigDecimal agreedFx = BigDecimal.valueOf(0.50);
        final BigDecimal pricePerUnit = BigDecimal.valueOf(100.25);
        final int units = 200;

        final DataRow fakeInstruction = new DataRow(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 20), // Its a Monday
                new DataRowDetail(
                        Currency.getInstance("SGD"),
                        agreedFx,
                        units,
                        pricePerUnit));

        // test initialization
        assertEquals(agreedFx, fakeInstruction.getAgreedFx());
        assertEquals(pricePerUnit, fakeInstruction.getPricePerUnit());
        assertEquals(units, fakeInstruction.getUnits());

        final BigDecimal correct = pricePerUnit
                                    .multiply(agreedFx)
                                    .multiply(BigDecimal.valueOf(units))
                                    .setScale(2, BigDecimal.ROUND_HALF_EVEN);
        assertEquals(correct, fakeInstruction.getTradeAmount());
    }
}