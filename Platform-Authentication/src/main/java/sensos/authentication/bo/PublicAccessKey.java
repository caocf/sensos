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
import java.security.spec.RSAPublicKeySpec;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author sensos
 */
@Entity
@Table(name = "PublicAccessKey")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "PublicAccessKey.findAll",     query =    "SELECT ak FROM PublicAccessKey ak"),
@NamedQuery(name = "PublicAccessKey.findById",    query =    "SELECT ak FROM PublicAccessKey ak WHERE ak.id = :id"),
@NamedQuery(name = "PublicAccessKey.findByKey",   query =    "SELECT ak FROM PublicAccessKey ak WHERE ak.accessKey = :accessKey"),
})
public class PublicAccessKey implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //Id
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    Long id;
    Long deviceId;
    String accessKey;
    String secretAccessKey;
    
    @Transient
    transient RSAPublicKeySpec pub;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public void setRSAPublicKeySpec(RSAPublicKeySpec pub) {
        this.pub=pub;
    }
    
    public RSAPublicKeySpec getRSAPublicKeySpec() {
        return pub;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof PublicAccessKey)) {
            return false;
        }
        PublicAccessKey other = (PublicAccessKey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sensos.bo.PublicAccessKey[ id=" + id + ", PublicAccessKey=" + accessKey +" ]";
    }

    
    
}