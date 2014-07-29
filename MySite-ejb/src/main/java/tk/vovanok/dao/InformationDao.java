
package tk.vovanok.dao;

import java.util.List;
import tk.vovanok.entities.Information;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public interface InformationDao extends GenericDao<Information, Long>{
    List<Information> getLast(int amount);

    public List<Information> get(int first, int pageSize, String sortField, boolean equals);
}
