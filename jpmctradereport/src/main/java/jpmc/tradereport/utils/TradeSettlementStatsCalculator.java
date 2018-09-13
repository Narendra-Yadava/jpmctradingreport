package jpmc.tradereport.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import jpmc.tradereport.model.DataRow;
import jpmc.tradereport.model.TradeAction;
import jpmc.tradereport.model.TradeRank;

import static java.util.stream.Collectors.*;

/**
 * Describes a mapping between dates and the trade amount of those dates, based
 * on instructions
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:01:00 PM
 *
 */
public class TradeSettlementStatsCalculator {

	// Create a predicate for outgoing
	private final static Predicate<DataRow> outgoingInstructionsPredicate = instruction -> instruction.getAction()
			.equals(TradeAction.BUY);

	// Create a predicate for incoming
	private final static Predicate<DataRow> incomingInstructionsPredicate = instruction -> instruction.getAction()
			.equals(TradeAction.SELL);

	/**
	 * Calculates the daily outgoing (BUY) trade amount in USD
	 * 
	 * @param dataRows
	 *            the instruction to calculate the stats from
	 * @return a map from date to total amount
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyOutgoingAmount(Set<DataRow> dataRows) {
		return calculateDailyAmount(dataRows, outgoingInstructionsPredicate);
	}

	/**
	 * Calculates the daily incoming (SELL) trade amount in USD
	 * 
	 * @param dataRows
	 *            the instruction to calculate the stats from
	 * @return a map from date to total amount
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyIncomingAmount(Set<DataRow> dataRows) {
		return calculateDailyAmount(dataRows, incomingInstructionsPredicate);
	}

	/**
	 * Ranks the daily outgoing (BUY) by trade amount in USD
	 * 
	 * @param dataRows
	 *            the instruction to calculate the stats from
	 * @return a map from date to a list of ranks (ranking)
	 */
	public static Map<LocalDate, LinkedList<TradeRank>> calculateDailyOutgoingRanking(Set<DataRow> dataRows) {
		return calculateRanking(dataRows, outgoingInstructionsPredicate);
	}

	/**
	 * Ranks the daily incoming (SELL) by trade amount in USD
	 * 
	 * @param dataRows
	 *            the instruction to calculate the stats from
	 * @return a map from date to a list of ranks (ranking)
	 */
	public static Map<LocalDate, LinkedList<TradeRank>> calculateDailyIncomingRanking(Set<DataRow> dataRows) {
		return calculateRanking(dataRows, incomingInstructionsPredicate);
	}

	private static Map<LocalDate, BigDecimal> calculateDailyAmount(Set<DataRow> dataRows,
			Predicate<DataRow> predicate) {
		return dataRows.stream().filter(predicate).collect(groupingBy(DataRow::getSettlementDate,
				mapping(DataRow::getTradeAmount, reducing(BigDecimal.ZERO, BigDecimal::add))));
	}

	private static Map<LocalDate, LinkedList<TradeRank>> calculateRanking(Set<DataRow> dataRows,
			Predicate<DataRow> predicate) {
		final Map<LocalDate, LinkedList<TradeRank>> ranking = new HashMap<>();

		dataRows.stream().filter(predicate).collect(groupingBy(DataRow::getSettlementDate, toSet()))
				.forEach((date, dailyInstructionSet) -> {
					final AtomicInteger rank = new AtomicInteger(1);

					final LinkedList<TradeRank> tradeRanks = dailyInstructionSet.stream()
							.sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
							.map(instruction -> new TradeRank(rank.getAndIncrement(), instruction.getEntity(), date))
							.collect(toCollection(LinkedList::new));

					ranking.put(date, tradeRanks);
				});

		return ranking;
	}
}
