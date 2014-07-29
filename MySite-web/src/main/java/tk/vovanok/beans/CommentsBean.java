/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import tk.vovanok.dao.CommentDao;
import tk.vovanok.entities.Comment;
import tk.vovanok.entities.User;

@Named
@ViewScoped
public class CommentsBean implements Serializable{
    @EJB
    private CommentDao commentDao;

    
    User curUser;
   
    private Comment selectedComment;
    List<Comment> comments;
    private Long allCommentsCount;
    private List<Integer> pages;
    
    private int curPage = 1;
    
    int page = 0;
    int pageSize = 10;
    private Long id;
    
    private String newCommentText;
  
    public void updateUser(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        curUser = (User) session.getAttribute("user");
    }
    
    public void handleEdit(){
        
        commentDao.update(selectedComment);
        updateComments();
        RequestContext.getCurrentInstance().update("comments");
        
    }
    
    public Long getUserId(){
        updateUser();
        if(curUser !=null) {
            System.out.println("id is "+curUser.getId());
            return curUser.getId();
            
        }
        else return -1l;
    }
    
    public int getUserAccessLevel(){
        updateUser();
        if(curUser !=null) return curUser.getAccessLevel();
        else return -1;
    }
    
    public void setPage(int page){
        curPage = page;
        updateComments();
        RequestContext.getCurrentInstance().update("comments");
    }
    
    public String curButtonStyle(int num){
        if(num == curPage) return "color: #FF9933";
        return "";
    }
    
    
    public void updateComments(){
        System.out.println("ID c:"+id);
        if(getId()==0) return;
        comments = commentDao.getCommentsByShipmentId(getId(), curPage-1 , pageSize);
        
        setAllCommentsCount(commentDao.getNumberOfCommentsForShipment(getId()));
    }
    
    public String getPageString(int page){
        
        if(page == -1) return "..";
        else return String.valueOf(page);
    }
    
    public void removeCommentById(int id){   
        commentDao.deleteById(id);
        updateComments();
        
        FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Комментарий удален!",
                            ""));
        RequestContext.getCurrentInstance().update("userinfo");

    }
    
    public List<Comment> getForId(){

        updateUser();
        if(comments == null){
            updateComments();
        }
        return comments;
    }
    
    public void createComment(){
        if(curUser == null || curUser.getAccessLevel()<0){return;}
        
        Comment c = new Comment();
        c.setText(newCommentText);
        c.setDateAdded(new Date());
        c.setForShipmentId(getId());

        c.setUserId(curUser.getId());
        c.setUserName(curUser.getLogin());
        
        c.setIp(
        ((HttpServletRequest) 
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequest()).getRemoteAddr()
        );
        
        commentDao.save(c);
        updateComments();
        
        RequestContext.getCurrentInstance().update("comments");
        
        FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Комментарий добавлен!",
                            ""));
        RequestContext.getCurrentInstance().update("userinfo");
        newCommentText="";
    }

    /**
     * @return the newCommentText
     */
    public String getNewCommentText() {
        return newCommentText;
    }

    /**
     * @param newCommentText the newCommentText to set
     */
    public void setNewCommentText(String newCommentText) {
        this.newCommentText = newCommentText;
    }

    /**
     * @return the allCommentsCount
     */
    public Long getAllCommentsCount() {
        return allCommentsCount;
    }

    /**
     * @param allCommentsCount the allCommentsCount to set
     */
    public void setAllCommentsCount(Long allCommentsCount) {
        this.allCommentsCount = allCommentsCount;
    }

    /**
     * @return the pages
     */
    public List<Integer> getPages() {
        
        pages = new ArrayList<>();
        int lastPage =(int)( allCommentsCount % pageSize == 0 ? allCommentsCount / pageSize : allCommentsCount / pageSize + 1);
        
        if(allCommentsCount <= pageSize * 5){
            for(int i = 1; i < lastPage + 1; i++) pages.add(i);
            return pages;    
        }
        
        if(curPage < 4){
            pages.add(1);
            pages.add(2);
            pages.add(3);
            pages.add(4);
            pages.add(-1);
            pages.add(lastPage);
            return pages;
        }
        
        if(curPage < lastPage-2){
            pages.add(1);
            pages.add(-1);
            pages.add(curPage-1);
            pages.add(curPage);
            pages.add(curPage + 1);
            pages.add(-1);
            pages.add(lastPage);
            return pages;
        }
        
        pages.add(1);
        pages.add(-1);
        pages.add(lastPage-3);
        pages.add(lastPage-2);
        pages.add(lastPage-1);
        pages.add(lastPage);
        
        return pages;
        
    }

    /**
     * @param pages the pages to set
     */
    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    /**
     * @return the curPage
     */
    public int getCurPage() {
        return curPage;
    }

    /**
     * @param curPage the curPage to set
     */
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }


    public Comment getSelectedComment() {
        return selectedComment;
    }

    public void setSelectedComment(Comment selectedComment) {
        this.selectedComment = selectedComment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
