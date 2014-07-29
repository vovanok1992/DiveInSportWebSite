
package tk.vovanok.dao;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import tk.vovanok.entities.RatingVote;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class RatingDaoImpl extends GenericJpaDao<RatingVote, Long>
                           implements RatingDao{

    @Override
    public boolean exists(Long shipmentId, Long userId) {

        Query q = getEntityManager().createQuery("select count(*) from "
                + getPersistantClass().getSimpleName()
                + " v  where v.userId = :par1 and v.shipmentId = :par2");
        
                q.setParameter("par1", userId);
                q.setParameter("par2", shipmentId);
                
        int count = 0;
        
        try {
            count = ((Long) q.getSingleResult()).intValue();
        } catch (NoResultException e) {
            //nop
        }
        
        return count > 0l;
        }
    
}
