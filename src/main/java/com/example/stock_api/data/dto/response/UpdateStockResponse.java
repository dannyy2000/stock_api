package com.example.stock_api.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStockResponse {
    private Long id;
    private String message;
    private boolean isSuccessful;
}
