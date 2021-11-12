package com.example.core.utils;

import java.security.MessageDigest;

public class MD5Utils {

    public final static String MD5(String s) {
        try {
            byte[] btInput = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            return Hex2Byte.byte2Hex(md);
        } catch (Exception e) {
            return null;
        }
    }

}
