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
	
	static Boolean[] toggleFlages = {true, true, true, true, true, true, true, true, true};

	public static Comparator<TradeEntry> getSortingComparator(String sortingOption) {
		Comparator<TradeEntry> sortingComparator = null;
		if(sortingOption == null){
			sortingOption = "Amount";
		}
		switch (sortingOption) {
		case "Entity":
			sortingComparator = (trader1, trader2) -> {
				int result = toggleFlages[0] ? trader2.getTraderName().compareTo(trader1.getTraderName()) : trader1.getTraderName().compareTo(trader2.getTraderName());
				return result;
				};
				toggleFlages[0] = !toggleFlages[0];
			break;
		case "In/Out":
			sortingComparator = (trader1, trader2) -> {
				int result = toggleFlages[1] ? trader2.getBuySellFlag().compareTo(trader1.getBuySellFlag()) : trader1.getBuySellFlag().compareTo(trader2.getBuySellFlag());
				return result;
				};
				toggleFlages[1] = !toggleFlages[1];
			break;
		case "Settlement Date":
			sortingComparator = (trader1, trader2) -> {
				if(toggleFlages[2]){
					TradeEntry temp = trader1;
					trader1 = trader2;
					trader2 = temp;
				}
				String pattern = "dd MMM yyyy";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
				LocalDate trader1Date = LocalDate.parse(trader1.getSettlementDate(), formatter);
				LocalDate trader2Date = LocalDate.parse(trader2.getSettlementDate(), formatter);
				return trader2Date.compareTo(trader1Date);
				};
				toggleFlages[2] = !toggleFlages[2];
			break;
		case "Instruction Date":
			sortingComparator = (trader1, trader2) -> {
				if(toggleFlages[3]){
					TradeEntry temp = trader1;
					trader1 = trader2;
					trader2 = temp;
				}
				String pattern = "dd MMM yyyy";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
				LocalDate trader1Date = LocalDate.parse(trader1.getInstructionDate(), formatter);
				LocalDate trader2Date = LocalDate.parse(trader2.getInstructionDate(), formatter);
				return trader2Date.compareTo(trader1Date);
				};
				toggleFlages[3] = !toggleFlages[3];
			break;
		case "Currency":
			sortingComparator = (trader1, trader2) -> {
				int result = toggleFlages[4] ? trader2.getCurrency().compareTo(trader1.getCurrency()) : trader1.getCurrency().compareTo(trader2.getCurrency());
				return result;
				};
				toggleFlages[4] = !toggleFlages[4];
			break;
		case "Agreed Fx":
			sortingComparator = (trader1, trader2) -> {
				int result = toggleFlages[5] ? trader2.getAgreedFx().compareTo(trader1.getAgreedFx()) : trader1.getAgreedFx().compareTo(trader2.getAgreedFx());
				return result;
				};
				toggleFlages[5] = !toggleFlages[5];
			break;
		case "Units":
			sortingComparator = (trader1, trader2) -> {
				int result = toggleFlages[6] ? trader2.getUnits().compareTo(trader1.getUnits()) : trader1.getUnits().compareTo(trader2.getUnits());
				return result;
				};
				toggleFlages[6] = !toggleFlages[6];
			break;
		case "Unit Price":
			sortingComparator = (trader1, trader2) -> {
				int result = toggleFlages[7] ? trader2.getUnitPrice().compareTo(trader1.getUnitPrice()) : trader1.getUnitPrice().compareTo(trader2.getUnitPrice());
				return result;
				};
				toggleFlages[7] = !toggleFlages[7];
			break;
		default:
			sortingComparator = (trader1, trader2) -> {
				int result = toggleFlages[8] ? trader2.getAmountOfTrade().compareTo(trader1.getAmountOfTrade()) : trader1.getAmountOfTrade().compareTo(trader2.getAmountOfTrade());
				return result;
				};
				toggleFlages[8] = !toggleFlages[8];
		}
		return sortingComparator;
	}
	
	public static String runTradeReporting(String sortingOption){
		StringBuffer result = new StringBuffer();
		result.append(printSortedReport(getSortingComparator(sortingOption)));
		return result.toString();
	}
	
	public static String runTradeFilteredReporting(String filter){
		StringBuffer result = new StringBuffer();
		result.append(printFilteredReport(filter));
		return result.toString();
	}
	
	private static String printSortedReport(Comparator<TradeEntry> sortOrder) {
		sortTrades(sortOrder); 
		StringBuffer result = new StringBuffer();
		result.append(printReport(DataLoadingUtility.tradeEntries, "All Buy/Sell Traders (Outgoing/Incoming)"));
		return result.toString();
	}
	
	private static String printFilteredReport(String filter) {
		StringBuffer result = new StringBuffer();
		result.append(printReport(DataLoadingUtility.tradeEntries.stream().filter(trader -> trader.getTraderName().equals(filter))
				.collect(Collectors.toList()), "Look up result for: ***"+filter+"***"));
		return result.toString();
	}

	public static void sortTrades(Comparator<TradeEntry> sortOrder) {
		Collections.sort(DataLoadingUtility.tradeEntries, sortOrder);
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
		return addHtmlTagToTableHeaders(tableHeaders, result.toString());
//		return result.toString();
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
	
	private static String addHtmlTagToTableHeaders(String[] tableHeaders, String table){
		for(String header : tableHeaders){
			table = table.replace(header, addHtmlTag(header, "label name='header' style='color:white'"));
		}
		return table;
	}
	
	private static String addHtmlTag(String text, String tagNameAndAttribute){
		return "<"+tagNameAndAttribute+">"+text+"</"+tagNameAndAttribute.split(" ")[0]+">";
	}


}
