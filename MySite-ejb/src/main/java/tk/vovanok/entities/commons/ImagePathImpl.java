package tk.vovanok.entities.commons;

import javax.annotation.Resource;
import javax.ejb.Stateless;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class ImagePathImpl implements ImagePath{

    @Resource(lookup = "imageFolder")
    private String pathImg;
    
    public String getPathImg(){
        return pathImg;
    }
}
