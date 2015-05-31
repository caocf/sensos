/*
 * Copyright (c) 2009-2011 LodgON  (http://www.lodgon.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package sensos.bo.social;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An authorizable is a convenience class that is used in conjunction with the
 * different permission entities. A permission can be granted to an authorizable
 * which is either a user or a group. In order to easily get the id of the
 * entity which the permission was granted to without checking if either the
 * user or group has been set, you can use the <code>getAuthorizable</code>
 * method of a permission.
 *
 * @author erwin
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Authorizable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;
    private Long id;
    private String uid;

    /**
     * The unique identifier of the entity covered by the authorizable.
     *
     * @return unique identifier of the authorizable
     * @see com.lodgon.dali.core.entity.Group#getId()
     * @see com.lodgon.dali.core.entity.User#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the authorizable. This id maps with an id
     * of an existing user or group.
     *
     * @param id the unique identifier
     * @see com.lodgon.dali.core.entity.Group#setId(java.lang.Long)
     * @see com.lodgon.dali.core.entity.User#setId(java.lang.Long)
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The type of the authorizable which is <code>user</code> for users and
     * <code>group</code> for groups.
     *
     * @return the type of the authorizable
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the authorizable to be able to know which entity is
     * covered by the authorizable (a user or a group).
     *
     * @param type the type of the authorizable
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * The unique string identifier of the entity covered by the authorizable.
     *
     * @return unique string identifier of the authorizable
     * @see com.lodgon.dali.core.entity.Group#getUid()
     * @see com.lodgon.dali.core.entity.User#getUid()
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the unique string identifier for the authorizable. This uid maps
     * with a uid of an existing user or group.
     *
     * @param uid the unique string identifier
     * @see com.lodgon.dali.core.entity.Group#setUid(java.lang.String)
     * @see com.lodgon.dali.core.entity.User#setUid(java.lang.String)
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
    
}
