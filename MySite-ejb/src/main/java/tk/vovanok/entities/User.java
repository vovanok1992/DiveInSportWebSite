/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import tk.vovanok.entities.commons.AditionalParameter;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity{
    
    @Column(name = "LOGIN")
    private String login;
    
    @Column(name = "PASS")
    private String password;
    
    @Column(name = "IP_ADD")
    private String ipAddress;
    
    @Column(name = "USER_ACCESS_LEVEL")
    private int accessLevel;
    
    @ElementCollection
    @JoinTable(name = "USER_ADITIONAL_INFO",
            joinColumns = @JoinColumn(name = "USER_ID")
    )
    private Collection<AditionalParameter> aditionalInfo;

   
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id.intValue();
        hash = 53 * hash + Objects.hashCode(this.login);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public Collection<AditionalParameter> getAditionalInfo() {
        return aditionalInfo;
    }

    public void setAditionalInfo(Collection<AditionalParameter> aditionalInfo) {
        this.aditionalInfo = aditionalInfo;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
