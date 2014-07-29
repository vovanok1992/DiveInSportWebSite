/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.system;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */

public class Sessions {
    
    private static List<HttpSession> sessions;
    
    public static int getSize(){
        return sessions.size();
    }
    
    public synchronized static void invalidateAll(){
        if(sessions == null || sessions.size() == 0) return;
        Object[] sess = sessions.toArray();
        for (Object httpSession : sess) {
            ((HttpSession)httpSession).invalidate();
        }
        sess = null;
        sessions.clear();
    }
    
    public synchronized static void add(HttpSession ses){
        if(sessions == null) sessions = new ArrayList<>();
        sessions.add(ses);
    }
    
    public synchronized static void remove(HttpSession ses){
        if(sessions == null) return;
        sessions.remove(ses);
    }
    
    
}
