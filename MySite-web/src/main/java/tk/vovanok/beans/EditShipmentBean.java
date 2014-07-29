/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vovanok.beans;



import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import tk.vovanok.commons.CurrencyUtils;
import tk.vovanok.commons.IdGenerator;
import tk.vovanok.entities.commons.ImagePath;
import tk.vovanok.commons.ImageUtils;
import tk.vovanok.dao.CategoryDao;
import tk.vovanok.dao.ShipmentDao;
import tk.vovanok.entities.Category;
import tk.vovanok.entities.Shipment;

/**
 *
 * @author vovan_000
 */
@ViewScoped
@Named
public class EditShipmentBean implements Serializable {
    
    @EJB
    private ShipmentDao shipmentDao;
    
    @EJB
    private CategoryDao categoryDao;

    private int textEditorNum;
    
    private String name;
    
    private String price;
    private String information;
    private int categoryId;
    private String pictureFolderName;
    private boolean renderFiles;
    private List<String> images;
    
    private String mainPicture;
    private boolean active;
    private List<Category> categories;
    private int currencyType = 1;
    private int currencyUnit = 1;
    private String currencyValue;
    
    private ImageUtils imUtils;
    
    private int editId;

    @Inject
    private ParametersTableBean table;

    @PostConstruct
    public void init(){
        textEditorNum=1;
        this.renderFiles = false;
        this.active = true;
        this.images = new ArrayList<>();
        this.imUtils = new ImageUtils();
        
    }
    
    private boolean edit = false;
    public void handleLoad(){
        if(edit || editId==0) return;
        loadById(editId);
        edit=true;
    }
 
    public void updateCategories(){
        
        categories = categoryDao.findAll();
    }
    
    public void reset(){
        name=null;
        price=null;
        information = null;
        categoryId = 0;
        pictureFolderName = null;
        renderFiles = false;
        active = true;
        images.clear();
        
        mainPicture = null;
        table.getParams().clear();
    }
    
        
    public String getCurrencyValue(){
        if(currencyUnit==4) return "1";
        if(currencyType==1) return new CurrencyUtils().getStatic(currencyUnit);
        if(currencyType==2) return new CurrencyUtils().getDinamic(currencyUnit);
        return currencyValue;        
    }
    
    public void setCurrencyValue(String value){
        currencyValue = value;        
    }
    
    public void loadById(int id){
        reset();
        
        Shipment cur = null;
        try{
            cur = shipmentDao.findById(Long.valueOf(id));
        } catch (Exception e){
            return;
        }
        if(cur==null) return;
        
        name = cur.getName();
        price = cur.getPrice().toString();
        information = cur.getInformation();
        categoryId = cur.getCategory();
        pictureFolderName = cur.getImageFolder();
        if(cur.getAllImages()!=null) images.addAll(cur.getAllImages());
        renderFiles = images.size() > 0;
        active = cur.isActive();
        mainPicture = cur.getMainImageName();
        if(cur.getParameters()!=null){
            table.getParams().addAll(cur.getParameters());
        }
        RequestContext.getCurrentInstance().update("form");
    }
    
