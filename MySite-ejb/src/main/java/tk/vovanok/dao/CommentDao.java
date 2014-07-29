
package tk.vovanok.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import tk.vovanok.entities.Comment;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Local
public interface CommentDao extends GenericDao<Comment, Long>{

    public List<Comment> getCommentsByShipmentId(Long id, int i, int pageSize);

    public Long getNumberOfCommentsForShipment(Long id);

    public void deleteById(int id);

    public Long getNumberOfCommentsForUser(Long userId);

    public List<Comment> getCommentsByUserId(Long userId, int first, int pageSize, String sortField, boolean equals);
    
    
    
}
