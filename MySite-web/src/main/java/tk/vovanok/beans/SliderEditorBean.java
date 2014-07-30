
package tk.vovanok.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import tk.vovanok.dao.SlideDao;
import tk.vovanok.entities.Slide;
import tk.vovanok.entities.commons.ImagePath;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class SliderEditorBean implements Serializable{
    
    private List<Slide> slides;
    
    @EJB
    SlideDao slideDao;
    
    @EJB
    ImagePath path;
    
    private String text;
    private String imageName;
    private String link;
    
    private Slide selected;
    
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    
    @PostConstruct
    public void init(){
        setSlides(slideDao.getMainSlides());
    }

    public void save(){
        File dir = null;
        System.out.println(file == null);
        try{
            
            dir = new File(path.getPathImg() + "/slider/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            OutputStream fos = new FileOutputStream(dir.getAbsolutePath()+"/"+file.getFileName());
            IOUtils.copy(file.getInputstream(), fos);
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(     
                            "Ошибка загрузки файла!"));
            System.out.println("Error!!!!!");
            e.printStackTrace();
            return;
        }
        
        Slide s = new Slide();
        s.setSliderId(1l);
        s.setImagePath(file.getFileName());
        s.setLink(getLink());
        s.setText(getText());
        
        slideDao.save(s);
        slides.add(s);
        System.out.println("Finish");
    }
    
    public void remove(){
        File f = new File(path.getPathImg()+"/slider/"+selected.getImagePath());
        try{
            f.delete();
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(     
                            "Ошибка удаления файла!"));
          
        }
        slideDao.delete(getSelected());
        slides.remove(selected);
        System.out.println("finish");
    }

    public List<Slide> getSlides() {
        return slides;
    }

    public void setSlides(List<Slide> slides) {
        this.slides = slides;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Slide getSelected() {
        return selected;
    }

    public void setSelected(Slide selected) {
        this.selected = selected;
    }
    
}
