
package tk.vovanok.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class MessegerBean {
    
    public void test1(){
        sendMessage(true, "asdasd");
    }
    
    public void test2(){
        sendMessage(false, "user");
    }
    
    public void sendMessage(boolean admin, String message){
        FacesContext context = FacesContext.getCurrentInstance();
         
        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        
        if(admin){
            RequestContext.getCurrentInstance().update("admmessform");
        } else {
            System.out.println("us");
            RequestContext.getCurrentInstance().update("growl");
        }
        
    }
    
}
