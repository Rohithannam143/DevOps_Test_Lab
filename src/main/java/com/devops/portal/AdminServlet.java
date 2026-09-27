package com.devops.portal;
import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.*;
@WebServlet("/api/admin/*") public class AdminServlet extends HttpServlet{
 protected void doPost(HttpServletRequest r,HttpServletResponse p)throws IOException{if(!Auth.admin(r)){p.setStatus(403);return;}try{long id=Long.parseLong(r.getParameter("id"));String status=r.getParameter("status"),priority=r.getParameter("priority"),note=r.getParameter("note");if(!java.util.Set.of("SUBMITTED","UNDER_REVIEW","ACTION_IN_PROGRESS","RESOLVED","CLOSED").contains(status)||!java.util.Set.of("LOW","NORMAL","HIGH","URGENT").contains(priority)){p.setStatus(400);return;}Database.updateFeedback(id,status,priority,note==null?"":note.trim());p.setContentType("application/json");p.getWriter().print("{\"ok\":true}");}catch(Exception e){p.setStatus(400);}}
}
