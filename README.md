# stock_api

The Stock API is a Java-based application . It provides functionality to create stocks, update,retrieve stock information and delete stock.

Features Create a new stock with details such as name, current price, and creationDate,lastUpdate. Update the details of an existing stock. Retrieve stock information by ID. Delete stock information by Id. Get a paginated list of all stocks.

Technologies Used:
Java 
Spring Boot 
Spring Data JPA 
MySQL 
Maven

Creating a Stock To create a new stock;
send a POST request to "api/v1/stocks/create" with the following payload:
name (String): The name of the stock. currentPrice (Double): The current price of the stock. creationDate(TimeStamp):The date the stock was created. lastUpdate(TimeStamp):The date the stock was last updated

Updating a Stock To update the details of a stock: 
send a PUT request to "api/v1/stocks/{stockId}" with the following parameters:
stockId (Long): The ID of the stock to update. name (String): The updated name of the stock. currentPrice (Double): The updated current price of the stock.

Retrieving Stock Information To retrieve the information of a specific stock:
send a GET request to "api/v1/stocks/{stockId}" with the following parameter:
stockId (Long): The ID of the stock to retrieve.

Getting All Stocks To get a paginated list of all stocks:
send a GET request to "api/v1/stocks/all/{pageNumber}" with the following parameter:
page (Integer): The page number to retrieve.
Delete Stock Information To delete information of a specific stock, send a delete request to "api/v1/stocks/{stockId}" with the Id of the stock to delete

Testing
I have written comprehensive tests to ensure the functionality and reliability of the endpoints.  The testing framework used for this project is JUnit.
