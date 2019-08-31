package com.example.entitymapping.repository.one_to_many;

import com.example.entitymapping.entity.one_to_many.uni.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountUniRepository extends JpaRepository<Account, Long> {
}
