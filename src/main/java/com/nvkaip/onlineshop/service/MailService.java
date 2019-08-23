package com.nvkaip.onlineshop.service;

import com.nvkaip.onlineshop.entity.Code;

public interface MailService {
    void sendConfirmCode(Code code);
}
