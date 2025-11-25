package org.example.progresssoft_test.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "fx_deals")
public class FxDeal {
    
    @Id
    @Column(name = "deal_unique_id")
    @NotBlank(message = "Deal ID cannot be empty")
    private String dealUniqueId;
    
    @Column(name = "from_currency", length = 3, nullable = false)
    @NotBlank(message = "From currency code is mandatory")
    @Size(min = 3, max = 3, message = "Invalid currency code format")
    private String fromCurrency;
    
    @Column(name = "to_currency", length = 3, nullable = false)
    @NotBlank(message = "To currency code is mandatory")
    @Size(min = 3, max = 3, message = "Invalid currency code format")
    private String toCurrency;
    
    @Column(name = "deal_timestamp", nullable = false)
    @NotNull(message = "Deal timestamp cannot be null")
    private LocalDateTime dealTimestamp;
    
    @Column(name = "deal_amount", precision = 19, scale = 4, nullable = false)
    @NotNull(message = "Deal amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private BigDecimal dealAmount;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public FxDeal() {
    }
    
    public FxDeal(String dealUniqueId, String fromCurrency, String toCurrency,
                  LocalDateTime dealTimestamp, BigDecimal dealAmount) {
        this.dealUniqueId = dealUniqueId;
        this.fromCurrency = fromCurrency.toUpperCase(); // Ensure uppercase
        this.toCurrency = toCurrency.toUpperCase();
        this.dealTimestamp = dealTimestamp;
        this.dealAmount = dealAmount;
    }
    

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    

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
        this.fromCurrency = fromCurrency != null ? fromCurrency.toUpperCase() : null;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency != null ? toCurrency.toUpperCase() : null;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}