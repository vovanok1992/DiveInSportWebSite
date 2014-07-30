/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.entities.commons;

import javax.annotation.Resource;
import javax.ejb.Stateless;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class ImagePathImpl implements ImagePath{

    @Resource(lookup = "ImageFolder")
    private String pathImg;
    
    public String getPathImg(){
        return pathImg;
    }
}
