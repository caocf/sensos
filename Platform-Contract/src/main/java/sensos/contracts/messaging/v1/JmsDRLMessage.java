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
package sensos.contracts.messaging.v1;

import java.io.Serializable;
import sensos.contracts.messaging.PayloadRequest;

/**
 *
 * @author sensos
 */
public class JmsDRLMessage  implements Serializable, PayloadRequest {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7737478436052664352L;

	public enum Method  { Logon, Logoff };
    public enum Channel { Web, Mobile };
    
    String remoteUser;
    private Method method;
    private Channel channel;
    
    @Override
    public JsonAccessKeyElement getAccessKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setChannel(Channel channel) {
        this.channel=channel;
    }

    public void setMethod(Method method) {
        this.method=method;
    }
    
    public void setRemoteUser(String remoteUser) {
        this.remoteUser=remoteUser;
    }

    public Method getMethod() {
        return method;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    
    
    
}
