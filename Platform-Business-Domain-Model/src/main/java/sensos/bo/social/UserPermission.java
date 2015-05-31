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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sensos.bo.user.PlatformUser;

/**
 * A user permission is a permission that is granted to a user or group to allow
 * a certain action on a specific user entity. An example can be to allow a user
 * or group to remove (action) a specific user (target user).
 *
 * @author joeri
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "UserPermission.findByUserAndTargetAndAction",
            query = "SELECT up FROM UserPermission up WHERE up.user = :user AND up.target = :target AND up.action = :action"
    ),
    @NamedQuery(
            name = "UserPermission.findByUserAndTarget",
            query = "SELECT up FROM UserPermission up WHERE up.user = :user AND up.target = :target"
    ),
    @NamedQuery(
            name = "UserPermission.findByUser",
            query = "SELECT up FROM UserPermission up WHERE up.user = :user"
    ),
    @NamedQuery(
            name = "UserPermission.findByGroupAndTargetAndAction",
            query = "SELECT up FROM UserPermission up WHERE up.group = :group AND up.target = :target AND up.action = :action"
    ),
    @NamedQuery(
            name = "UserPermission.findByGroupAndTarget",
            query = "SELECT up FROM UserPermission up WHERE up.group = :group AND up.target = :target"
    ),
    @NamedQuery(
            name = "UserPermission.findByGroup",
            query = "SELECT up FROM UserPermission up WHERE up.group = :group"
    ),
    @NamedQuery(
            name = "UserPermission.findByTarget",
            query = "SELECT up FROM UserPermission up WHERE up.target = :target"
    )
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class UserPermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private PlatformUser user;
    @ManyToOne
    private Group group;
    @ManyToOne
    private PlatformUser target;
    @Column(name = "PERMISSIONACTION")
    private int action;

    @Transient
    private Long targetId;

    @Transient
    private Authorizable authorizable;

    /**
     * The action associated with this user permission.
     *
     * @return the action of the user permission
     */
    public int getAction() {
        return action;
    }

    /**
     * Sets the action associated with this user permission.
     *
     * @param action the action of the user permission
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * The group that was granted this user permission. If the permission was
     * instead granted to a user, then the group will be <code>null</code>.
     *
     * @return the group that was granted the user permission
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Grants the user permission to the specified group.
     *
     * @param group the group that this user permission will be granted to
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * The unique identifier automatically generated by the persistence
     * provider.
     *
     * @return unique identifier of the user permission
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the user permission. Note that this method
     * should never be called by developers as it will be automatically set by
     * the underlying persistence provider.
     *
     * @param id the unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The user that was granted this user permission. If the permission was
     * instead granted to a group, then the user will be <code>null</code>.
     *
     * @return the user that was granted the user permission
     */
    public PlatformUser getUser() {
        return user;
    }

    /**
     * Grants the user permission to the specified user.
     *
     * @param user the user that this user permission will be granted to
     */
    public void setUser(PlatformUser user) {
        this.user = user;
    }

    /**
     * The user that this user permission was created for.
     *
     * @return the user for which the user permission is created
     */
    public PlatformUser getTarget() {
        return target;
    }

    /**
     * Sets the user where this user permission is created for.
     *
     * @param target the user on which the user permission is created
     */
    public void setTarget(PlatformUser target) {
        this.target = target;
    }

    /**
     * The id of the target user.
     *
     * @return the id the target user
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * Sets the id of the target user. This method should not be called directly
     * by developers as the field will be set automatically by taking the id of
     * the target user field.
     *
     * @param targetId the id of the target user
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    /**
     * An authorizable that represents either the user or group that this user
     * permission was granted to.
     *
     * @return an authorizable representing the entity this user permission was
     * granted to
     */
    public Authorizable getAuthorizable() {
        return authorizable;
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    public void postLoad() {
        authorizable = new Authorizable();
        if (group != null) {
            authorizable.setId(group.getId());
            authorizable.setUid(group.getUid());
            authorizable.setType("group");
        }
        if (user != null) {
            authorizable.setId(user.getId());
            authorizable.setUid(user.getUsername());
            authorizable.setType("user");
        }
        if (target != null) {
            setTargetId(target.getId());
        }
    }
}
