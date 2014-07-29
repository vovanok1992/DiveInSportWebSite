/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.system;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.hibernate.Hibernate;
import tk.vovanok.entities.commons.HibernateVersion;


/**
 *
 * @author vovan_000
 */
@ManagedBean
public class ServerInfo {
    
    private List<String> images;  
    
    @EJB
    HibernateVersion hibernateVersion;
    
    @PostConstruct
    public void init() {  
        images = new ArrayList<>(); 
        images.add("/resources/img/about/glassfish.png");
        images.add("/resources/img/about/hibernate.png");
        images.add("/resources/img/about/java.png");
        images.add("/resources/img/about/jsf.png");
        images.add("/resources/img/about/maven.png");
        images.add("/resources/img/about/prime.png");
        images.add("/resources/img/about/atmosphere.png");
    }
    
    public String getJsfImplementation(){
        return FacesContext.class.getPackage().getImplementationTitle();
    }
    
    public String getImplementationVersion(){
        return FacesContext.class.getPackage().getImplementationVersion();
    }
    
    public String getJavaVersion(){
        return Runtime.class.getPackage().getImplementationVersion();
    }

    public String getServerInfo(){
        return ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getServerInfo();
    }
    
    public String getPrimeFacesInfo(){
        return org.primefaces.util.Constants.class.getPackage().getImplementationVersion();
    }
    
    public String getHibernateInfo(){
        return hibernateVersion.getHibernateVersion();
    }
    
    public String getAtmosphereInfo(){
        return "Atmosphere "+
                org.atmosphere.cpr.AtmosphereFilter.class.getPackage().getImplementationVersion();
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    
  
    
    
    
}
