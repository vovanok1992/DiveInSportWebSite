/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;


import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import tk.vovanok.dao.RatingDao;
import tk.vovanok.dao.ShipmentDao;
import tk.vovanok.entities.Category;
import tk.vovanok.entities.RatingVote;
import tk.vovanok.entities.Shipment;
import tk.vovanok.entities.User;
import tk.vovanok.entities.commons.CategoriesUtils;

@Named
@ViewScoped
public class ProductDetailBean implements Serializable{  
    
    @EJB
    private CategoriesUtils categoriesUtils;
    
    @EJB
    private RatingDao ratingDaoImpl;
    
    @EJB
    private ShipmentDao shipmentDao;
    
    
    private int someass;
    
    private int stars;
 
    private long id;
    private Shipment curr;
    
    private MenuModel menu;
    
    @PostConstruct
    public void init(){
        if(id==0) return;
        setMenu(new DefaultMenuModel());
           
         DefaultMenuItem item = new DefaultMenuItem();
         item.setValue("Home");
         item.setUrl("/catalog.xhtml");
         item.setId("home");
         this.getMenu().addElement(item);
         
         for(Category c : categoriesUtils.getParentItems(getCurr().getCategory())){
             DefaultMenuItem itemTemp = new DefaultMenuItem();
             itemTemp.setValue(c.getName());
             itemTemp.setUrl("/catalog.xhtml?categoryId="+c.getId());
             itemTemp.setId("cat"+c.getId());
             this.getMenu().addElement(itemTemp);
         }

    }
      


    public Shipment getCurr() {
        if(id == 0) return null;
        if(curr == null) setCurr(shipmentDao.findById(id));
        return curr;
    }
    
    public HttpSession getSession() {
        return (HttpSession)
          FacesContext.
          getCurrentInstance().
          getExternalContext().
          getSession(false);
      }
    
    
    public void handleRate(){
         
       
        
        HttpSession session = getSession();
        User cur = (User)session.getAttribute("user");
        if(cur == null || cur.getAccessLevel() < 0){
            sendMessage("Вы не можете оценивать товары");
            return;
        }
        
        if(ratingDaoImpl.exists(id, cur.getId())){
            sendMessage("Вы уже голосовали за этот товар");
            return;
        }
        
        RatingVote rv = new RatingVote();
        rv.setCreated(new Date());
        rv.setMark(stars);
        rv.setUserId(cur.getId());
        rv.setShipmentId(id);
        
        ratingDaoImpl.save(rv);
        
       
        Shipment sh = shipmentDao.findById(id);
        double rate = sh.getEverageRating();
        int numOfVoters = sh.getVotesCount();
        
        rate = (rate * numOfVoters + stars)/(numOfVoters + 1);
        sh.setEverageRating(rate);
        sh.setVotesCount(numOfVoters+1);
        
        shipmentDao.update(sh);
        curr = sh;
        stars = sh.getRatingInStars();
        
        RequestContext.getCurrentInstance().update("rateInfo");
        
    }
    
    public void sendMessage(String message){
       RequestContext.getCurrentInstance().execute("alert('"+ message +"')");
       RequestContext.getCurrentInstance().update("rateInfo");
    }


    public void setCurr(Shipment curr) {
        this.curr = curr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStars() {
        if(id!=0) return curr.getRatingInStars();
        else return 0;        
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public MenuModel getMenu() {
        if(menu == null) init();
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }

    public int getSomeass() {
        return someass;
    }

    public void setSomeass(int someass) {
        this.someass = someass;
    }

} 