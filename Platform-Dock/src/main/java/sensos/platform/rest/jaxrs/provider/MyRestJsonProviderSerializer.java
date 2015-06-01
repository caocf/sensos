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
package sensos.platform.rest.jaxrs.provider;

/**
 *
 * @author sensos
 */

/**
 * By the way: we are using Jackson 1.9.2 and we want to configure Jackson globally in our own @Provider class. 
 * Instead we could also use @Json* annotations, i.e. @JsonSerialize(include = Inclusion.NON_EMPTY). 
 * Inclusion.NON_EMPTY tells Jackson not to include null values or empty collections. 
 * That's it - the rest happens without any further configuration :-) 
 * Please check here in case you have multiple Providers for the same type.
 * 
 * @link http://stackoverflow.com/questions/9754733/jersey-jackson-multiple-providers-for-same-type
 * @author sensos
 */
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;


@Provider
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class MyRestJsonProviderSerializer implements ContextResolver<ObjectMapper> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {

      // since Jackson 1.9
      // this default configuration can be overwritten via annotations
      MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
      
    }

    public MyRestJsonProviderSerializer() {

        System.out.println("Initializing... " + MyRestJsonProviderSerializer.class.getName());
        
    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("MyJacksonJsonProviderSerializer.getContext(): "+type);
        return MAPPER;
    }

    public static ObjectMapper getMAPPER() {
        System.out.println("MyJacksonJsonProviderSerializer.getMapper(): " + MAPPER.toString());
        return MAPPER;
    }

}