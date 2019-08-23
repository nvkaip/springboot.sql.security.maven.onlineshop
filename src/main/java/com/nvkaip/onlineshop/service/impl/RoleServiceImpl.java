package com.service.impl;

import com.entity.Role;
import com.repository.RoleRepository;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public Optional<Role> getRoleById(Long roleId) {
        return roleRepository.findById(roleId);
    }

    @Transactional
    @Override
    public Optional<Role> getRoleByName(String roleName) {
        return roleRepository.getRoleByName(roleName);
    }

    @Transactional
    @Override
    public void removeRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
