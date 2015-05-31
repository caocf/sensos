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

import java.sql.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import sensos.bo.user.PlatformUser;

/**
 *
 * @author joeri
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Friend extends PlatformUser {

        private static final long serialVersionUID = 1L;
    
    private String network;
	private String remoteId;
	private String uri;

	@Override
	@XmlElement
	public Date getCreationDate() {
		return super.getCreationDate();
	}

	@Override
	public void setCreationDate(java.util.Date creationDate) {
		super.setCreationDate(creationDate);
	}

	@Override
	@XmlElement
	public String getDescription() {
		return super.getDescription();
	}

	@Override
	public void setDescription(String depiction) {
		super.setDescription(depiction);
	}

	@Override
	@XmlElement
	public String getFirstName() {
		return super.getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		super.setFirstName(firstName);
	}

	@Override
	@XmlElement
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Override
	@XmlElement
	public String getLastName() {
		return super.getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		super.setLastName(lastName);
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getRemoteId() {
		return remoteId;
	}

	public void setRemoteId(String remoteId) {
		this.remoteId = remoteId;
	}

	@Override
	@XmlElement
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public void setUsername(String screenName) {
		super.setUsername(screenName);
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "Friend{" + "network=" + network + ", remoteId=" + remoteId + ", username=" + getUsername() + '}';
	}
}