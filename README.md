# StockPrices
Stock RestApi 
**Build with**

 - Java 1.8 
 - Spring Boot v2.6.1 
 - JUnit 
 - Eclipse 

**Getting Started**
- Clone the project from Git 
- https://github.com/aishwarya-prasad/StockPrices.git
- Packaging > mvn clean package –DskipTests=true  
- Running > java –jar  ./target/xxx.jar 

**Rest endpoints info**

**Add user stock to  watch list**

GET 	localhost:8080/stockapi/add/user-1/INTC 

GET	localhost:8080/stockapi/add/user-1/BABA 

GET	localhost:8080/stockapi/add/user-2/AIR.PA 

**Get all user watch list** 

GET 	localhost:8080/stockapi/getstocks/user-1 

**Delete stock for a user**

DELETE  ::   localhost:8080/stockapi/delete/user-1/AIR.PA 

**Get user watched stock prices**

GET   :: localhost:8080/stockapi/getPrices/user-1 

**Avg price of stock in a year**

localhost:8080/stockapi/avgprice/INTC 

 

 

	 

	 

 

 

 

 

 

 
