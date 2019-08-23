package com.service.impl;

import com.entity.Code;
import com.repository.CodeRepository;
import com.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    private CodeRepository codeRepository;

    @Autowired
    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Transactional
    @Override
    public List<Code> getAll() {
        return codeRepository.findAll();
    }

    @Transactional
    @Override
    public void add(Code code) {
        codeRepository.save(code);
    }

    @Transactional
    @Override
    public Optional<Code> getCodeById(Long codeId) {
        return codeRepository.findById(codeId);
    }

    @Transactional
    @Override
    public Optional<Code> getCodeByUserId(Long userId) {
        return codeRepository.getCodeByUserId(userId);
    }

    @Transactional
    @Override
    public void removeCode(Long codeId) {
        codeRepository.deleteById(codeId);
    }
}
