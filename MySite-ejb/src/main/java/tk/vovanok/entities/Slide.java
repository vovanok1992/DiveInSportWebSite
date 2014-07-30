
package tk.vovanok.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import tk.vovanok.entities.BaseEntity;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Entity
@Table(name = "SLIDER")
public class Slide extends BaseEntity{
    
    @Column(name = "SLIDER_ID")
    private Long sliderId;
    
    @Column(name = "TEXT")
    private String text;
    
    @Column(name = "LINK")
    private String link;
    
    @Column(name = "IMAGE")
    private String imagePath;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.imagePath);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Slide other = (Slide) obj;
        if (!Objects.equals(this.imagePath, other.imagePath)) {
            return false;
        }
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }
    


    public Long getSliderId() {
        return sliderId;
    }

    public void setSliderId(Long sliderId) {
        this.sliderId = sliderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
}
