package com.nvkaip.onlineshop.service.impl;

import com.nvkaip.onlineshop.entity.Code;
import com.nvkaip.onlineshop.repository.CodeRepository;
import com.nvkaip.onlineshop.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CodeServiceImpl implements CodeService {

    private CodeRepository codeRepository;

    @Autowired
    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public List<Code> getAll() {
        return codeRepository.findAll();
    }

    @Override
    public void add(Code code) {
        codeRepository.save(code);
    }

    @Override
    public Optional<Code> getCodeById(Long codeId) {
        return codeRepository.findById(codeId);
    }

    @Override
    public Optional<Code> getCodeByUserId(Long userId) {
        return codeRepository.getCodeByUserId(userId);
    }

    @Override
    public void removeCode(Long codeId) {
        codeRepository.deleteById(codeId);
    }
}
