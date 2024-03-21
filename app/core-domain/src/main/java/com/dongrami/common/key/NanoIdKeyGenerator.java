package com.dongrami.common.key;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

public class NanoIdKeyGenerator implements KeyGenerator {

    @Override
    public String generateKey() {
        return NanoIdUtils.randomNanoId();
    }

}
