package com.service;

import com.entity.Code;
import java.util.List;
import java.util.Optional;

public interface CodeService {
    List<Code> getAll();
    void add(Code code);
    Optional<Code> getCodeById(Long codeId);
    Optional<Code> getCodeByUserId(Long userId);
    void removeCode(Long codeId);
}
