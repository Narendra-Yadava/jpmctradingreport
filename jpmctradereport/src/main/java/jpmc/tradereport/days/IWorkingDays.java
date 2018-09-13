package jpmc.tradereport.days;

import java.time.LocalDate;

/**
 * This interface is used expose to calculate the days.
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:39:01 PM
 *
 */
public interface IWorkingDays {
	LocalDate findFirstWorkingDate(LocalDate date);
}
