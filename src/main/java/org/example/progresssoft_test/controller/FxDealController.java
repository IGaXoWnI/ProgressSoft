package org.example.progresssoft_test.controller;

import org.example.progresssoft_test.dto.FxDealRequest;
import org.example.progresssoft_test.entity.FxDeal;
import org.example.progresssoft_test.service.FxDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fx-deals")
public class FxDealController {
    
    private final Logger log = LoggerFactory.getLogger(FxDealController.class);
    
    @Autowired
    private FxDealService fxDealService;
    
    @PostMapping("/import")
    public ResponseEntity<String> importDeal(@Valid @RequestBody FxDealRequest dealRequest) {
        try {
            log.info("Processing new FX deal with id: {}", dealRequest.getDealUniqueId());
            
            FxDeal deal = fxDealService.saveDeal(dealRequest);
            log.info("FX deal imported successfully: {}", deal.getDealUniqueId());
            return ResponseEntity.ok("Deal imported successfully with id: " + deal.getDealUniqueId());
            
        } catch (RuntimeException ex) {
            log.error("Failed to import deal {}: {}", dealRequest.getDealUniqueId(), ex.getMessage());
            return ResponseEntity.badRequest().body("Import failed: " + ex.getMessage());
        } catch (Exception ex) {
            log.error("Unexpected error while importing deal: {}", ex.getMessage());
            return ResponseEntity.internalServerError().body("System error occurred");
        }
    }
}