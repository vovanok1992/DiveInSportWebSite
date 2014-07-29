/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import tk.vovanok.models.AdminCommentsEditorModel;
import tk.vovanok.dao.CommentDao;
import tk.vovanok.entities.Comment;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class CommentsTableBean implements Serializable{
    @EJB
    private CommentDao commentDao;
    
    private Long userId;
    
    private LazyDataModel<Comment> lazyModel;
    private Comment selectedComment;

    public LazyDataModel<Comment> getLazyModel() {
        if (lazyModel == null) {
            lazyModel = new AdminCommentsEditorModel();
            ((AdminCommentsEditorModel) lazyModel).setCommentDao(commentDao);
            ((AdminCommentsEditorModel) lazyModel).setUserId(userId);
            ((AdminCommentsEditorModel) lazyModel).updateRowCount();
        }
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Comment> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Comment getSelectedComment() {
       
        return selectedComment;
    }

    public void setSelectedComment(Comment selectedComment) {
        this.selectedComment = selectedComment;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
