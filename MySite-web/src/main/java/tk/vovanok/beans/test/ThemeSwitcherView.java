
package tk.vovanok.beans.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class ThemeSwitcherView implements Serializable{
 
    private List<String> themes;
    
    private String selected;
    

    
    @Inject
    GlobalThemeBean globalThemeBean;
 
    public void handleChange(){
        System.out.println(selected);
        globalThemeBean.setTheme(selected);
    }
    
    @PostConstruct
    public void init() {
        themes = new ArrayList<>();
        getThemes().add("ser-one");
        getThemes().add("ser-two");
        getThemes().add("ser-three");
        getThemes().add("ser-four");
        getThemes().add("ser-five");
        getThemes().add("vovan-one");
        
        getThemes().add("aristo");
        getThemes().add("black-tie");
        getThemes().add("blitzer");
        getThemes().add("bluesky");
        getThemes().add("casablanca");
        getThemes().add("cupertino");
        getThemes().add("dark-hive");
        getThemes().add("dot-luv");
        getThemes().add("eggplant");
        getThemes().add("excite-bike");
        getThemes().add("flick");
        getThemes().add("glass-x");
        getThemes().add("hot-sneaks");
        getThemes().add("humanity");
        getThemes().add("le-frog");
        
        getThemes().add("midnight");
        getThemes().add("mint-choc");
        getThemes().add("overcast");
        getThemes().add("pepper-grinder");
        
        getThemes().add("redmond");
        getThemes().add("rocket");
        getThemes().add("sam");
        getThemes().add("smoothness");
        
        getThemes().add("south-street");
        getThemes().add("start");
        getThemes().add("sunny");
        getThemes().add("swanky-purse");
        
        getThemes().add("trontastic");
        getThemes().add("ui-lightness");
        getThemes().add("ui-darkness");
        getThemes().add("vader");

    }
     


    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public List<String> getThemes() {
        return themes;
    }

    public void setThemes(List<String> themes) {
        this.themes = themes;
    }
}