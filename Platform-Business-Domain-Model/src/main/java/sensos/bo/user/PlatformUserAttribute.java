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
package sensos.bo.user;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sensos
 */
@Entity
@Table(name = "PlatformUserAttribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlatformUserAttribute.findAll", query = "SELECT u FROM PlatformUserAttribute u"),
    @NamedQuery(name = "PlatformUserAttribute.findById", query = "SELECT ua FROM PlatformUserAttribute ua WHERE ua.id = :id"),
    @NamedQuery(name = "PlatformUserAttribute.findByUser", query = "SELECT ua FROM PlatformUserAttribute ua WHERE ua.userId = :userId")})
public class PlatformUserAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    Long userId;
    String name;
    String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "sensos.bo.UserAttribute[ id=" + id + "]";
    }

}
