package com.service;

import com.entity.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAll();
    void addRole(Role role);
    Optional<Role> getRoleById(Long roleId);
    Optional<Role> getRoleByName(String roleName);
    void removeRole(Long roleId);
}
