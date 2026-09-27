package com.devops.portal;
import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.*;import java.util.*;
@WebServlet("/api/feedback/*") public class FeedbackServlet extends HttpServlet{
 protected void doGet(HttpServletRequest r,HttpServletResponse p)throws IOException{
  try{Map<String,Object> u=Auth.user(r);String path=r.getPathInfo();
   if("/mine".equals(path)){if(u==null){p.setStatus(401);return;}send(p,Json.list(Database.feedback(((Number)u.get("id")).longValue(),false)));return;}
   if("/stats".equals(path)){if(!Auth.admin(r)){p.setStatus(403);return;}send(p,statsJson(Database.stats()));return;}
   if(!Auth.admin(r)){p.setStatus(403);return;}send(p,Json.list(Database.feedback(null,true)));
  }catch(Exception e){p.setStatus(500);send(p,"{\"error\":\"Database unavailable\"}");}
 }
 protected void doPost(HttpServletRequest r,HttpServletResponse p)throws IOException{
  try{Map<String,Object> u=Auth.user(r);
   String c=t(r.getParameter("category")),title=t(r.getParameter("title")),msg=t(r.getParameter("message"));int rating=Integer.parseInt(r.getParameter("rating"));
   if(c.length()<2||title.length()<3||msg.length()<3||msg.length()>2000||rating<1||rating>5){p.setStatus(400);send(p,"{\"error\":\"Please complete all feedback fields correctly.\"}");return;}
   boolean anon="true".equals(r.getParameter("anonymous"));Long uid=u==null?null:((Number)u.get("id")).longValue();long id=Database.addFeedback(uid,c,title,msg,rating,anon);send(p,"{\"ok\":true,\"id\":"+id+"}");
  }catch(Exception e){p.setStatus(400);send(p,"{\"error\":\"Unable to submit feedback.\"}");}
 }
 private static String statsJson(Map<String,Object> m){StringBuilder b=new StringBuilder("{");b.append("\"total\":").append(m.get("total")).append(",\"average\":").append(m.get("average")).append(",\"open\":").append(m.get("open")).append(",\"resolved\":").append(m.get("resolved")).append(",\"categories\":{");Map<String,Integer> c=(Map<String,Integer>)m.get("categories");int i=0;for(var e:c.entrySet()){if(i++>0)b.append(',');b.append('"').append(Json.esc(e.getKey())).append("\":").append(e.getValue());}return b.append("}}").toString();}
 private static String t(String s){return s==null?"":s.trim();}private static void send(HttpServletResponse p,String s)throws IOException{p.setContentType("application/json;charset=UTF-8");p.getWriter().print(s);}

}