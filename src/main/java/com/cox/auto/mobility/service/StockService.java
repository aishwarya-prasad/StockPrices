package com.cox.auto.mobility.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Service
public class StockService {

	private Map<String, List<String>> userWatchMap = new HashMap<>();

	/**
	 * @param userId
	 * @param stockName
	 * @return
	 */
	public String addUserStockWatch(String userId, String stockName) {

		if (userWatchMap.containsKey(userId)) {
			userWatchMap.get(userId).add(stockName);
		} else {
			List<String> watchList = new ArrayList<>();
			watchList.add(stockName);
			userWatchMap.put(userId, watchList);
		}

		return "Add stock name " + stockName;

	}

	/**
	 * @param userId
	 * @param stockName
	 * @return
	 */
	public String removUserWatch(String userId, String stockName) {

		if (userWatchMap.containsKey(userId)) {
			boolean remove = userWatchMap.get(userId).remove(stockName);
			if (remove)
				return "remove";
		}
		return "failed";

	}

	/**
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getUserStockWatchList(String userId) {

		if (userWatchMap.containsKey(userId)) {
			return userWatchMap.get(userId);
		}

		return Collections.EMPTY_LIST;
	}

	/**
	 * @param userId
	 * @return
	 */
	public Map<String, BigDecimal> getUserStockPrices(String userId) {

		Map<String, BigDecimal> stockPrices = new HashMap<>();

		try {
			List<String> userStockWatchList = this.getUserStockWatchList(userId);

			String[] abc = new String[userStockWatchList.size()];
			String[] symbols = userStockWatchList.toArray(abc);
			// String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "YHOO"};
			// Can also be done with explicit from, to and Interval parameters
			Map<String, Stock> stocks = YahooFinance.get(symbols, true);
			stocks.forEach((k, stock) -> {
				stockPrices.put(k, stock.getQuote().getPrice());
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return stockPrices;

	}

	/**
	 * 
	 */
	public void getStock() {

		try {
			Stock stock = YahooFinance.get("INTC");
			stock.print();

		} catch (Exception ex) {

		}

	}

	/**
	 * @param stockName
	 * @return
	 */
	public Map<String, String> getAvgPrice(String stockName) {

		Map<String, String> avgPrice = new HashMap<String, String>();

		try {

			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.YEAR, -1); // from 1 years ago

			Stock stockHistoricalData = YahooFinance.get(stockName, from, to, Interval.WEEKLY);
			List<HistoricalQuote> history = stockHistoricalData.getHistory();

			List<Double> weeklCloseList = history.stream().map(hist -> hist.getClose().doubleValue())
					.collect(Collectors.toList());

			Double totalSum = weeklCloseList.stream().collect(Collectors.summingDouble(Double::doubleValue));
			long count = weeklCloseList.stream().count();
			double yearAvg = totalSum / count;

			DecimalFormat df = new DecimalFormat("0.00");
			avgPrice.put(stockName, df.format(yearAvg));

			return avgPrice;

		} catch (Exception ex) {
			ex.printStackTrace();
			avgPrice.put("errorMsg", "Please provide valid stock name");
		}

		return avgPrice;
	}

}
