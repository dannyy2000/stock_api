package com.example.stock_api.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStockResponse {

    private Long id;
    private String name;
    private Double currentPrice;
    private LocalDate creationDate;
    private LocalDate lastUpdate;

}
