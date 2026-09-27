package com.devops.portal;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebFilter;import jakarta.servlet.http.*;import java.io.*;
@WebFilter("/api/admin/*") public class AuthFilter implements Filter{public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain)throws IOException,ServletException{HttpServletRequest r=(HttpServletRequest)req;HttpServletResponse p=(HttpServletResponse)res;if(!Auth.admin(r)){p.setStatus(403);p.setContentType("application/json");p.getWriter().print("{\"error\":\"Administrator access required.\"}");return;}chain.doFilter(req,res);}}
