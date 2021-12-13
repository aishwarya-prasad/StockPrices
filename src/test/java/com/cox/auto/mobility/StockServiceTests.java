package com.cox.auto.mobility;

import java.math.BigDecimal;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cox.auto.mobility.service.StockService;

public class StockServiceTests extends MobilityApplicationTests {
	
	@Autowired
	StockService stockService;
	
	
	@Test
	public void testAPI() {
		stockService.getStock();
	}
	
	
	//@Test
	public void addUser() {
		stockService.addUserStockWatch("user-1", "INTC");
		stockService.addUserStockWatch("user-1", "BABA");
		stockService.addUserStockWatch("user-1", "AIR.PA");
		
	//	stockService.addUserStockWatch("user-1", "stock-2");
		stockService.addUserStockWatch("user-2", "stock-3");
		
		stockService.removUserWatch("user-1", "stock-1");
		
		stockService.getUserStockWatchList("user-1").forEach(System.out::println);
		
		stockService.getUserStockPrices("user-1").forEach((k,v) ->{
			System.out.println(k + "  -- "+ v);
		});;
		
	}
	
	
        @Test
		public void getAvgPrice() {
        	
        	Map<String, String> avgPrice = stockService.getAvgPrice("INTC");
            System.out.println(avgPrice);
		
		}


}
