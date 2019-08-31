package com.example.entitymapping.repository.one_to_many;

import com.example.entitymapping.entity.one_to_many.join.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetJoinRepository extends JpaRepository<Budget, Long> {
}
