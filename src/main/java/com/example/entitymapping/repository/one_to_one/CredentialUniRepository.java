package com.example.entitymapping.repository.one_to_one;

import com.example.entitymapping.entity.one_to_one.uni.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialUniRepository extends JpaRepository<Credential, Long> {
}
