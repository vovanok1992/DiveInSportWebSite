
package tk.vovanok.dao;

import java.io.Serializable;
import java.util.List;
import tk.vovanok.entities.Slide;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public interface SlideDao extends GenericDao<Slide, Long>{
    
    List<Slide> getMainSlides();
    
    List<Slide> getSlidesForId(Long id);
 
    
}
