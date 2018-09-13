package jpmc.tradereport.generator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import jpmc.tradereport.model.DataRow;
import jpmc.tradereport.model.TradeRank;
import jpmc.tradereport.utils.TradeSettlementDateCalculator;
import jpmc.tradereport.utils.TradeSettlementStatsCalculator;

/**
 * This class is used to provide the trade generation implementation.
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:12:55 PM
 *
 */
public class TradeReportGenerator implements ITradeReportGenerator {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public String generateInstructionsReport(Set<DataRow> dataRows) {
        // first calculate the correct settlement dates
        TradeSettlementDateCalculator.calculateSettlementDates(dataRows);

        // Build the report string
        return generateDailyOutgoingRanking(dataRows,
                generateDailyIncomingRanking(dataRows,
                generateDailyIncomingAmount(dataRows,
                generateDailyOutgoingAmount(dataRows, stringBuilder))))
            .toString();
    }

    private static StringBuilder generateDailyOutgoingAmount(Set<DataRow> dataRows, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                TradeSettlementStatsCalculator.calculateDailyOutgoingAmount(dataRows);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingAmount(Set<DataRow> dataRows, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                TradeSettlementStatsCalculator.calculateDailyIncomingAmount(dataRows);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                    .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyOutgoingRanking(Set<DataRow> dataRows, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<TradeRank>> dailyOutgoingRanking =
                TradeSettlementStatsCalculator.calculateDailyOutgoingRanking(dataRows);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingRanking.keySet()) {
            for (TradeRank tradeRank : dailyOutgoingRanking.get(date)) {
                stringBuilder
                    .append(date).append("   |      ")
                    .append(tradeRank.getRank()).append("     |    ")
                    .append(tradeRank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingRanking(Set<DataRow> dataRows, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<TradeRank>> dailyIncomingRanking =
                TradeSettlementStatsCalculator.calculateDailyIncomingRanking(dataRows);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyIncomingRanking.keySet()) {
            for (TradeRank tradeRank : dailyIncomingRanking.get(date)) {
                stringBuilder
                        .append(date).append("   |      ")
                        .append(tradeRank.getRank()).append("     |    ")
                        .append(tradeRank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }
}
