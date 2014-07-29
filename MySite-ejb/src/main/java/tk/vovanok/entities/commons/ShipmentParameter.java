package tk.vovanok.entities.commons;

import java.io.Serializable;
import java.util.Objects;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Named
public class ShipmentParameter implements Serializable{

    @Column(name="PARAMETER_NAME")
    private String name;
    
    @Column(name="PARAMETER_ARG")
    private String argument;
    
    @Column(name="PARAMETER_MULTY")
    private boolean multy;

    @Column(name="PARAM_ID")
    private int id;

    public ShipmentParameter(String name, String argument, int id) {
        this.name = name;
        this.argument = argument;
        this.id = id;
    }

    public ShipmentParameter() {
        name = "";
        argument = "";
    }
    
    @Override
    public int hashCode() {
        return name.hashCode() ^ argument.hashCode();
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShipmentParameter other = (ShipmentParameter) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.argument, other.argument);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getId() {
        return Integer.toHexString(id);
    }
    
    public int getIdNum() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean isMulty() {
        return multy;
    }

    public void setMulty(boolean multy) {
        this.multy = multy;
    }

}
