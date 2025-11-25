package org.example.progresssoft_test.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FxDealRequest {
    
    @NotBlank(message = "Deal ID is required")
    private String dealUniqueId;
    
    @NotBlank(message = "From currency is required")
    @Size(min = 3, max = 3, message = "Currency must be 3 characters")
    private String fromCurrency;
    
    @NotBlank(message = "To currency is required") 
    @Size(min = 3, max = 3, message = "Currency must be 3 characters")
    private String toCurrency;
    
    @NotNull(message = "Deal timestamp is required")
    private LocalDateTime dealTimestamp;
    
    @NotNull(message = "Deal amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
    private BigDecimal dealAmount;
    
    public FxDealRequest() {}
    
    public String getDealUniqueId() {
        return dealUniqueId;
    }
    
    public void setDealUniqueId(String dealUniqueId) {
        this.dealUniqueId = dealUniqueId;
    }
    
    public String getFromCurrency() {
        return fromCurrency;
    }
    
    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }
    
    public String getToCurrency() {
        return toCurrency;
    }
    
    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }
    
    public LocalDateTime getDealTimestamp() {
        return dealTimestamp;
    }
    
    public void setDealTimestamp(LocalDateTime dealTimestamp) {
        this.dealTimestamp = dealTimestamp;
    }
    
    public BigDecimal getDealAmount() {
        return dealAmount;
    }
    
    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }
}