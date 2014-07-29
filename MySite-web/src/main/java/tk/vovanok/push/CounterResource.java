/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.push;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

@PushEndpoint("/counter")
public class CounterResource {

    @OnMessage(encoders = {JSONEncoder.class})
    public String onMessage(String count) {
        System.out.println("MESSAGE: "+count);
        return count;
    }
}
