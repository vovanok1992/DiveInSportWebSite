package tk.vovanok.dao;

import javax.ejb.Local;
import tk.vovanok.entities.Category;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Local
public interface CategoryDao extends GenericDao<Category, Long>{
    
}
