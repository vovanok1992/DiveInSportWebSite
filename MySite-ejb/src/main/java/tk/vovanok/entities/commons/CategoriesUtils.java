
package tk.vovanok.entities.commons;

import java.util.List;
import tk.vovanok.entities.Category;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public interface CategoriesUtils {
    public  List<Category> getAll();
    public void update();
    public List<Category> getParentItems(int cId);
    public List<Category> getChildWthCategories(int cId);
}
