package com.example.stock_api.services;

import com.example.stock_api.data.dto.request.CreateStockRequest;
import com.example.stock_api.data.dto.request.StockUpdateRequest;
import com.example.stock_api.data.dto.response.CreateStockResponse;
import com.example.stock_api.data.dto.response.GetStockResponse;
import com.example.stock_api.data.dto.response.UpdateStockResponse;
import com.example.stock_api.exceptions.StockNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceImplTest {
    @Autowired
    private StockService stockService;
    private CreateStockRequest createStockRequest;



    @BeforeEach
    void setUp() {
       createStockRequest = new CreateStockRequest();
       createStockRequest.setName("Unknown");
       createStockRequest.setCurrentPrice(70.00);

        LocalDateTime now = LocalDateTime.now();
        Timestamp creationDate = Timestamp.valueOf(now);
        createStockRequest.setCreationDate(creationDate);

        LocalDateTime lastUpdated = now.minusDays(1L);
        Timestamp lastUpdate = Timestamp.valueOf(lastUpdated);
        createStockRequest.setLastUpdate(lastUpdate);

    }

    @Test
    void createStockTest(){
        CreateStockResponse createStockResponse = stockService.createStock(createStockRequest);
        assertThat(createStockResponse).isNotNull();
        System.out.println(createStockResponse.getMessage());
    }

    @Test
    void getStockByIdTest(){
        CreateStockResponse response = stockService.createStock(createStockRequest);
        GetStockResponse getStockResponse = stockService.getStockById(response.getId());
        assertThat(getStockResponse).isNotNull();
        assertThat(getStockResponse.getName()).isEqualTo(createStockRequest.getName());
    }

    @Test
    void updateStockTest(){
        CreateStockResponse response = stockService.createStock(createStockRequest);
        StockUpdateRequest stockUpdateRequest = new StockUpdateRequest();
        stockUpdateRequest.setName("ghsdhd");
        stockUpdateRequest.setCurrentPrice(90.00);
        UpdateStockResponse updateStockResponse = stockService.updateStock(response.getId(),stockUpdateRequest);
        assertThat(updateStockResponse).isNotNull();
        assertThat(updateStockResponse.getId()).isEqualTo(response.getId());
    }

    @Test
    void getAllStocks(){
        CreateStockResponse response = stockService.createStock(createStockRequest);
        int pageNumber = 1;
        Page<GetStockResponse> getStockResponse = stockService.getAllStocks(pageNumber);
        assertThat(getStockResponse).isNotNull();
    }


    @Test
     void deleteStockTest(){
        CreateStockResponse response = stockService.createStock(createStockRequest);
        stockService.deleteStock(response.getId());
        assertThrows(StockNotFoundException.class, ()-> stockService.getStockById(response.getId()));
    }

}