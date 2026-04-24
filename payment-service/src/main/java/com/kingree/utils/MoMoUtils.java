package com.kingree.utils;

import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class MoMoUtils {
    public static String HmacSHA256(String data, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKey);

        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));

        StringBuilder hex = new StringBuilder(2 * rawHmac.length);
        for (byte b : rawHmac) {
            String hexByte = Integer.toHexString(0xff & b);
            if (hexByte.length() == 1)
                hex.append('0');
            hex.append(hexByte);
        }

        return hex.toString();
    }
}