    public void updateSession(){
        HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());  
        request.getSession();
        FacesMessage msg = new FacesMessage("Время сессии продлено"," на: "+(request.getSession().getMaxInactiveInterval()/60)+" минут");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void inputsErrors(){
        boolean error = false;
        if(name==null || name.trim().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Ошибка в названии", "Поле пустое"));  
            error = true;
        } else if(name.length() < 4){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Ошибка в названии", "Минимальное количество символов: 4"));  
            error = true;
        }
        
        Pattern p = Pattern.compile("[0-9]+(\\.[0-9]+)?");
        Matcher m = p.matcher(price);
        if(price==null || price.trim().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Ошибка в цене", "Поле пустое"));  
            error = true;
        }else if(!m.matches()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Ошибка в цене", "Число записано неправильно, допустимы только цифры и точка(.) например 120.5"));  
            error = true;
        }
        save();
        
        reset();
        RequestContext.getCurrentInstance().update("form");
        if(!error) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Сохранено", ":)"));  
    }
    
    public void save() {
        if(editId==0) shipmentDao.save(createEntity(this));
        else {
            Shipment s = createEntity(this);
            s.setId((long)editId);
            shipmentDao.update(s);
        }
    }
    
    private Shipment createEntity(EditShipmentBean shipment) {
        Shipment sh = new Shipment();
        sh.setName(shipment.getName());
        sh.setCreated(new Date());
        sh.setImageFolder(shipment.getPictureFolderName());
        sh.setInformation(shipment.getInformation());
        sh.setPrice(new BigDecimal(shipment.getPrice()));
        sh.setMainImageName(shipment.getMainPicture());
        sh.setCategory(shipment.getCategory());
        sh.setActive(shipment.isActive());
        sh.setParameters(shipment.getTable().getParams());
        sh.setAllImages(shipment.getImages());
        
        if(currencyType == 1) sh.setPriceCurrent(new BigDecimal("0"));
        else if(currencyType == 2) sh.setPriceCurrent(new BigDecimal("-1"));
        else sh.setPriceCurrent(new BigDecimal(currencyValue));
        
        sh.setPriceUnit(currencyUnit);
        String text = Jsoup.parse(shipment.getInformation()).text();
        if(text.length() > 300) text = text.substring(0,270);
        text += "...";
        
        sh.setInfoLight(text);
        
        return sh;
    }
    
    
    public synchronized void removePicture(String pictureName){
        if(images.size()==1){
            images.clear();
            try {
                FileUtils.deleteDirectory(new File(ImagePath.getPath() + "/" + getPictureFolderName()));
            } catch (IOException ex) {
                System.out.println("Error deleting folder");
            } 
            renderFiles = false;
        } else {
            images.remove(pictureName);
            FileUtils.deleteQuietly(new File(ImagePath.getPath() + "/" + getPictureFolderName()+"/"+pictureName));
            FileUtils.deleteQuietly(new File(ImagePath.getPath() + "/" + getPictureFolderName()+"/"+pictureName+"_small."+pictureName.substring(pictureName.lastIndexOf(".")+1).toLowerCase()));
        }
        
        RequestContext.getCurrentInstance().update("form:savedImages");
    }
    
    public String getAbsolutePath(String name){
        return "/image/"+getPictureFolderName()+"/"+name;
    }
    
    public String getAbsolutePathSmall(String name){
        return "/image/"+getPictureFolderName()+"/"+name+"_small."+name.substring(name.lastIndexOf(".")+1).toLowerCase();
    }
    
    
    
    public synchronized void handleFileUpload(FileUploadEvent event) {
        if (getPictureFolderName() == null) {
            setPictureFolderName(IdGenerator.generateId());
        }
        try {
            File dir = new File(ImagePath.getPath() + "/" + getPictureFolderName());
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if(new File(dir, event.getFile().getFileName()).exists()){
                FacesMessage msg = new FacesMessage("Ошибка!", event.getFile().getFileName() + " файл с таким именем уде есть!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return;
            }
            saveImage(event, dir);
            saveSmallImage(dir, event);
            
        } catch (IOException e) {
            //TODO good handling 
            FacesMessage msg = new FacesMessage("Ошибка!", event.getFile().getFileName() + " не получилось загрузить. Проблема сохранения файла.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } finally {
            
        }
        
        RequestContext.getCurrentInstance().update("form:savedImages");
        FacesMessage msg = new FacesMessage("Файл ", event.getFile().getFileName() + " загружен. Абсолютный путь папки: "+ImagePath.getPath()+"/"+getPictureFolderName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void saveSmallImage(File dir, FileUploadEvent event) throws IOException, ImagingOpException, IllegalArgumentException {
        imUtils.saveSmallImage(dir, event.getFile().getFileName());
    }

    private void saveImage(FileUploadEvent event, File dir) throws IOException, FileNotFoundException {
        imUtils.saveImage(dir, event.getFile().getInputstream(), event.getFile().getFileName());
        renderFiles = true;
        images.add(event.getFile().getFileName());
    }
    
// <editor-fold defaultstate="collapsed" desc="getters/setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public int getCategory() {
        return categoryId;
    }

    public void setCategory(int category) {
        this.categoryId = category;
    }

    public boolean isRenderFiles() {
        return renderFiles;
    }

    public void setRenderFiles(boolean renderFiles) {
        this.renderFiles = renderFiles;
    }

    public ParametersTableBean getTable() {
        return table;
    }

    public void setTable(ParametersTableBean table) {
        this.table = table;
    }
    
    public void setImages(List<String> images) {
        this.images = images;
    }
    
    public List<String> getImages() {
        return images;
    }
        
    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String mainPicture) {
        this.mainPicture = mainPicture;
    }
    
    public String getPictureFolderName() {
        return pictureFolderName;
    }

    public void setPictureFolderName(String pictureFolderName) {
        this.pictureFolderName = pictureFolderName;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    


    /**
     * @return the categories
     */
    public List<Category> getCategories() {
        if(categories == null || categories.isEmpty()) updateCategories();
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * @return the currencyType
     */
    public int getCurrencyType() {
        return currencyType;
    }

    /**
     * @param currencyType the currencyType to set
     */
    public void setCurrencyType(int currencyType) {
        this.currencyType = currencyType;
    }

    /**
     * @return the currencyUnit
     */
    public int getCurrencyUnit() {
        return currencyUnit;
    }

    /**
     * @param currencyUnit the currencyUnit to set
     */
    public void setCurrencyUnit(int currencyUnit) {
        this.currencyUnit = currencyUnit;
    }
    
    // </editor-fold> 

    public int getEditId() {
        return editId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
    }

    public int getTextEditorNum() {
        return textEditorNum;
    }

    public void setTextEditorNum(int textEditorNum) {
        this.textEditorNum = textEditorNum;
    }
}
