package banoun.aneece.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import banoun.aneece.model.TradeEntry;

public class TradeReportingUtility {

	public static Comparator<TradeEntry> comparingAmountOfTrade = (trader1, trader2) -> trader2.getAmountOfTrade()
			.compareTo(trader1.getAmountOfTrade());

	public static Comparator<TradeEntry> comparingSettlementDate = (trader1, trader2) -> {
		String pattern = "dd MMM yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		LocalDate trader1Date = LocalDate.parse(trader1.getSettlementDate(), formatter);
		LocalDate trader2Date = LocalDate.parse(trader2.getSettlementDate(), formatter);
		return trader2Date.compareTo(trader1Date);
	};

	private static List<String> sortingTimes = new ArrayList<>();

	public static String runTradeReporting(){

		Long start = System.currentTimeMillis();
		StringBuffer result = new StringBuffer();
		result.append(printSortedReport(comparingAmountOfTrade));
		result.append(printSortedReport(comparingSettlementDate));
		Long end = System.currentTimeMillis();
		System.out.println("Data Load time: " + DataLoadingUtility.DATA_LOADING_TIME);
		for(String sortingTime : sortingTimes){
			System.out.println("Sorting time: " + sortingTime);
		}
		System.out.println("Total time: " + DataLoadingUtility.timing(start, end));
		
		return result.toString();
	}
	
	public static String runTradeFilteredReporting(String filter){

		Long start = System.currentTimeMillis();
		StringBuffer result = new StringBuffer();
		result.append(printFilteredReport(filter));
		Long end = System.currentTimeMillis();
		System.out.println("Data Load time: " + DataLoadingUtility.DATA_LOADING_TIME);
		for(String sortingTime : sortingTimes){
			System.out.println("Sorting time: " + sortingTime);
		}
		System.out.println("Total time: " + DataLoadingUtility.timing(start, end));
		
		return result.toString();
	}
	
	private static String printSortedReport(Comparator<TradeEntry> sortOrder) {
		sortTrades(sortOrder); 
		StringBuffer result = new StringBuffer();
		result.append(printReport(DataLoadingUtility.tradeEntries, "All Buy/Sell Traders (Outgoing/Incoming)"));
		result.append( printReport(DataLoadingUtility.tradeEntries.stream().filter(trader -> trader.getBuySellFlag() == 'S')
				.collect(Collectors.toList()), "Sellers(Incomings)"));
		result.append( printReport(DataLoadingUtility.tradeEntries.stream().filter(trader -> trader.getBuySellFlag() == 'B')
				.collect(Collectors.toList()), "Buyers(Outgoings)"));
		return result.toString();
	}
	
	private static String printFilteredReport(String filter) {
		StringBuffer result = new StringBuffer();
		result.append(printReport(DataLoadingUtility.tradeEntries.stream().filter(trader -> trader.getTraderName().equals(filter))
				.collect(Collectors.toList()), "Look up result for: ***"+filter+"***"));
		return result.toString();
	}

	public static void sortTrades(Comparator<TradeEntry> sortOrder) {
		Long start = System.currentTimeMillis();
		Collections.sort(DataLoadingUtility.tradeEntries, sortOrder);
		Long end = System.currentTimeMillis();
		sortingTimes.add(DataLoadingUtility.timing(start, end));
	}

	private static String printReport(List<TradeEntry> tradeEnties, String header) {
		StringBuffer result = new StringBuffer();
		String cornerChar = "*";
		String lineChar = "-";
		String[] tableHeaders = {"Entity", "In/Out", "Amount", "Settlement Date", "Instruction Date", "Currency",
				"Agreed Fx", "Units", "Unit Price" };
		int[] columnsWidth = adjustWidthForData(tradeEnties, tableHeaders);
		int[] htmlColumnsWidth = adjustWidthForHtmlData(tradeEnties, tableHeaders);

		String tableAlignFormat = getTableAlignFormat(columnsWidth);
		String tableRowsAlignFormat = getTableAlignFormat(htmlColumnsWidth);
		String lineSeperator = getLineSeperator(lineChar, cornerChar, columnsWidth);
		String headerAlignFormat = "| %-" + (lineSeperator.length() - 6) + "s |%n";
		result.append(
		String.format(lineSeperator.replace(cornerChar, lineChar).replaceFirst(lineChar, cornerChar).substring(0,
				lineSeperator.length() - 3) + cornerChar + "%n") );
		result.append(String.format(headerAlignFormat, header));
		result.append(String.format(lineSeperator));
		result.append(String.format(tableAlignFormat, tableHeaders));
		result.append(String.format(lineSeperator));

		for (TradeEntry tradeEnty : tradeEnties) {
			String fName = getToAddress(tradeEnty.getTraderName());
			result.append(String.format(tableRowsAlignFormat, 
					fName/*tradeEnty.getTraderName()*/,
					tradeEnty.getBuySellFlag() == 'B' ? "Outcoming" : "Incoming",
					String.format("%.2f", tradeEnty.getAmountOfTrade()),
					tradeEnty.getSettlementDate().equals(tradeEnty.getInstructionDate()) ? tradeEnty.getSettlementDate()
							: tradeEnty.getSettlementDate() + "*",
					tradeEnty.getInstructionDate(), tradeEnty.getCurrency(),
					String.format("%.2f", tradeEnty.getAgreedFx()), tradeEnty.getUnits(),
					String.format("%.2f", tradeEnty.getUnitPrice())));
		}
		result.append(String.format(lineSeperator));
		return result.toString();
	}

	private static int[] adjustWidthForData(List<TradeEntry> tradeEnties, String[] cHeaders) {
		int[] cWidths = new int[cHeaders.length];
		for (int n = 0; n < cHeaders.length; n++) {
			cWidths[n] = cHeaders[n].length();
		}
		for (TradeEntry trader : tradeEnties) {
			int[] tWidths = { trader.getTraderName().length(), 9,
					String.format("%.2f", trader.getAmountOfTrade()).length(), 14, 16, 8, 9, 6, 10 };
			for (int i = 0; i < tWidths.length; i++) {
				if (tWidths[i] > cWidths[i]) {
					cWidths[i] = tWidths[i];
				}
			}
		}
		return cWidths;
	}
	
	private static int[] adjustWidthForHtmlData(List<TradeEntry> tradeEnties, String[] cHeaders) {
		int[] cWidths = new int[cHeaders.length];
		for (int n = 0; n < cHeaders.length; n++) {
			cWidths[n] = cHeaders[n].length();
		}
		for (TradeEntry trader : tradeEnties) {
			String fName = getToAddress(trader.getTraderName());
			int[] tWidths = { fName.length(), 9,
					String.format("%.2f", trader.getAmountOfTrade()).length(), 14, 16, 8, 9, 6, 10 };
			for (int i = 0; i < tWidths.length; i++) {
				if (tWidths[i] > cWidths[i]) {
					cWidths[i] = tWidths[i];
				}
			}
		}
		return cWidths;
	}

	private static String getTableAlignFormat(int... columns) {
		String tableAlignFormat = "";
		for (int column : columns) {
			tableAlignFormat += "| %-" + column + "s ";
		}
		return tableAlignFormat + "|%n";
	}

	private static String getLineSeperator(String lineChar, String cornerChar, int... columns) {
		String seperator = cornerChar;
		for (int column : columns) {
			column = column + 2;
			for (int n = 0; n < column; n++) {
				seperator += lineChar;
			}
			seperator += cornerChar;
		}
		return seperator + "%n";
	}
	
	private static String getToAddress(String text){
		return "<label style='color:red'>"+text+"</label>";
	}

}
