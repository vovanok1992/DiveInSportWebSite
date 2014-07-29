/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@WebServlet(urlPatterns = {"/gcm"})
public class GcmServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET METHOD!");
        doPost(req, resp);
    }

    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST METHOD");
        
        Map<String, String[]> parameters = req.getParameterMap();
        for (String parameter : parameters.keySet()) {
            System.out.println("PARAM: "+ parameter);
            System.out.println("VALUE: "+ Arrays.toString(parameters.get(parameter)));
        }
        
        System.out.println("FINISH");
    }
    
    
    
}
