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
import java.sql.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "PlatformUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlatformUser.findById", query = "SELECT u FROM PlatformUser u WHERE u.id = :id"),
    @NamedQuery(name = "PlatformUser.findByUsername", query = "SELECT u FROM PlatformUser u WHERE u.username = :username")})
public class PlatformUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public PlatformUser() { }

    /**
     * Used for querying User.
     *
     * @param userId
     */
    public PlatformUser(Long userId) {

        this.id = userId;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private String uid;

    private String email;

    private String name;

    private String passwordHash;

    private String firstName;

    private String lastName;

    private boolean nameVisible;

    private boolean emailVisible;

    private boolean userEnabled;

    private Date creationDate;

    private Date modificationDate;

    private Date lastLoggedIn;

    private Date lastProfileUpdate;

    private boolean external;

    private boolean federated;

    private boolean visible;

    private int status;

    private String description;

    private String country;

    @Transient
    private String ip_address;

    public enum Status implements Serializable {

        Approved(1);

        int status;

        Status(int status) {
            this.status = status;
        }

        public int getStatus() {
            return status;
        }

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = new Date(creationDate.getTime());
    }

    public boolean isEmailVisible() {
        return emailVisible;
    }

    public void setEmailVisible(boolean emailVisible) {
        this.emailVisible = emailVisible;
    }

    public boolean isExternal() {
        return external;
    }

    public void setExternal(boolean external) {
        this.external = external;
    }

    public boolean isFederated() {
        return federated;
    }

    public void setFederated(boolean federated) {
        this.federated = federated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(java.util.Date lastLoggedIn) {
        if(lastLoggedIn==null)
            return;
        else
            this.lastLoggedIn = new Date(lastLoggedIn.getTime());;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getLastProfileUpdate() {
        return lastProfileUpdate;
    }

    public void setLastProfileUpdate(java.util.Date lastProfileUpdate) {
        this.lastProfileUpdate = new Date(lastProfileUpdate.getTime());;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(java.util.Date modificationDate) {
        this.modificationDate = new Date(modificationDate.getTime());;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNameVisible() {
        return nameVisible;
    }

    public void setNameVisible(boolean nameVisible) {
        this.nameVisible = nameVisible;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Status getStatus() {

        for (Status s : Status.values()) {
            if (s.getStatus() == status) {
                return s;
            }
        }
        return null;

    }

    public void setStatus(Status status) {
        this.status = status.getStatus();
    }

    public boolean isUserEnabled() {
        return userEnabled;
    }

    public void setUserEnabled(boolean userEnabled) {
        this.userEnabled = userEnabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    @Override
    public String toString() {
        return "sensos.bo.User[ id=" + id + "]";
    }

}
