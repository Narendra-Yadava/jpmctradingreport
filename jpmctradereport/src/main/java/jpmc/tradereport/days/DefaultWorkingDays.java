package jpmc.tradereport.days;

import java.time.DayOfWeek;

/**
 * This class contains working days data
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:14:23 PM
 *
 */
public class DefaultWorkingDays extends WorkingDays {

    private static DefaultWorkingDays instance = null;

    public static DefaultWorkingDays getInstance() {
        if (instance == null) {
            instance = new DefaultWorkingDays();
        }
        return instance;
    }

    private DefaultWorkingDays() {
        super();
    }

    @Override
    protected void setupWorkingDays() {
        this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.FRIDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false);
        this.isWorkingDayMap.put(DayOfWeek.SUNDAY, false);
    }
}
