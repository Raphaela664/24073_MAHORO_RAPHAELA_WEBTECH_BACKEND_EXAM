package com.aua.fexam_backend.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static  String generatePassword(){
        String characters = "ABC123dyhkzmsop";
        StringBuilder password = new StringBuilder();
        int length = 8;
        for (int i=0; i<length; i++){
            int index = (int)(Math.random() * characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    public  static String encryptPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public  static  boolean CheckPassword(String passwordGiven, String passwordStored){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(passwordGiven,passwordStored);
    }

}
