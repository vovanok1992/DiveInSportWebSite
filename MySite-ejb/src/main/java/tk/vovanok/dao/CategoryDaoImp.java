
package tk.vovanok.dao;

import java.io.Serializable;
import javax.ejb.Stateless;
import tk.vovanok.entities.Category;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class CategoryDaoImp extends GenericJpaDao<Category, Long>
                            implements CategoryDao{
    
}
