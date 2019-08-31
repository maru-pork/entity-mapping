package com.example.entitymapping.repository.one_to_one;

import com.example.entitymapping.entity.one_to_one.bi.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBiRepository extends JpaRepository<User, Long> {
}
