package com.service;

import com.entity.Code;

public interface MailService {
    void sendConfirmCode(Code code);
}
