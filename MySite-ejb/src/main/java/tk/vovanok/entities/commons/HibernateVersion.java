
package tk.vovanok.entities.commons;

import javax.ejb.Stateless;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class HibernateVersion {
    
    public String getHibernateVersion(){
        return org.hibernate.Version.getVersionString();
    }

    
}
