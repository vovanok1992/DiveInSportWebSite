package tk.vovanok.dao;

import java.util.List;
import tk.vovanok.entities.User;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public interface UserDao extends GenericDao<User, Long>{
    
    boolean checkAvailable(String userName);
    
    User loadByUserName(String userName);

    public List<User> getUsers(int first, int pageSize, String sortField, boolean equals);
    
    
    
}
