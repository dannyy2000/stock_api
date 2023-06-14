package com.example.stock_api.data.repositories;

import com.example.stock_api.data.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock,Long> {

}
