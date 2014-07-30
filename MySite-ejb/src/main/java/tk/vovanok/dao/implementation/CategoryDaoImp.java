
package tk.vovanok.dao.implementation;

import java.io.Serializable;
import javax.ejb.Stateless;
import tk.vovanok.dao.CategoryDao;
import tk.vovanok.dao.GenericJpaDao;
import tk.vovanok.entities.Category;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class CategoryDaoImp extends GenericJpaDao<Category, Long>
                            implements CategoryDao{
    
}
