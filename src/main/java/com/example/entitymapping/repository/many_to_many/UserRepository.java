package com.example.entitymapping.repository.many_to_many;

import com.example.entitymapping.entity.many_to_many.bi.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
