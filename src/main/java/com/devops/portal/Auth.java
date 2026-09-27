package com.devops.portal;
import jakarta.servlet.http.*;import java.util.*;
public final class Auth{
 private Auth(){}
 public static Map<String,Object> user(HttpServletRequest r){Object u=r.getSession(false)==null?null:r.getSession(false).getAttribute("user");return u instanceof Map?(Map<String,Object>)u:null;}
 public static boolean admin(HttpServletRequest r){Map<String,Object> u=user(r);return u!=null&&"ADMIN".equals(u.get("role"));}
 public static void login(HttpServletRequest r,Map<String,Object> u){r.getSession(true).setAttribute("user",u);}
 public static void logout(HttpServletRequest r){HttpSession s=r.getSession(false);if(s!=null)s.invalidate();}
}
