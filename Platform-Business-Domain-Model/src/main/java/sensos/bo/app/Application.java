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
package sensos.bo.app;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Application")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a"),
    @NamedQuery(name = "Application.findById", query = "SELECT a FROM Application a WHERE a.id = :id"),
    @NamedQuery(name = "Application.findByCreator", query = "SELECT a FROM Application a WHERE a.creator = :creator"),
    @NamedQuery(name = "Application.findApplicationIds", query = "SELECT a.id FROM Application a WHERE a.creator = :creator"),
    @NamedQuery(name = "Application.findByDisplayName", query = "SELECT a FROM Application a WHERE a.urlName = :urlName")
})

public class Application implements Serializable {

    private static final long serialVersionUID = 1L;

    public Application() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * This id will be used as application displayname
     * http://www.sensos.co.uk/application/mycoolapp where mycoolapp is the
     * applicationId.
     */
    private Long creator;

    private Long version;

    private String displayname;

    private String urlName;

    private String description;

    @Lob
    private byte[] application;

    private Date creationDate;

    private Date modificationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlName() {
        return this.urlName;
    }

    public String getApplicationId() {
        return this.urlName;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = new Date(creationDate.getTime());;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(java.util.Date modificationDate) {
        this.modificationDate = new Date(modificationDate.getTime());
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    private boolean visible;

    public byte[] getApplication() {
        return application;
    }

    public void setApplication(byte[] application) {
        this.application = application;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayname;
    }

    public void setDisplayName(String displayName) {
        this.displayname = displayName;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersion() {
        return version;
    }

    public String toString() {
        return "sensos.bo.Application[ id=" + id + "]";
    }

}
