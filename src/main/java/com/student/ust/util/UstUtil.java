package com.student.ust.util;

import com.student.ust.exception.InvalidEmail;
import com.student.ust.exception.InvalidPassword;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Ust util.
 */
public class UstUtil {
    /**
     * Email validate int.
     *
     * @param email the email
     * @return the int
     */
    public static int emailValidate(String email) {
        String regex="^([A-Za-z0-9+_.-]+)@([A-Za-z0-9]+)\\.([a-z]+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return 0;
        }
        else {
            throw new InvalidEmail();
        }
    }

    /**
     * Password validate int.
     *
     * @param password the password
     * @return the int
     */
    public static int passwordValidate(String password) {
        String regex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return 0;
        }
        else {
            throw new InvalidPassword();
        }
    }

    public static String hashPassword(String password){
        try{
            return toHexString(getSHA(password));
        }
        catch (NoSuchAlgorithmException e){
            throw new InvalidPassword();
        }
    }

    private static byte[] getSHA(String password) throws NoSuchAlgorithmException{
        MessageDigest md= MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash){
        BigInteger number=new BigInteger(1,hash);
        StringBuilder hexString=new StringBuilder(number.toString(16));
        while (hexString.length() < 64){
            hexString.insert(0,'0');
        }

        return hexString.toString();
    }
}
