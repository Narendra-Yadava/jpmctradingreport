package jpmc.tradereport.generator;

import java.util.Set;

import jpmc.tradereport.model.DataRow;

/**
 * This interface used to expose/declare the trade report generator method 
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 3:12:10 PM
 *
 */
public interface ITradeReportGenerator {
    String generateInstructionsReport(Set<DataRow> dataRows);
}
