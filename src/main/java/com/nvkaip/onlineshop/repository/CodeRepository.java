package com.nvkaip.onlineshop.repository;

import com.nvkaip.onlineshop.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> getCodeByUserId(Long userId);
}
