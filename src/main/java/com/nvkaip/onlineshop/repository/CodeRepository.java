package com.repository;

import com.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> getCodeByUserId(Long userId);
}
