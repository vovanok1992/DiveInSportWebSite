
package tk.vovanok.dao.implementation;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import tk.vovanok.dao.GenericJpaDao;
import tk.vovanok.dao.SlideDao;
import tk.vovanok.entities.Slide;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class SlideDaoImpl extends GenericJpaDao<Slide, Long>
                          implements SlideDao{

    @Override
    public List<Slide> getMainSlides() {
        return getSlidesForId(1l);
    }

    @Override
    public List<Slide> getSlidesForId(Long id) {
        Query q = getEntityManager().createQuery("select s from "
                                                    +getPersistantClass().getSimpleName()
                                                    +" s where sliderId = :sId ").setParameter("sId", id);
        return (List<Slide>) q.getResultList();
    }
    
}
