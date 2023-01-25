package com.synergy.productService.entity;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rule")
@Where(clause = "deleted_at is null")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rule_id", nullable = false)
    private Long id;

    @Column(name = "rule_name")
    private String ruleName;
}
