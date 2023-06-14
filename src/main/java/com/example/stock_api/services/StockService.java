package com.example.stock_api.services;

import com.example.stock_api.data.dto.request.CreateStockRequest;
import com.example.stock_api.data.dto.request.StockUpdateRequest;
import com.example.stock_api.data.dto.response.CreateStockResponse;
import com.example.stock_api.data.dto.response.GetStockResponse;
import com.example.stock_api.data.dto.response.UpdateStockResponse;
import org.springframework.data.domain.Page;

public interface StockService {

    CreateStockResponse createStock(CreateStockRequest stockRequest);
    GetStockResponse getStockById(Long stockId);

    Page <GetStockResponse> getAllStocks(int pageNumber);

    UpdateStockResponse updateStock(Long stockId, StockUpdateRequest stockUpdateRequest);

    void deleteStock(Long stockId);
}
