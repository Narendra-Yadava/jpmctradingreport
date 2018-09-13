package jpmc.tradereport.model;

import java.time.LocalDate;

/**
 * This class contains the trade ranking data
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:03:33 PM
 *
 */
public class TradeRank {
    private final int rank;
    private final String entity;
    private final LocalDate date;

    public TradeRank(int rank, String entity, LocalDate date) {
        this.rank = rank;
        this.entity = entity;
        this.date = date;
    }

    public int getRank() {
        return rank;
    }

    public String getEntity() {
        return entity;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        final TradeRank other = (TradeRank) obj;

        return other.getRank() == this.getRank() &&
            other.getEntity().equals(this.getEntity()) &&
            other.getDate().equals(this.getDate());
    }

    @Override
    public String toString() {
        return "(" + getRank() + ") - " + getEntity();
    }
}
