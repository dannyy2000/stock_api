package com.example.stock_api.controllers;

import com.example.stock_api.data.dto.request.CreateStockRequest;
import com.example.stock_api.data.dto.request.StockUpdateRequest;
import com.example.stock_api.data.dto.response.CreateStockResponse;
import com.example.stock_api.data.dto.response.GetStockResponse;
import com.example.stock_api.data.dto.response.UpdateStockResponse;
import com.example.stock_api.services.StockService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stocks")
@AllArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/create")
    public ResponseEntity<?> createStock(@RequestBody CreateStockRequest stockRequest){
        CreateStockResponse stockResponse = stockService.createStock(stockRequest);
        return new ResponseEntity<>(stockResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{stockId}")
    public ResponseEntity<?> getStockById(@PathVariable Long stockId){
        GetStockResponse getStockResponse = stockService.getStockById(stockId);
        return ResponseEntity.status(HttpStatus.OK).body(getStockResponse);
    }

    @GetMapping(value = "/all/{pageNumber}")
    public ResponseEntity <?> getAllPatient(@PathVariable int pageNumber){
        Page<GetStockResponse>response = stockService.getAllStocks(pageNumber);
        return ResponseEntity.ok(response.getContent());
    }

    @PutMapping("/{stockId}")
    public ResponseEntity<?>updateStock(@PathVariable Long stockId, @RequestBody StockUpdateRequest stockUpdateRequest){
        UpdateStockResponse updateStockResponse = stockService.updateStock(stockId, stockUpdateRequest);
        return new ResponseEntity<>(updateStockResponse,HttpStatus.CREATED);
    }

    @DeleteMapping("/{stockId}")
    public ResponseEntity<?>deleteStock(@PathVariable Long stockId){
        stockService.deleteStock(stockId);
        return ResponseEntity.ok(String.format("Stock with id %d deleted",stockId));
    }
}
