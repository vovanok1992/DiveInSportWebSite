/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.servlet;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tk.vovanok.beans.LoginBean;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public class AccessFilter implements Filter{

    @Inject
    LoginBean login;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req;
        req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        if (requestURI.contains("/resources/admin/")) {
            if(login==null || login.getAccess() < 5){
                ((HttpServletResponse) response).sendRedirect("../../index.xhtml");
            }
        } 
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {
    }
    
}
