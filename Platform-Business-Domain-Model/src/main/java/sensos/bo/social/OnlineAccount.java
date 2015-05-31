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
package sensos.bo.social;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import sensos.bo.user.PlatformUser;

/**
 *
 * @author joeri
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "OnlineAccount.findByAppKeyAndNetworkAndAccountId",
            query = "SELECT oa FROM OnlineAccount oa WHERE oa.appKey = :appKey AND oa.network = :network AND oa.accountId = :accountId ORDER BY oa.creationDate DESC"),
    @NamedQuery(name = "OnlineAccount.findByAppKeyAndUser",
            query = "SELECT oa FROM OnlineAccount oa WHERE oa.appKey = :appKey AND oa.user = :user ORDER BY oa.creationDate DESC"),
    @NamedQuery(name = "OnlineAccount.findByUserAndNetwork",
            query = "SELECT oa FROM OnlineAccount oa WHERE oa.user = :user AND oa.network = :network ORDER BY oa.creationDate DESC")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class OnlineAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long creationDate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private PlatformUser user;
    @Lob
    private String profileLink;
    @OneToOne
    private ExternalToken accessToken;
    private String network;
    private String accountId;
    private String appKey;

    @XmlTransient
    public ExternalToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(ExternalToken accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

    @XmlTransient
    public PlatformUser getUser() {
        return user;
    }

    public void setUser(PlatformUser user) {
        this.user = user;
    }

}
