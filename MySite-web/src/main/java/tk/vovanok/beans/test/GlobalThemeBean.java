
package tk.vovanok.beans.test;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ApplicationScoped
public class GlobalThemeBean implements Serializable{
    
    private String theme = "vovan-one";

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    
    
}
