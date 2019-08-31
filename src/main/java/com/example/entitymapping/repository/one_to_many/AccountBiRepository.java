package com.example.entitymapping.repository.one_to_many;

import com.example.entitymapping.entity.one_to_many.bi.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountBiRepository extends JpaRepository<Account, Long> {
}
