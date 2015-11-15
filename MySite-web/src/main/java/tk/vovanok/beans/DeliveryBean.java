package tk.vovanok.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vovan_000 on 15.11.2015.
 */
@Named
@RequestScoped
public class DeliveryBean {

    private Map<String, String> deliveryTypes;
    private Map<String, String> payTypes;


    @PostConstruct
    public void init() {

        deliveryTypes = new HashMap<>();
        payTypes = new HashMap<>();

        deliveryTypes.put("Курьером (Киев)","cur");
        deliveryTypes.put("Новая почта","post");
        deliveryTypes.put("Автолюкс","autolux");
        deliveryTypes.put("Самовывоз в Киеве","self");

        payTypes.put("На карту Приват Банка","privat");
        payTypes.put("Наложенный платеж","pay");



    }

    public Map<String, String> getDeliveryTypes() {
        return deliveryTypes;
    }

    public void setDeliveryTypes(Map<String, String> deliveryTypes) {
        this.deliveryTypes = deliveryTypes;
    }

    public Map<String, String> getPayTypes() {
        return payTypes;
    }

    public void setPayTypes(Map<String, String> payTypes) {
        this.payTypes = payTypes;
    }
}
