/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.vovanok.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import tk.vovanok.entities.Category;
import tk.vovanok.entities.commons.CategoriesUtils;

/**
 *
 * @author vovan_000
 */
@Named
@ViewScoped
public class UserCategoriesBean implements Serializable{
    @EJB
    private CategoriesUtils categoriesUtilsImpl;

    private TreeNode root;

    @PostConstruct
    public void init() {

        root = new DefaultTreeNode("root", null);
        
        fill(root);
        collapsingORexpanding(root, true);
    }

    public void fill(TreeNode localRoot) {
        if (localRoot.getParent() == null) {
            for (Category c : getCategories()) {
                if (c.getParentId() == 0) {
                    TreeNode documents = new DefaultTreeNode(c, getRoot());
                    fill(documents);
                }
            }
        } else {

            Long curId = ((Category) localRoot.getData()).getId();

            for (Category c : getCategories()) {

                if (c.getParentId() == curId) {

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

    /**
     * @return the root
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * @return the categories
     */
    public List<Category> getCategories() {
        if(categoriesUtilsImpl == null) System.out.println("NULL!!");
        return categoriesUtilsImpl.getAll();
    }

}
