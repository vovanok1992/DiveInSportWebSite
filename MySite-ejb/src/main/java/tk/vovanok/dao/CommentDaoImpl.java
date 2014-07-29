
package tk.vovanok.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import tk.vovanok.entities.Comment;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class CommentDaoImpl extends GenericJpaDao<Comment, Long> 
                             implements CommentDao{

    @Override
    public List<Comment> getCommentsByShipmentId(Long id, int page, int pageSize) {
        Query q = getEntityManager().createQuery("select c from " +getPersistantClass().getSimpleName()+
                                                 " c where c.forShipmentId = :par1");
        q.setParameter("par1", id);
        q.setFirstResult(page*pageSize);
        q.setMaxResults(pageSize);
        
        List<Comment> result = (List<Comment>) q.getResultList();
        return result;
    }

    @Override
    public Long getNumberOfCommentsForShipment(Long id) {
        Query q = getEntityManager().createQuery("select count(*) from "
                                                    +getPersistantClass().getSimpleName()
                                                    +" c where c.id = :id")
                                                    .setParameter("id", id);
        
        Long count =(Long) q.getSingleResult();
        return count;
    }

    @Override
    public void deleteById(int id) {
        Query q = getEntityManager().createQuery("delete from "
                                                    +getPersistantClass().getSimpleName()
                                                    +" c whwere c.id = :id")
                                                    .setParameter("id", id);
        q.executeUpdate();
    }

    @Override
    public Long getNumberOfCommentsForUser(Long userId) {
        Query q = getEntityManager().createQuery("select count(*) from "
                                                    +getPersistantClass().getSimpleName()
                                                    +" c where c.userId = :userId")
                                                    .setParameter("userId", userId);
        
        Long count =(Long) q.getSingleResult();
        return count;
    }

    @Override
    public List<Comment> getCommentsByUserId(Long id, int starstRow, int pageSize, String orderField, boolean asc) {
        List<Comment> result = null;
        Session session = getEntityManager().unwrap(Session.class);        
        Criteria c = session.createCriteria(Comment.class);
        c.add(Restrictions.eq("userId", id));
        c.setFirstResult(starstRow);
        c.setMaxResults(pageSize);
        
        if(orderField!=null){
            if(asc) c.addOrder(Order.asc(orderField));
            else c.addOrder(Order.desc(orderField));
        }
        
        result = (List<Comment>) c.list();
   
        return result;
    }
    
}
