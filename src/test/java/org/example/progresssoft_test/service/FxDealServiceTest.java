package org.example.progresssoft_test.service;

import org.example.progresssoft_test.dto.FxDealRequest;
import org.example.progresssoft_test.entity.FxDeal;
import org.example.progresssoft_test.repository.FxDealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FxDealServiceTest {

    @Mock
    private FxDealRepository repository;

    @InjectMocks
    private FxDealService service;

    private FxDealRequest validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new FxDealRequest();
        validRequest.setDealUniqueId("DEAL001");
        validRequest.setFromCurrency("USD");
        validRequest.setToCurrency("EUR");
        validRequest.setDealTimestamp(LocalDateTime.of(2024, 1, 15, 10, 30));
        validRequest.setDealAmount(BigDecimal.valueOf(1000.50));
    }

    @Test
    void saveDeal_Success() {
        when(repository.existsByDealUniqueId(anyString())).thenReturn(false);
        when(repository.save(any(FxDeal.class))).thenAnswer(invocation -> invocation.getArgument(0));

        FxDeal result = service.saveDeal(validRequest);

        assertNotNull(result);
        assertEquals("DEAL001", result.getDealUniqueId());
        assertEquals("USD", result.getFromCurrency());
        assertEquals("EUR", result.getToCurrency());
        verify(repository, times(1)).existsByDealUniqueId("DEAL001");
        verify(repository, times(1)).save(any(FxDeal.class));
    }

    @Test
    void saveDeal_DuplicateThrowsException() {
        when(repository.existsByDealUniqueId("DEAL001")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.saveDeal(validRequest);
        });

        assertTrue(exception.getMessage().contains("already exists"));
        verify(repository, times(1)).existsByDealUniqueId("DEAL001");
        verify(repository, never()).save(any(FxDeal.class));
    }

    @Test
    void saveDeal_RepositoryFailureThrowsException() {
        when(repository.existsByDealUniqueId(anyString())).thenReturn(false);
        when(repository.save(any(FxDeal.class))).thenThrow(new RuntimeException("DB error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.saveDeal(validRequest);
        });

        assertNotNull(exception);
        verify(repository, times(1)).save(any(FxDeal.class));
    }
}