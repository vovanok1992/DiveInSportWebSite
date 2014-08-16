
package tk.vovanok.dao.implementation;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import tk.vovanok.dao.GenericJpaDao;
import tk.vovanok.dao.ShipmentDao;
import tk.vovanok.entities.Category;
import tk.vovanok.entities.Discount;
import tk.vovanok.entities.Shipment;
import tk.vovanok.entities.commons.CategoriesUtils;

import tk.vovanok.entities.commons.CategorySortsTypes;
import tk.vovanok.entities.commons.ImagePath;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class ShipmentDaoImpl extends GenericJpaDao<Shipment, Long>
                             implements ShipmentDao{
    @EJB
    private ImagePath imagePath;
    
    @EJB
    private CategoriesUtils categoriesUtilsImpl;
    
    

    
    
    @Override
    public List<Shipment> search(String value, int pageSize, int page,int sortType) {
        Session session = getEntityManager().unwrap(Session.class);
        
        Criteria q = session.createCriteria(Shipment.class);
        
        q.add(Restrictions.ilike("name", value, MatchMode.ANYWHERE));
        q.setFirstResult(page*pageSize);
        q.setMaxResults(pageSize);
        q.addOrder(getOrderById(sortType));
        
        return q.list();
    }

    @Override
    public Shipment findById(Long id) {
        Session session = getEntityManager().unwrap(Session.class);
        Shipment result = (Shipment) session.get(Shipment.class, id);
        Hibernate.initialize(result.getParameters());
        Hibernate.initialize(result.getAllImages());
        return result;
    }
    
    
    
    Order getOrderById(int id) {
        switch (id) {
            case CategorySortsTypes.BY_DATE_ASC:
                return Order.asc("created");
            case CategorySortsTypes.BY_DATE_DESC:
                return Order.desc("created");

            case CategorySortsTypes.BY_PRICE_ASC:
                return Order.asc("price");
            case CategorySortsTypes.BY_PRICE_DESC:
                return Order.desc("price");

            default:
                return Order.asc("surrogateKey");
        }
    }

    @Override
    public Long countSearchResults(String value) {
        Session session = getEntityManager().unwrap(Session.class);
        Criteria q = session.createCriteria(Shipment.class);
        q.add(Restrictions.ilike("name", value, MatchMode.ANYWHERE));
        q.setProjection(Projections.rowCount());

        Long result = (long) q.uniqueResult();
        return result;
    }

    @Override
    public long getNuberOfShipments(int category) {
        Long result = null;
        List<Category> searchCategories = categoriesUtilsImpl.getChildWthCategories(category);
        
        Session session = getEntityManager().unwrap(Session.class);
        
        Criteria q = session.createCriteria(Shipment.class);
        Disjunction or = Restrictions.disjunction();

        if (category != 0) {
            for (Category curCat : searchCategories) {
                or.add(Restrictions.eq("category", curCat.getId().intValue()));
            }
            q.add(or);
        }
        
        q.setProjection(Projections.rowCount());

        
        
        result = (Long) q.uniqueResult();
        
        return result;
    }

    @Override
    public List<Shipment> getBasicInfoList(int sortType, int c, int page, int pageSize) {

        List<Category> searchCategories = categoriesUtilsImpl.getChildWthCategories(c);
        List<Shipment> result = null;

        Session session = getEntityManager().unwrap(Session.class);
        
        Criteria q = session.createCriteria(Shipment.class);
        Disjunction or = Restrictions.disjunction();

        if (c != 0) {
            for (Category curCat : searchCategories) {
                or.add(Restrictions.eq("category", curCat.getId().intValue()));
            }
        }

        q.add(or);
        q.addOrder(getOrderById(sortType));
        q.setFirstResult(page*pageSize);
        q.setMaxResults(pageSize);

        result = q.list();

        return result;
    }

    @Override
    public List<Shipment> getLast(int i) {
        Session session = getEntityManager().unwrap(Session.class);
       
        Criteria q = session.createCriteria(Shipment.class);
        q.addOrder(Order.desc("created"));
        q.setMaxResults(i);

        return q.list();
    }

    @Override
    public void removeAllByCategoryId(Long id) {
         List<Category> searchCategories = categoriesUtilsImpl.getChildWthCategories(id.intValue());  
         for(Category c : searchCategories){
             removeAllBySingleCategoryId(c.getId().intValue());
         }
    }
    
     public void removeAllBySingleCategoryId(int id) {
     
        Session session = getEntityManager().unwrap(Session.class);
      

        Query q = session.createQuery("from SHIPMENTS where category =:key");
        q.setInteger("key", id);
        List<Shipment> result = q.list();
        
        for(Shipment s: result){
            try {
                FileUtils.deleteDirectory(new File(imagePath.getPathImg()+"/"+s.getImageFolder()));
            } catch (IOException ex) {
                System.out.println("Error deleting picture folder for  '"+s.getName()+"'");
            }
            session.delete(s);
        }

    }
     
    @Override
    public List<Shipment> getIdIn(List<Long> list){
        Session s = getEntityManager().unwrap(Session.class);
        
        Criteria c = s.createCriteria(Shipment.class);
        c.add(Restrictions.in("id", list));
        
        System.out.println("LIST is"+list);
        
        List<Shipment> discs = c.list();
    
        
        return discs;        
    }
    
}
