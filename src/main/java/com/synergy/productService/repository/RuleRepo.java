package com.synergy.productService.repository;

import com.synergy.productService.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepo extends JpaRepository<Rule, Long> {
    List<Rule> findByIdIn(String[] id);

}
