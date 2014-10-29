package tk.vovanok.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import tk.vovanok.dao.UserDao;
import tk.vovanok.entities.User;

@Named
@SessionScoped
public class LoginBean implements Serializable {
    
    @EJB
    private UserDao userDao;

    private String password;
    private String message, uname;
    private boolean valid = false;
    
    private User u;
    
    private int priceType;

    public String getMessage() {
        return message;
    }
    
    @PostConstruct
    public void test(){
        System.out.println("WORKING!");
    }
    
    public long getId(){   
        if(getU()==null){        
            return -1;   
        }
        return getU().getId();
    }
    
    public int getAccess(){     
        if(getU()==null){
            return -1;    
        }
        return getU().getAccessLevel();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
    
    public boolean login(String uname, String password) {
        
        User u = userDao.loadByUserName(uname);
        if(u == null || (!u.getPassword().equals(password))) return false;
        
        this.u = u;
        return true;
    }

    public String login() {
        setValid(login(uname, password));
        if (isValid()) {
            // get Http Session and store username
            HttpSession session = getSession();
            session.setAttribute("user", this.getU());

            return "index";

        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ошибка!",
                            "Неправильный логин или пароль!"));
            return "login";
        }
    }

    public void reflesh() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
           
            ec.redirect(getURL());

        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loginReflesh() {
        setValid(login(uname, password));
        if (isValid()) {
            // get Http Session and store username
            HttpSession session = getSession();
            session.setAttribute("user", this.getU());
            updateHeader();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Ошибка!",  
                            "Неправильный логин или пароль!") );
           
            RequestContext.getCurrentInstance().update("header");

        }
    }

    public void loginRedirect() {
        System.out.println("here");
        setValid(login(uname, password));
        if (isValid()) {
            // get Http Session and store username
            HttpSession session = getSession();
            session.setAttribute("user", this.getU());
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ошибка!",
                            "Неправильный логин или пароль!"));
        }
    }

//    public String logout() {
//        HttpSession session = Util.getSession();
//        session.invalidate();
//        return "login";
//    }

    public void logoutReflesh() {
        HttpSession session = getSession();
        session.removeAttribute("user");
        
        this.setU(null);
        this.uname = null;
        this.password = null;
        this.valid = false;
        
        
        updateHeader();
    }

    public void updateHeader(){
        RequestContext.getCurrentInstance().update("header");
        RequestContext.getCurrentInstance().update("content"); 
    }
    
    private String getURL() {
        
       HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
       
       return req.getRequestURI();
   
    }
    
    
    public  HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

    public  HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().getRequest();
    }

    public  String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

    public  String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }
    

    /**
     * @return the valid
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * @param valid the valid to set
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public int getPriceType() {
        return priceType;
    }

    public void setPriceType(int priceType) {
        this.priceType = priceType;
    }

}
