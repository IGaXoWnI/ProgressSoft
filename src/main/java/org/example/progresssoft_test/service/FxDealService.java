package org.example.progresssoft_test.service;

import org.example.progresssoft_test.dto.FxDealRequest;
import org.example.progresssoft_test.entity.FxDeal;
import org.example.progresssoft_test.repository.FxDealRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FxDealService {
    
    private static final Logger log = LoggerFactory.getLogger(FxDealService.class);
    
    @Autowired
    private FxDealRepository repository;
    
    public FxDeal saveDeal(FxDealRequest request) {
        log.info("Processing deal: {}", request.getDealUniqueId());
        
        if (repository.existsByDealUniqueId(request.getDealUniqueId())) {
            log.warn("Duplicate deal detected: {}", request.getDealUniqueId());
            throw new RuntimeException("Deal already exists: " + request.getDealUniqueId());
        }
        
        FxDeal deal = new FxDeal(
            request.getDealUniqueId(),
            request.getFromCurrency(),
            request.getToCurrency(),
            request.getDealTimestamp(),
            request.getDealAmount()
        );
        
        FxDeal saved = repository.save(deal);
        log.info("Deal saved successfully: {}", saved.getDealUniqueId());
        return saved;
    }
}