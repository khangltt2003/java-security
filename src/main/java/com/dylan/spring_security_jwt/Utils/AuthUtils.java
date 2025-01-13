package com.dylan.spring_security_jwt.Utils;

import java.util.Random;

public class AuthUtils {
    public static String  generateVerificationCode(){
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return Integer.toString(code);
    }
}
