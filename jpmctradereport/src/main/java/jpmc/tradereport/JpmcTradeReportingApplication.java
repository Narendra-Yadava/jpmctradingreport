package jpmc.tradereport;

import java.util.Set;

import jpmc.tradereport.generator.ITradeReportGenerator;
import jpmc.tradereport.generator.TradeReportGenerator;
import jpmc.tradereport.model.DataRow;
import jpmc.tradereport.utils.TradeReportDataInitializer;

/**
 * This is main class to run this application
 *
 * @author Narendra.Kumar
 * @date Sep 13, 2018
 * @time 2:59:00 PM
 *
 */
public class JpmcTradeReportingApplication {

    public static void main(String[] args) {

        final Set<DataRow> dataRows = TradeReportDataInitializer.getFakeInstructions();
        StringBuilder sb=new StringBuilder();
        dataRows.forEach(instruction->{
        	sb.append("\n"+instruction.getEntity()+ "  |  ");
        	sb.append(instruction.getAction()+ "  |  ");
        	sb.append(instruction.getAgreedFx()+ "  |  ");
        	sb.append(instruction.getCurrency().getCurrencyCode()+ "  |  ");
        	sb.append(instruction.getInstructionDate()+ "  |  ");
        	sb.append(instruction.getSettlementDate()+ "  |  ");
        	sb.append(instruction.getUnits()+ "  |  ");
        	sb.append(instruction.getPricePerUnit()+ "\n");
        	
        }
        );
        System.out.println(sb.toString());	

        final ITradeReportGenerator tradeReportGenerator = new TradeReportGenerator();

        System.out.println(tradeReportGenerator.generateInstructionsReport(dataRows));
    }
}
