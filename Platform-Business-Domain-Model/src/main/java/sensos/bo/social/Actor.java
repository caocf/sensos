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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import sensos.bo.user.PlatformUser;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Actor extends PlatformUser {

    private static final long serialVersionUID = 1L;
    private String remoteId;

    @Override
    @XmlElement
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    @XmlElement
    public String getFirstName() {
        return super.getFirstName();
    }

    public String getRemoteId() {
        return remoteId;
    }

    @Override
    @XmlElement
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    @XmlElement
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    @XmlElement
    public String getUid() {
        return super.getUid();
    }

    @Override
    public void setDescription(String depiction) {
        super.setDescription(depiction);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    @Override
    public void setUsername(String screenName) {
        super.setUsername(screenName);
    }

    @Override
    public void setUid(String uid) {
        super.setUid(uid);
    }

}
