package com.coocaa.myspring.util.crypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mr giraffe on 16/7/4.
 */
public class CipherUtil {
    public CipherUtil() {
    }

    public static String md5(String plain) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException var8) {
            ;
        }

        assert md != null;

        md.update(plain.getBytes());
        StringBuilder sb = new StringBuilder();
        byte[] var3 = md.digest();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            int i = b & 255;
            if(i < 16) {
                sb.append("0");
            }

            sb.append(Integer.toHexString(i));
        }

        return sb.toString();
    }
}
