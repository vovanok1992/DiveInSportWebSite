package tk.vovanok.entities;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import tk.vovanok.entities.commons.ShipmentParameter;


@Entity(name = "SHIPMENTS")
public class Shipment extends BaseEntity{

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;
    
    @Column(name = "PRICE_UNIT")
    private int priceUnit;
    
    @Column(name = "PRICE_CURRENT")
    private BigDecimal priceCurrent;

    @Column(name = "DESCIPTION",length = 12000)
    private String information;
    
    @Column(name = "DESCIPTION_LIGHT",length = 355)
    private String infoLight;
    
    @Column(name = "RATING_VOTES_COUNT")
    private int votesCount;

    @Column(name = "EVERAGE_RATING")
    private double everageRating;

    @Column(name = "IMAGES_FOLDER_NAME")
    private String imageFolder;
    
    @Column(name = "CATEGORY")
    private int category;
    
    @Column(name = "MAIN_IMAGE")
    private String mainImageName;
    
    @Column(name = "ACTIVE")
    private boolean active;

    @ElementCollection
    @JoinTable(name = "PARAMETERS_OF_SHIPMENT",
            joinColumns = @JoinColumn(name = "SHIPMENT_ID")
    )
    private Collection<ShipmentParameter> parametersList;
    
    @ElementCollection
    @JoinTable(name = "IMAGES_OF_SHIPMENT",
            joinColumns = @JoinColumn(name = "SHIPMENT_ID")
    )
    @GenericGenerator(name="hilo-gen2",strategy = "hilo")
    @CollectionId(
            columns = {@Column(name="IMAGE_ID")},
            generator="hilo-gen2",
            type=@Type(type="int")
    )
    private Collection<String> allImages;

    @PrePersist
    protected void onCreate() {
        setCreated(new Date());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal prise) {
        this.price = prise;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String icon) {
        this.imageFolder = icon;
    }

    public double getEverageRating() {
        return everageRating;
    }
    
    public int getRatingInStars(){
        return (int)everageRating;
    }

    public void setEverageRating(double everageRating) {
        this.everageRating = everageRating;
    }

    public Collection<ShipmentParameter> getParameters() {
        return parametersList;
    }

    public void setParameters(List<ShipmentParameter> parameters) {
        this.parametersList = parameters;
    }

    public String getMainImageName() {
        return mainImageName;
    }

    public void setMainImageName(String mainImageName) { 
        this.mainImageName = mainImageName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<String> getAllImages() {
        return allImages;
    }

    public void setAllImages(Collection<String> allImages) {
        this.allImages = allImages;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }

    public String getAbsolutePath(String name){
        return "image/"+imageFolder+"/"+name;
    }
    
    public String getAbsolutePathSmall(String name){
        return "image/"+imageFolder+"/"+name+"_small."+name.substring(name.lastIndexOf(".")+1).toLowerCase();
    }

    /**
     * @return the infoLight
     */
    public String getInfoLight() {
        return infoLight;
    }

    /**
     * @param infoLight the infoLight to set
     */
    public void setInfoLight(String infoLight) {
        this.infoLight = infoLight;
    }
    
//    
//    @Transient MenuModel model;
//    public MenuModel getMenuModel(){
//        if(model==null) model = new LineMenu(this.category).getModel();
//        return model;
//    }

    /**
     * @return the priceCurrent
     */
    public BigDecimal getPriceCurrent() {
        return priceCurrent;
    }

    /**
     * @param priceCurrent the priceCurrent to set
     */
    public void setPriceCurrent(BigDecimal priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    /**
     * @return the priceUnit
     */
    public int getPriceUnit() {
        return priceUnit;
    }

    /**
     * @param priceUnit the priceUnit to set
     */
    public void setPriceUnit(int priceUnit) {
        this.priceUnit = priceUnit;
    }
}
