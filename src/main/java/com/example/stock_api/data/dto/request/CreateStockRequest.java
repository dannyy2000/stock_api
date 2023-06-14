package com.example.stock_api.data.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateStockRequest {
    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private String name;
    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private Double currentPrice;
    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private Timestamp creationDate;
    @NotNull(message = "field name cannot be null")
    @NotEmpty(message = "field name cannot be empty")
    private Timestamp lastUpdate;

}
