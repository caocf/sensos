/**
 *  Sensos IoT Platform.
 *  Copyright (C) 2015 sensos
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sensos.authentication.bo;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PrivateAccessKey")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "PrivateAccessKey.findAll",    query =    "SELECT ak FROM PrivateAccessKey ak"),
@NamedQuery(name = "PrivateAccessKey.findById",   query =    "SELECT ak FROM PrivateAccessKey ak WHERE ak.id = :id")})
@NamedQuery(name = "PrivateAccessKey.findByKey",  query =    "SELECT ak FROM PrivateAccessKey ak WHERE ak.accessKey = :accessKey")
public class PrivateAccessKey implements Serializable {

    private static final long serialVersionUID = 1L;

    //Id
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    //@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQMYCLASSID")
    //@SequenceGenerator(name="SEQMYCLASSID", sequenceName="SEQMYCLASSID")
    private Long id;
    
    //Public key that is stored in the database.
    @Lob
    private String privateKey;
    
    @Lob
    private String privateExponent;

    /**
     * This accessKey is shared between device and the platform.
     */
    @Lob
    private String accessKey;

    private Date created;

    private boolean expired;

    private Long deviceId;
    
    private String mode;
    
    public PrivateAccessKey() {

        accessKey = UUID.randomUUID().toString().replaceAll("-", "");
        created = new Date( System.currentTimeMillis() );
        
    }
    
    public PrivateAccessKey( Long id ) {
    
        this.id = id;
        
    }
    
    public PrivateAccessKey( String PrivateAccessKey) {
        
        this.accessKey = PrivateAccessKey;
        
    }

    public String getAccessKey() {
        return accessKey;
    }
    
    public void setAccessKey(String accessKey) {
        this.accessKey=accessKey;
    }

    public void getAccessKey(String PrivateAccessKey) {
        this.accessKey = PrivateAccessKey;
    }
    
    public String getPrivateExponent() {
        return privateExponent;
    }

    public void setPrivateExponent(String privateExponent) {
        this.privateExponent = privateExponent;
    }
    
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrivateAccessKey)) {
            return false;
        }
        PrivateAccessKey other = (PrivateAccessKey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sensos.bo.PrivateAccessKey[ id=" + id + ", PrivateAccessKey=" + accessKey +" ]";
    }

    public void setMode(String mode) {
        this.mode=mode;
    }
    
    public String getMode() {
        return mode;
    }
    
    
    
}