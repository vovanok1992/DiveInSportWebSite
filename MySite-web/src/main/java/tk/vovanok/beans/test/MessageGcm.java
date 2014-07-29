/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans.test;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author vovan_000
 */
@ManagedBean
@ViewScoped
public class MessageGcm implements Serializable{
    
    private String collapseKey = "GCM_Message";
    private String userMessage  = "Generic Broadcast Message";
    private Object collapseKeyObj;

     private static final String SENDER_ID = "AIzaSyDbZibUa_UPC7r0yiqCqxOlcle5JRNLDYU";
     
    // This is a *cheat*  It is a hard-coded registration ID from an Android device
    // that registered itself with GCM using the same project id shown above.
    private static final String DROID_BIONIC = "APA91bGeDY4RHYYxOmLlIKEsmXLY3i7hEphvHPUPM-t6Io0efB-t59flEuMbdAI4p4u0huveI64eFe4ktXcHw3JA2fcvBzZb8fHsFj3i7-7IFH9OQZMQA3Zb3I5ZODfcnlaUpbGGyzirHxXF_ObOUEzW5PfsOK-SJvZRCcRqGnP5Wa16qsiTd_Q";
         
    // This array will hold all the registration ids used to broadcast a message.
    // for this demo, it will only have the DROID_BIONIC id that was captured 
    // when we ran the Android client app through Eclipse.
    private List<String> androidTargets = new ArrayList<String>();
    
    
    public MessageGcm() {
        
        androidTargets.add(DROID_BIONIC);
     
    }

    
    public void sendMessage(){
        Sender sender = new Sender(SENDER_ID);
         
        // This Message object will hold the data that is being transmitted
        // to the Android client devices.  For this demo, it is a simple text
        // string, but could certainly be a JSON object.
        Message message = null;
         
        // If multiple messages are sent using the same .collapseKey()
        // the android target device, if it was offline during earlier message
        // transmissions, will only receive the latest message for that key when
        // it goes back on-line.
        
         
        try {
            
        message = new Message.Builder().collapseKey(collapseKey)
        .timeToLive(30)
        .delayWhileIdle(true)
        .addData("message", URLEncoder.encode(userMessage,"UTF-8"))
        .build();
        
        
            
            // use this for multicast messages.  The second parameter
            // of sender.send() will need to be an array of register ids.
            MulticastResult result = sender.send(message, androidTargets, 1);
             
            if (result.getResults() != null) {
                int canonicalRegId = result.getCanonicalIds();
                if (canonicalRegId != 0) {
                     
                }
            } else {
                int error = result.getFailure();
                System.out.println("Broadcast failure: " + error);
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("Finish"+userMessage); 
    }
    
    /**
     * @return the collapseKey
     */
    public String getCollapseKey() {
        return collapseKey;
    }

    /**
     * @param collapseKey the collapseKey to set
     */
    public void setCollapseKey(String collapseKey) {
        this.collapseKey = collapseKey;
    }

    /**
     * @return the message
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * @param message the message to set
     */
    public void setUserMessage(String message) {
        this.userMessage = message;
    }

    /**
     * @return the collapseKeyObj
     */
    public Object getCollapseKeyObj() {
        return collapseKeyObj;
    }

    /**
     * @param collapseKeyObj the collapseKeyObj to set
     */
    public void setCollapseKeyObj(Object collapseKeyObj) {
        this.collapseKeyObj = collapseKeyObj;
    }
    
    
    
}
