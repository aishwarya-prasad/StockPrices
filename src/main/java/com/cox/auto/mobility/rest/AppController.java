package com.cox.auto.mobility.rest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cox.auto.mobility.service.StockService;

@RestController
@RequestMapping("/stockapi")
public class AppController {
	
	
	@Autowired
	StockService stockService;

	@RequestMapping
	public String app() {
		return "MicroService Running...";
		
	}
	
	
	/**
	 * Method to add stock 
	 * @param userid
	 * @param stockName
	 * @return
	 */
	@GetMapping("add/{userid}/{stockename}")
	public String addStock(@PathVariable("userid") String userid,@PathVariable("stockename") String stockName) {
		
		stockService.addUserStockWatch(userid, stockName);
		
		return "Success";
		
	}
	
	
	/**
	 * Method to get watched user stock
	 * @param userid
	 * @return
	 */
	@GetMapping("getstocks/{userid}")
	public List<String> getsTocks(@PathVariable("userid") String userid) {
		
	 return	stockService.getUserStockWatchList(userid);
		
	}
	
	
	/**
	 * Method to delete stocks
	 * @param userid
	 * @return
	 */
	@DeleteMapping("delete/{userid}/{stockename}")
	public String deletewatachStock(@PathVariable("userid") String userid,@PathVariable("stockename") String stockName) {

		return stockService.removUserWatch(userid, stockName);
	}
	

	
	/**
	 * Method for watched stock prices
	 * @param userid
	 * @return
	 */
	@GetMapping("getPrices/{userid}")
	public Map<String,BigDecimal> getUserStockPrices(@PathVariable("userid") String userid) {
		
		Map<String, BigDecimal> userStockPrices = stockService.getUserStockPrices(userid);
		
		return userStockPrices;
		
	}
	
	
	
	/**
	 * Method for Average price of stocks
	 * @param stockName
	 * @return
	 */
	@GetMapping("avgprice/{stockname}")
	public Map<String, String> getAveragePriceOfSTock(@PathVariable("stockname") String stockName) {
		
		 Map<String, String> yearAvgPrice = stockService.getAvgPrice(stockName);
		
		return yearAvgPrice;
	}
	

	
}
