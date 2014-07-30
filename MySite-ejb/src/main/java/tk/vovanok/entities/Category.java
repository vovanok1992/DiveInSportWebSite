package tk.vovanok.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "CATEGORIES")
public class Category extends BaseEntity {
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "PARENT_ID")
    private Long parentId;
        
    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj.getClass() != Category.class) return false;
        return Objects.equals(this.id, ((Category)obj).getId());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + id.intValue();
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + this.parentId.intValue();
        return hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
