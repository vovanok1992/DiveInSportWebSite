
package tk.vovanok.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tk.vovanok.dao.SlideDao;
import tk.vovanok.entities.Slide;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class SliderBean implements Serializable{
    
    @EJB
    SlideDao slideDao;
    
    private List<Slide> slides;
    
    @PostConstruct
    public void init(){
        setSlides(slideDao.getMainSlides());
        System.out.println(slides.size()+"---");
    }

    public List<Slide> getSlides() {
        return slides;
    }

    public void setSlides(List<Slide> slides) {
        this.slides = slides;
    }
    
}
