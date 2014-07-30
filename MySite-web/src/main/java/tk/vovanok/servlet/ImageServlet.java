/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.annotation.Resource;
import javax.ejb.EJB;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tk.vovanok.entities.commons.ImagePath;

/**
 *
 * @author vovan_000
 */
public class ImageServlet extends HttpServlet {

    @EJB
    private ImagePath path;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
        
        String filename = request.getPathInfo();

        File file = new File(path.getPathImg()+filename);

        if(file.exists()){
        response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        Files.copy(file.toPath(), response.getOutputStream());
        } else {    
            ServletContext context = getServletContext();
            String fullPath = context.getRealPath("/resources/img/no-image.png");
            File fileNotFound = new File(fullPath);
            response.setHeader("Content-Type", getServletContext().getMimeType(fileNotFound.getName()));
            response.setHeader("Content-Length", String.valueOf(fileNotFound.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileNotFound.getName() + "\"");
            Files.copy(fileNotFound.toPath(), response.getOutputStream());
        }
    }
}
