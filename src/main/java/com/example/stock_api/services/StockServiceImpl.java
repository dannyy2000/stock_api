package com.example.stock_api.services;

import com.example.stock_api.data.dto.request.CreateStockRequest;
import com.example.stock_api.data.dto.request.StockUpdateRequest;
import com.example.stock_api.data.dto.response.CreateStockResponse;
import com.example.stock_api.data.dto.response.GetStockResponse;
import com.example.stock_api.data.dto.response.UpdateStockResponse;
import com.example.stock_api.data.models.Stock;
import com.example.stock_api.data.repositories.StockRepository;
import com.example.stock_api.exceptions.StockNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.stock_api.AppUtilities.NUMBER_OF_ITEMS_PER_PAGE;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockServiceImpl implements StockService{
    private  final ModelMapper mapper;
    private final StockRepository stockRepository;

    @Override
    public CreateStockResponse createStock(CreateStockRequest stockRequest) {
        log.info("Request to create a stock with payload={}",stockRequest);
        Stock stock = mapper.map(stockRequest, Stock.class);
        Stock savedStock = stockRepository.save(stock);
        CreateStockResponse stockResponse = getStockResponse(savedStock);
        return stockResponse;
    }

    @Override
    public GetStockResponse getStockById(Long stockId) {{
            Stock stock = stockRepository.findById(stockId)
                    .orElseThrow(() -> new StockNotFoundException("Stock not found with ID: " + stockId));

            return mapToGetStockResponse(stock);
        }

    }

    @Override
    public Page<GetStockResponse> getAllStocks(int pageNumber) {
            if (pageNumber < 1) {
                pageNumber = 0;
            } else {
                pageNumber = pageNumber - 1;
            }
            Pageable pageable = PageRequest.of(pageNumber, NUMBER_OF_ITEMS_PER_PAGE);
            Page<Stock> stockPage = stockRepository.findAll(pageable);
            return stockPage.map(this::convertToDTO);
        }

    @Override
    public UpdateStockResponse updateStock(Long stockId, StockUpdateRequest stockUpdateRequest) {
        Optional<Stock> stock = stockRepository.findById(stockId);
        if(stock.isEmpty())throw new StockNotFoundException("Stock not found with Id: " + stockId);
        Stock savedStock = stock.get();
        savedStock.setName(stockUpdateRequest.getName());
        savedStock.setCurrentPrice(stockUpdateRequest.getCurrentPrice());
        stockRepository.save(savedStock);
        UpdateStockResponse updatedStockResponse = getUpdatedStockResponse(savedStock);
        return updatedStockResponse;
    }

    @Override
    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }

    private UpdateStockResponse getUpdatedStockResponse(Stock savedStock) {
        UpdateStockResponse updateStockResponse = new UpdateStockResponse();
        updateStockResponse.setId(savedStock.getId());
        updateStockResponse.setSuccessful(true);
        updateStockResponse.setMessage("Update success");
        return updateStockResponse;
    }


    private GetStockResponse mapToGetStockResponse(Stock stock) {
        GetStockResponse getStockResponse = new GetStockResponse();
        getStockResponse.setId(stock.getId());
        getStockResponse.setName(stock.getName());
        getStockResponse.setCurrentPrice(stock.getCurrentPrice());
        getStockResponse.setCreationDate(stock.getCreationDate());
        getStockResponse.setLastUpdate(stock.getLastUpdate());
        return getStockResponse;
    }


    private CreateStockResponse getStockResponse(Stock savedStock ) {
        CreateStockResponse stockResponse = new CreateStockResponse();
        stockResponse.setId(savedStock.getId());
        stockResponse.setSuccessful(true);
        stockResponse.setMessage("Stocks Created Successfully");
        return stockResponse;
    }

    private GetStockResponse convertToDTO(Stock stock  ) {
        GetStockResponse getStockResponse = new GetStockResponse();
        getStockResponse.setId(stock.getId());
        getStockResponse.setName(stock.getName());
        getStockResponse.setCurrentPrice(stock.getCurrentPrice());
        getStockResponse.setLastUpdate(stock.getLastUpdate());
        getStockResponse.setCreationDate(stock.getCreationDate());
        return getStockResponse;
    }
}
