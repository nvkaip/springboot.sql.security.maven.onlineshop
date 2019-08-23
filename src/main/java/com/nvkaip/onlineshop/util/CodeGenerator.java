package com.nvkaip.onlineshop.util;

import org.apache.commons.lang.RandomStringUtils;

public interface CodeGenerator {

    static String getValidationCode(){
        return RandomStringUtils.randomAlphanumeric(4);
    }
}
