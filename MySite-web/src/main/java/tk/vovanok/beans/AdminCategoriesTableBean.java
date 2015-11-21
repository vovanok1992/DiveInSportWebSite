/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;


import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import tk.vovanok.dao.CategoryDao;
import tk.vovanok.dao.ShipmentDao;
import tk.vovanok.entities.Category;
import tk.vovanok.entities.commons.CategoriesUtils;
import tk.vovanok.entities.commons.ImagePath;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
  

@Named
@ViewScoped
public class AdminCategoriesTableBean implements Serializable {  
    
    @EJB
    private CategoriesUtils categoriesUtilsImpl;
    
    @EJB
    private ShipmentDao shipmentDaoImpl;
   
    @EJB
    private CategoryDao categoryDaoImp;
    
    @EJB
    private ImagePath path;

    private TreeNode root;  
    private List<Category> categories;
   
    private Category selectedDocument;
    private UploadedFile file;
    
    private String editName;
    private String editDesc;
    private String newName;
    private String newDesc;
    private String newRootName;
    private String newRootDesc;

    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    @PostConstruct
    public void init() {  
        root = new DefaultTreeNode("root", null);  
        categories = categoryDaoImp.findAll();
        fill(root); 
        collapsingORexpanding(root, true);
    }  
    
    public void removeWithChilds(Category c , List forRemove){
        System.out.println("c_id"+c.getId());
        Iterator<Category> iter = categories.iterator();
        for(Category curr: categories){
            if (curr.getParentId() == c.getId()) {
                removeWithChilds(curr, forRemove);
                forRemove.add(curr); 
                
            }
        }
        categoriesUtilsImpl.update();
    }
    
    public void updateImage(){
    
        if((selectedDocument.getImg() != null)){
            File last =new File(path.getPathImg() + "/categories/" + selectedDocument.getImg());
            if(last.exists()){
                try{
                    last.delete();
                }catch(Exception e){
                    FacesContext.getCurrentInstance().addMessage(
                            null, new FacesMessage(
                                    "Ошибка удаления файла!"));
                    System.out.println("Error remove!!!!!");
                    e.printStackTrace();
                    return;
                }
            }
        }
        
        File dir = null;
        System.out.println(file == null);
        OutputStream fos = null;
        try{
            
            dir = new File(path.getPathImg() + "/categories/");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            fos = new FileOutputStream(dir.getAbsolutePath()+"/"+file.getFileName());
            IOUtils.copy(file.getInputstream(), fos);
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(     
                            "Ошибка загрузки файла!"));
            System.out.println("Error!!!!!");
            e.printStackTrace();
            return;
        } finally{
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(AdminCategoriesTableBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        selectedDocument.setImg(file.getFileName());
        categoryDaoImp.update(selectedDocument);
        categoriesUtilsImpl.update();
    }
    
    public void handleChange(){
        categoryDaoImp.update(selectedDocument);
        RequestContext.getCurrentInstance().update("form");
        categoriesUtilsImpl.update();
    }
    
   
    public void remove(Category c){
        List<Category> forRemove = new ArrayList<>();
        forRemove.add(c);   

        for(Category cat : forRemove){ 
            shipmentDaoImpl.removeAllByCategoryId(cat.getId());
            categoryDaoImp.delete(cat);
           
        }
        
        removeWithChilds(c,forRemove);
        reflesh();
        categoriesUtilsImpl.update();
    }
    
    public void reflesh(){
        root = new DefaultTreeNode("root", null); 
        categories = categoryDaoImp.findAll();
        fill(root);
        categoriesUtilsImpl.update();
        RequestContext.getCurrentInstance().update("form");
    }
    
    public void newCategory(String name, String desc){

        Category newCat = new Category();
        newCat.setCreated(new Date());
        if(selectedDocument!=null)newCat.setParentId(selectedDocument.getId());
        else newCat.setParentId(0l);
        newCat.setName(name);
        newCat.setDescription(desc);
        
        categoryDaoImp.save(newCat);
        categories.add(newCat);

        reflesh();
        categoriesUtilsImpl.update();
    }
    
    public void newRootCategory() {
        newCategory(newRootName, newRootDesc);
    }

    public void newChildCategory() {
        newCategory(newName, newDesc);
    }

    public void fill(TreeNode localRoot){
        
        if(localRoot.getParent()==null){
            for(Category c : categories){
                if(c.getParentId()==0){
                    TreeNode documents = new DefaultTreeNode(c, root); 
                    fill(documents);
                }
            }
        } else {
            int curId = ((Category)localRoot.getData()).getId().intValue();
            for(Category c : categories){
                if(c.getParentId() == curId){
                    TreeNode newNode = new DefaultTreeNode(c, localRoot);
                    fill(newNode);
                }
            }
        }
    }
    
    public void collapsingORexpanding(TreeNode n, boolean option) {

        if (n.getChildren().isEmpty()) {
            n.setSelected(false);
        } else {
            for (TreeNode s : n.getChildren()) {
                collapsingORexpanding(s, option);
            }
            n.setExpanded(option);
            n.setSelected(false);
        }
    }
    
      
    public TreeNode getRoot() {  
        if(root==null) init();
        return root;  
    }  
      
    public Category getSelectedDocument() {  
        return selectedDocument;  
    }  
  
    public void setSelectedDocument(Category selectedDocument) {  
        this.selectedDocument = selectedDocument;  
    }  

    /**
     * @return the categories
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * @return the editName
     */
    public String getEditName() {
        return editName;
    }

    /**
     * @param editName the editName to set
     */
    public void setEditName(String editName) {
        this.editName = editName;
    }

    /**
     * @return the editDesc
     */
    public String getEditDesc() {
        return editDesc;
    }

    /**
     * @param editDesc the editDesc to set
     */
    public void setEditDesc(String editDesc) {
        this.editDesc = editDesc;
    }

    /**
     * @return the newName
     */
    public String getNewName() {
        return newName;
    }

    /**
     * @param newName the newName to set
     */
    public void setNewName(String newName) {
        this.newName = newName;
    }

    /**
     * @return the newDesc
     */
    public String getNewDesc() {
        return newDesc;
    }

    /**
     * @param newDesc the newDesc to set
     */
    public void setNewDesc(String newDesc) {
        this.newDesc = newDesc;
    }

    /**
     * @return the newRootName
     */
    public String getNewRootName() {
        return newRootName;
    }

    /**
     * @param newRootName the newRootName to set
     */
    public void setNewRootName(String newRootName) {
        this.newRootName = newRootName;
    }

    /**
     * @return the newRootDesc
     */
    public String getNewRootDesc() {
        return newRootDesc;
    }

    /**
     * @param newRootDesc the newRootDesc to set
     */
    public void setNewRootDesc(String newRootDesc) {
        this.newRootDesc = newRootDesc;
    }
}  