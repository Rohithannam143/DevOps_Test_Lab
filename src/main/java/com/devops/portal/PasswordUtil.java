package com.devops.portal;
import java.security.*;import java.util.*;import javax.crypto.SecretKeyFactory;import javax.crypto.spec.PBEKeySpec;
public final class PasswordUtil{
 private PasswordUtil(){}
 public static String hash(String password){try{byte[] salt=new byte[16];new SecureRandom().nextBytes(salt);byte[] out=derive(password.toCharArray(),salt,120000,32);return "pbkdf2$120000$"+Base64.getEncoder().encodeToString(salt)+"$"+Base64.getEncoder().encodeToString(out);}catch(Exception e){throw new IllegalStateException(e);}}
 public static boolean verify(String password,String encoded){try{String[] p=encoded.split("\\$");if(p.length!=4)return false;int iter=Integer.parseInt(p[1]);byte[] salt=Base64.getDecoder().decode(p[2]);byte[] expected=Base64.getDecoder().decode(p[3]);return MessageDigest.isEqual(expected,derive(password.toCharArray(),salt,iter,expected.length));}catch(Exception e){return false;}}
 private static byte[] derive(char[] pass,byte[] salt,int iter,int len)throws Exception{return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(new PBEKeySpec(pass,salt,iter,len*8)).getEncoded();}
}
