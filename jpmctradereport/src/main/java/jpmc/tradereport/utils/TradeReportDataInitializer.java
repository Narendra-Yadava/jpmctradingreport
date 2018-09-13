package jpmc.tradereport.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

import jpmc.tradereport.model.DataRow;
import jpmc.tradereport.model.DataRowDetail;
import jpmc.tradereport.model.TradeAction;

/**
 * This class is used to prepare Data for trade
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:06:40 PM
 *
 */
public class TradeReportDataInitializer {
    public static Set<DataRow> getFakeInstructions() {
        return new HashSet<>(Arrays.asList(

            new DataRow(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 20),
                new DataRowDetail(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25))),

            new DataRow(
                "E2",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 19),
                new DataRowDetail(
                        Currency.getInstance("AED"),
                        BigDecimal.valueOf(0.22),
                        450,
                        BigDecimal.valueOf(150.5))),

            new DataRow(
                "E3",
                TradeAction.SELL,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 18),
                new DataRowDetail(
                        Currency.getInstance("SAR"),
                        BigDecimal.valueOf(0.27),
                        150,
                        BigDecimal.valueOf(400.8))),

            new DataRow(
                "E4",
                TradeAction.SELL,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                new DataRowDetail(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                        50,
                        BigDecimal.valueOf(500.6))),

            new DataRow(
                "E5",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                new DataRowDetail(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                        20,
                        BigDecimal.valueOf(40.6))),

            new DataRow(
                "E6",
                TradeAction.BUY,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                new DataRowDetail(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                        20,
                        BigDecimal.valueOf(40.6))),

            new DataRow(
                "E7",
                TradeAction.SELL,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                new DataRowDetail(
                        Currency.getInstance("EUR"),
                        BigDecimal.valueOf(0.34),
                    1000,
                        BigDecimal.valueOf(160.6))),

            new DataRow(
                "E8",
                TradeAction.SELL,
                LocalDate.of(2017, 3, 10),
                LocalDate.of(2017, 3, 21),
                    new DataRowDetail(
                            Currency.getInstance("EUR"),
                            BigDecimal.valueOf(0.34),
                        120,
                            BigDecimal.valueOf(500.6)))
        ));
    }
}
