package com.example.entitymapping.repository.many_to_many;

import com.example.entitymapping.entity.many_to_many.uni.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
