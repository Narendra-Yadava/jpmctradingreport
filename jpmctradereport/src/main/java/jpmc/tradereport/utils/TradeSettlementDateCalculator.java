package jpmc.tradereport.utils;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import jpmc.tradereport.days.ArabiaWorkingDays;
import jpmc.tradereport.days.DefaultWorkingDays;
import jpmc.tradereport.days.IWorkingDays;
import jpmc.tradereport.model.DataRow;

/**
 * A settlement date calculator
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 2:59:12 PM
 *
 */
public class TradeSettlementDateCalculator {

    /**
     * Helper function to calculate settlement date for every given instruction
     * @param dataRows the instructions of which the settlement date will be calculated
     */
    public static void calculateSettlementDates(Set<DataRow> dataRows) {
        dataRows.forEach(TradeSettlementDateCalculator::calculateSettlementDate);
    }

    /**
     * Calculate the settlementDate Based on some logic
     * @param dataRow the instruction of which the settlement date will be calculated
     */
    public static void calculateSettlementDate(DataRow dataRow) {
        // Select proper strategy based on the Currency
        final IWorkingDays workingDaysMechanism = getWorkingDaysStrategy(dataRow.getCurrency());

        // find the correct settlement date
        final LocalDate newSettlementDate =
                workingDaysMechanism.findFirstWorkingDate(dataRow.getSettlementDate());

        if (newSettlementDate != null) {
            // set the correct settlement date
            dataRow.setSettlementDate(newSettlementDate);
        }
    }

    /**
     * Select proper strategy based on the Currency
     * @param currency the currency to choose
     * @return the proper working days strategy
     */
    private static IWorkingDays getWorkingDaysStrategy(Currency currency) {
        if ((currency.getCurrencyCode().equals("AED")) ||
            (currency.getCurrencyCode().equals("SAR")))
        {
            return ArabiaWorkingDays.getInstance();
        }
        return DefaultWorkingDays.getInstance();
    }
}
