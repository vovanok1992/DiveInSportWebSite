/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vovanok.commons;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;
import org.primefaces.context.RequestContext;
import tk.vovanok.dao.UserDao;
import tk.vovanok.entities.User;
import tk.vovanok.entities.commons.AditionalParameter;

@Named
@RequestScoped
public class RegiserBean {
    
    @EJB
    private UserDao userDao;

    private String password;
    private String passwordConfirm;
    private String login;
    private String captcha;
    
    private String phone;
    private String email;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    public void registerRedirect() {
        boolean error = false;
        if (userDao.checkAvailable(login)) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ошибка! Пользоваель с таким именем уже существует!", "error1"));
            error = true;
        }

        if (!password.equals(passwordConfirm)) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ошибка! Пароли не совпадают!", "error2"));
            error = true;
        }

        
       
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        Captcha secretcaptcha = (Captcha) session.getAttribute(Captcha.NAME);
        if (!secretcaptcha.isCorrect(captcha)){
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ошибка! Проверка на бота провалена!", "error3"));
            error = true;
        }
                
        
        if (error) {
            return;
        }

        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setAccessLevel(0);
        newUser.setRegistrationDate(new Date());
        newUser.setIpAddress(getIp());
        List<AditionalParameter> params = new ArrayList<>();
       
        if(checkPhone()){
            
            AditionalParameter phoneParam = new AditionalParameter();
            phoneParam.setParam("phone");
            phoneParam.setArgument(phone);
            params.add(phoneParam);
            
        }
        
        if((email!=null) && (!email.trim().equals(""))){
            AditionalParameter phoneParam = new AditionalParameter();
            phoneParam.setParam("email");
            phoneParam.setArgument(email);
            params.add(phoneParam);
        }
        
        newUser.setAditionalInfo(params);
        
        try{
            userDao.save(newUser);
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Ошибка! DB error!", "error4"));
            return;
        }
        RequestContext.getCurrentInstance().execute("PF('dlgRegister').hide()");
        RequestContext.getCurrentInstance().execute("PF('regFinish').show()");
    }

    public String getIp(){
        
        HttpServletRequest request = (HttpServletRequest) 
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest();
        return request.getRemoteAddr()+"("+request.getHeader("X-FORWARDED-FOR")+")";
    }
    
    /**
     * @return the passwordConfirm
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * @param passwordConfirm the passwordConfirm to set
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private boolean checkPhone() {
        if(phone == null || phone.trim().equals("")) return false;
        
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
