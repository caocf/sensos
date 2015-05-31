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
package sensos.platform.rest.jms;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensos.contracts.messaging.PayloadRequest;
import sensos.contracts.messaging.PlatformMessageRequest;
import sensos.contracts.messaging.PlatformMessageResponse;
import sensos.contracts.messaging.v1.JsonRegisterRequest;
import sensos.util.misc.Timer;


/**
 *
 * @author sensos
 */
public class RestLetterChannel implements Runnable {

    private static final HashMap<String, PlatformMessageRequest> platformRequests = new HashMap<>();

    private final int CLEAN_TIME;
    boolean isRunning = true;

    /**
     *
     * @param cleanTime
     */
    RestLetterChannel(final int cleanTime) {
        CLEAN_TIME = cleanTime;
    }

    /**
     * Represents a cleaner for dead letters.
     */
    @Override
    public void run() {

        while (isRunning) {

            synchronized (platformRequests) {

                Set<String> keys = platformRequests.keySet();

                if (keys != null) {

                    Date now = new Date();

                    for (int i = 0; i < keys.size(); i++) {

                        PlatformMessageRequest request = platformRequests.get(i);

                        Date then = request.getPlatformReceived();

                        if (Timer.secondsBetween(now, then) > CLEAN_TIME) {

                            platformRequests.remove(i--);

                        }

                    }

                }

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RestLetterChannel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        platformRequests.clear();

    }

    public void putRequest(PlatformMessageRequest request) {

        if (request == null) {
            throw new IllegalStateException("Incoming PlatformMessageRequest cannot be null.");
        }

        synchronized (platformRequests) {

            platformRequests.put(request.getId(), request);

        }

    }

    public void close() {

        isRunning = false;

    }

    /**
     * 
     * @param remoteAddr
     * @return 
     */
    public static JsonRegisterRequest peekRegisterRequest(String remoteAddr) {

        synchronized (platformRequests) {

            for (PlatformMessageRequest r : platformRequests.values()) {

                PayloadRequest payload = r.getPayload();

                if (payload instanceof JsonRegisterRequest) {

                    JsonRegisterRequest registerRequest = (JsonRegisterRequest) payload;

                    if (registerRequest.getRemoteAddr() != null
                            && registerRequest.getRemoteAddr().equals(remoteAddr)) {
                        return registerRequest;
                    }

                }

            }

        }

        return null;

    }

    /**
     * 
     * @param remoteAddr
     * @return 
     */
    public static PlatformMessageResponse acceptRegisterRequest(String remoteAddr) {

        synchronized (platformRequests) {
            
            for (PlatformMessageRequest r : platformRequests.values()) {

                PayloadRequest payload = r.getPayload();

                if (payload instanceof JsonRegisterRequest) {

                    JsonRegisterRequest registerRequest = (JsonRegisterRequest) payload;

                    if (registerRequest.getRemoteAddr() != null
                            && registerRequest.getRemoteAddr().equals(remoteAddr)) {
                        
                        PlatformMessageResponse platformMessageResponse = new PlatformMessageResponse();
                        platformMessageResponse.setOriginalId(r.getId());
                        
                        return platformMessageResponse;
                        
                    }

                }

            }
            
        }
        
        return null;
        
    }

}
