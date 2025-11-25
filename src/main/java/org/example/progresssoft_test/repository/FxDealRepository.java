package org.example.progresssoft_test.repository;

import org.example.progresssoft_test.entity.FxDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FxDealRepository extends JpaRepository<FxDeal, String> {
    
    boolean existsByDealUniqueId(String dealUniqueId);
}