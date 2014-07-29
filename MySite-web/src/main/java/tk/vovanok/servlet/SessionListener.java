/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.servlet;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;
import tk.vovanok.system.Sessions;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public class SessionListener implements HttpSessionListener{
    EventBus eventBus = EventBusFactory.getDefault().eventBus();
    static volatile int sessionCounter = 0;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Sessions.add(se.getSession());
        System.out.println("new session :"+ ++sessionCounter);
        
        
        eventBus.publish("/counter", String.valueOf(sessionCounter));
        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Sessions.remove(se.getSession());
        eventBus.publish("/counter", String.valueOf(--sessionCounter));
    }
    
}
