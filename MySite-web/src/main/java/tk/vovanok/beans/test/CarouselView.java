
package tk.vovanok.beans.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class CarouselView implements Serializable{
    
    private List<String> logos;
    
    @PostConstruct
    public void init(){
        logos = new ArrayList<>();
        logos.add("/resources/img/logo/1.jpg");
        logos.add("/resources/img/logo/2.jpg");
        logos.add("/resources/img/logo/3.jpg");
        logos.add("/resources/img/logo/4.jpg");
        logos.add("/resources/img/logo/5.jpg");
        logos.add("/resources/img/logo/6.jpg");
    }

    public List<String> getLogos() {
        return logos;
    }

    public void setLogos(List<String> logos) {
        this.logos = logos;
    }
    
}
