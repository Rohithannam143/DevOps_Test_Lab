package com.devops.portal;
import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.*;import java.util.*;
@WebServlet("/api/volunteer/*") public class VolunteerServlet extends HttpServlet {
 protected void doPost(HttpServletRequest r,HttpServletResponse p)throws IOException {
  try { String name=t(r.getParameter("name")),surname=t(r.getParameter("surname")),mobile=t(r.getParameter("mobile")),email=t(r.getParameter("email")),gender=t(r.getParameter("gender")),studying=t(r.getParameter("studying_about")),description=t(r.getParameter("description")),availability=t(r.getParameter("availability")),skills=t(r.getParameter("skills"));
   if(name.length()<2||surname.length()<2||mobile.length()<8||email.length()<5||studying.length()<2||description.length()<10){p.setStatus(400);send(p,"{\"ok\":false,\"error\":\"Please complete the required volunteer fields.\"}");return;}
   long id=Database.addVolunteer(name,surname,mobile,email,gender,studying,description,availability,skills);send(p,"{\"ok\":true,\"id\":"+id+"}");
  }catch(Exception e){p.setStatus(500);send(p,"{\"ok\":false,\"error\":\"Unable to save volunteer application.\"}");}
 }
 protected void doGet(HttpServletRequest r,HttpServletResponse p)throws IOException {try{if(!Auth.admin(r)){p.setStatus(403);send(p,"{\"error\":\"Administrator access required.\"}");return;}send(p,Json.list(Database.volunteers()));}catch(Exception e){p.setStatus(500);send(p,"{\"error\":\"Database unavailable.\"}");}}
 protected void doPut(HttpServletRequest r,HttpServletResponse p)throws IOException {try{if(!Auth.admin(r)){p.setStatus(403);return;}long id=Long.parseLong(r.getParameter("id"));Database.updateVolunteer(id,t(r.getParameter("status")));send(p,"{\"ok\":true}");}catch(Exception e){p.setStatus(400);send(p,"{\"error\":\"Unable to update volunteer.\"}");}}
 private static String t(String s){return s==null?"":s.trim();} private static void send(HttpServletResponse p,String s)throws IOException{p.setContentType("application/json;charset=UTF-8");p.getWriter().print(s);}
}
