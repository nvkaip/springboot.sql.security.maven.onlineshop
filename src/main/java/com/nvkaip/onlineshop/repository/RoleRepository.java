package com.nvkaip.onlineshop.repository;

import com.nvkaip.onlineshop.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByName(String roleName);
}
