package org.care.utils;

import org.care.context.MyApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

public class CommonUtil {

    public static String getRedirectURL(String url) {
        return MyApplicationContext.get().getAppURI() + url;
    }

    public static String getHashedPassword(String plainText) {
        String saltPrefix = "1@123$%^%$%$5";
        String saltSuffix = "^%%^*()$asd";
        String saltedPassword = saltPrefix + plainText + saltSuffix;
        try {
            return new String(
                    MessageDigest.getInstance("SHA-256").digest(saltedPassword.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(CommonUtil.class.getName()).severe("SHA256 alg not found in lib");
        }
        return null;
    }
}
