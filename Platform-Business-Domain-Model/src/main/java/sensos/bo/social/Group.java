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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sensos.bo.user.PlatformUser;

/**
 * A Group in its most basic usage is a collection of other DaliCore entities.
 * A group has the following relations with the other DaliCore entities:
 *
 * <ul>
 *   <li>parent: a group can be specified as the parent of another Group</li>
 *   <li>child: a group can therefore also be the child of another Group</li>
 *   <li>group: a group can be a collection of Content</li>
 *   <li>group: a group can have a Member relation with one or more Users</li>
 *   <li>authorizable: a group can be used in all permissions as the authorizable</li>
 *   <li>permission target: a group can be the target of a GroupPermission</li>
 * </ul>
 *
 * @author joeri
 */
@Entity
@Table(
	name = "DALIGROUP",
	uniqueConstraints = @UniqueConstraint(
		columnNames = { "appKey", "uid" }
	)
)
@NamedQueries({
  @NamedQuery(
    name = "Group.findByAppKey",
    query = "SELECT g FROM Group g WHERE g.appKey = :appKey"
  ),
  @NamedQuery(
    name = "Group.findByUid",
    query = "SELECT g FROM Group g WHERE g.uid = :uid and g.appKey = :appKey"
  ),
  @NamedQuery(
    name = "Group.count",
    query = "SELECT COUNT(g) FROM Group g WHERE g.appKey = :appKey"
  )
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Group implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private int type;

  @ManyToOne
  private PlatformUser author;
  @Transient
  private Long authorId;

  private long creationDate;
  private long modificationDate;

  @ManyToOne
  private Group parent;
  @Transient
  private Long parentId;
	@Column(name = "PARENTPATH")
	private String path;
	@Column(name = "PARENTDEPTH")
	private int depth;

	private String uid;

  @OneToMany(mappedBy= "group", orphanRemoval = true, cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
  private List<TypePermission> typePermissions;

  @OneToMany(mappedBy= "group", orphanRemoval = true, cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
  private List<ContentPermission> contentPermissions;

  @OneToMany(mappedBy= "group", orphanRemoval = true, cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
  private List<GroupPermission> groupPermissions;

  @OneToMany(mappedBy= "target", orphanRemoval = true, cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
  private List<GroupPermission> targetGroupPermissions;

  @OneToMany(mappedBy= "group", orphanRemoval = true, cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
  private List<UserPermission> userPermissions;

	@OneToMany(mappedBy = "group", orphanRemoval = true, cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
	private List<Member> memberships;

  @OneToMany(mappedBy = "group", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Field> fields;

  private String appKey;

	/**
	 * The unique identifier automatically generated by the persistence provider.
	 *
	 * @return unique identifier of the group
	 */
  public Long getId() {
    return id;
  }

	/**
	 * Sets the unique identifier for the group. Note that this method should
	 * never be called by developers as it will be automatically set by the
	 * underlying persistence provider.
	 *
	 * @param id the unique identifier
	 */
  public void setId(Long id) {
    this.id = id;
  }

	/**
	 * The author of the group.
	 *
	 * @return the group's author
	 */
  public PlatformUser getAuthor() {
    return author;
  }

	/**
	 * Sets the author for this group.
	 *
	 * @param author the author of the group
	 */
  public void setAuthor(PlatformUser author) {
    this.author = author;
  }

	/**
	 * The id of the author of the group.
	 *
	 * @return the id of the group's author
	 */
  public Long getAuthorId() {
    return authorId;
  }

	/**
	 * Sets the id of the author of the group. This method should not be called
	 * directly by developers as the field will be set automatically by taking
	 * the id of the author field.
	 *
	 * @param authorId the id of the author of the group
	 */
  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }

	/**
	 * The time in milliseconds when the group was created.
	 *
	 * @return the time in milliseconds since midnight, January 1, 1970 UTC
	 */
  public long getCreationDate() {
    return creationDate;
  }

	/**
	 * Sets the time when the group was created. Note that the creation date will
	 * be automatically set if it was not provided when persisting the group.
	 *
	 * @param creationDate the time in milliseconds when the group was created
	 */
  public void setCreationDate(long creationDate) {
    this.creationDate = creationDate;
  }

	/**
	 * The time in milliseconds when the group was last updated.
	 *
	 * @return the time in milliseconds since midnight, January 1, 1970 UTC
	 */
  public long getModificationDate() {
    return modificationDate;
  }

	/**
	 * Sets the time when the group was last updated. This method has no meaning
	 * for developers as the modification date will be automatically set by
	 * the persistence provider.
	 *
	 * @param modificationDate the time in milliseconds when the group was last updated
	 */
  public void setModificationDate(long modificationDate) {
    this.modificationDate = modificationDate;
  }

	/**
	 * The name of the group.
	 *
	 * @return the group's name
	 */
  public String getName() {
    return name;
  }

	/**
	 * Defines the name for the group.
	 *
	 * @param name the name of the group
	 */
  public void setName(String name) {
    this.name = name;
  }

	/**
	 * The parent of the group.
	 *
	 * @return the group's parent
	 */
  public Group getParent() {
    return parent;
  }

	/**
	 * Sets the parent for this group.
	 *
	 * @param parent the parent of the group
	 */
  public void setParent(Group parent) {
    this.parent = parent;
  }

	/**
	 * The id of the parent of the group.
	 *
	 * @return the id of the group's parent
	 */
  public Long getParentId() {
    return parentId;
  }

	/**
	 * Sets the id of the parent of the group. This method should not be called
	 * directly by developers as the field will be set automatically by taking
	 * the id of the parent field.
	 *
	 * @param parentId the id of the parent of the group
	 */
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

	/**
	 * The complete path of the group.
	 *
	 * @return the group's path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets the complete path of the group. A path consists of the path of the
	 * parent group extended with a / character and the id of the parent. A group
	 * without a parent will have an empty string as path. The path can thus be
	 * used to reconstruct the entire parent structure of the group. Please note
	 * that the path of a group and the entire subtree of that group will be set
	 * automatically when creating, updating or removing the group.
	 *
	 * @param path the path of the group
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * The depth of the group in relation to it's parent tree.
	 *
	 * @return the number of parents above the group
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * Sets the depth of the group. The depth is the number of parents that
	 * exist from the parent of this group till the root parent. As such, a group
	 * without a parent will have a depth of 0. Please note that the depth of a
	 * group and the entire subtree of that group will be set automatically when
	 * creating, updating or removing the group.
	 *
	 * @param depth the depth of the group
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

  /**
	 * The unique identifier for this group.
	 *
   * @return the group's unique identifier
   */
  public String getUid() {
    return uid;
  }

  /**
	 * A unique identifier that can optionally be set by an application. In
	 * contrast to the {@link #setId(java.lang.Long) id}, this value can be
	 * custom provided by an application. The only requirement is that the
	 * value needs to be unique over all groups. When the <code>uid</code> is not
	 * provided when persisting a group, then it will be set automatically to a
	 * {@link java.util.UUID#randomUUID() UUID}.
	 *
   * @param uid the unique String identifier of the group
   */
  public void setUid(String uid) {
    this.uid = uid;
  }

	/**
	 * The type of the group.
	 *
	 * @return the group's type
	 */
  public int getType() {
    return type;
  }

	/**
	 * Sets the type of the group. A type can be used to categorize a list of
	 * groups that define the same logical entity.
	 *
	 * @param type the type of the group
	 */
  public void setType(int type) {
    this.type = type;
  }

	/**
	 * All the memberships of the group.
	 *
	 * @return all memberships of the group
	 */
	public List<Member> getMemberships() {
		return memberships;
	}

	/**
	 * Sets the memberships of the group. Instead of using this method,
	 * controlling the memberships of a group should be done by using the
	 * {@link com.lodgon.dali.core.ejb.GroupBean#join(int, int) join} and
	 * {@link com.lodgon.dali.core.ejb.GroupBean#leave(int, int) leave} methods.
	 *
	 * @param memberships the memberships for the group
	 */
	public void setMemberships(List<Member> memberships) {
		this.memberships = memberships;
	}

	/**
	 * The application key that is associated with this group.
	 *
	 * @return the group's application key
	 */
  public String getAppKey() {
    return appKey;
  }

	/**
	 * Sets the application key associated with the group. The application key
	 * defines the application scope of the group object. By setting an
	 * application key, this group can only interact with other entities within
	 * the same application scope.
	 *
	 * @param appKey the application key to set
	 */
  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

	/**
	 * A list of content permissions that were granted to this group.
	 *
	 * @return all content permissions granted to the group
	 */
  public List<ContentPermission> getContentPermissions() {
    return contentPermissions;
  }

	/**
	 * Sets the list of content permissions that were granted to this group.
	 *
	 * @param contentPermissions a list of content permissions granted to this group.
	 */
  public void setContentPermissions(List<ContentPermission> contentPermissions) {
    this.contentPermissions = contentPermissions;
  }

	/**
	 * A list of group permissions that were granted to this group.
	 *
	 * @return all group permissions granted to the group
	 */
  public List<GroupPermission> getGroupPermissions() {
    return groupPermissions;
  }

	/**
	 * Sets the list of group permissions that were granted to this group.
	 *
	 * @param groupPermissions a list of group permissions granted to this group.
	 */
  public void setGroupPermissions(List<GroupPermission> groupPermissions) {
    this.groupPermissions = groupPermissions;
  }

	/**
	 * A list of group permissions that were set on this group.
	 *
	 * @return all group permissions set on this group
	 */
  public List<GroupPermission> getTargetGroupPermissions() {
    return targetGroupPermissions;
  }

	/**
	 * Sets the list of group permissions of which this group is the target.
	 *
	 * @param targetGroupPermissions a list of group permissions set on this group
	 */
  public void setTargetGroupPermissions(List<GroupPermission> targetGroupPermissions) {
    this.targetGroupPermissions = targetGroupPermissions;
  }

	/**
	 * A list of type permissions that were granted to this group.
	 *
	 * @return all type permissions granted to the group
	 */
  public List<TypePermission> getTypePermissions() {
    return typePermissions;
  }

	/**
	 * Sets the list of type permissions that were granted to this group.
	 *
	 * @param typePermissions a list of type permissions granted to this group.
	 */
  public void setTypePermissions(List<TypePermission> typePermissions) {
    this.typePermissions = typePermissions;
  }

	/**
	 * A list of user permissions that were granted to this group.
	 *
	 * @return all user permissions granted to the group
	 */
  public List<UserPermission> getUserPermissions() {
    return userPermissions;
  }

	/**
	 * Sets the list of user permissions that were granted to this group.
	 *
	 * @param userPermissions a list of user permissions granted to this group.
	 */
  public void setUserPermissions(List<UserPermission> userPermissions) {
    this.userPermissions = userPermissions;
  }

	/**
	 * A list of custom fields defined for this group.
	 *
	 * @return the custom fields of the group
	 */
  public List<Field> getFields() {
    return fields;
  }

	/**
	 * Sets the list of custom fields to be defined for this group.
	 *
	 * @param fields the complete list of custom fields
	 */
  public void setFields(List<Field> fields) {
    this.fields = fields;
  }

	/**
	 * Convenience method for quickly linking fields to a user. When more
	 * advanced management of fields is needed, one can always use
	 * {@link com.lodgon.dali.core.ejb.FieldBean FieldBean} instead.
	 *
	 * @param field the new field to be added
	 */
  public void addField(Field field) {
    if (fields == null) {
      fields = new LinkedList<Field>();
    }
    fields.add(field);
  }

	/**
	 * Convenience method for quickly updating existing fields. All fields
	 * that have a key that matches the specified name, will be updated by storing
	 * the new value. When more advanced management of fields is needed, one can
	 * always use {@link com.lodgon.dali.core.ejb.FieldBean FieldBean} instead.
	 *
	 * @param name the name of the field(s) to be updated
	 * @param newValue the new value to be set on the field
	 */
  public void updateField(String name, String newValue) {
    if (fields != null) {
			for (Field field : fields) {
        if (field.getName().equals(name)) {
					field.setValue(newValue);
				}
			}
		}
  }

	/**
	 * Convenience method for quickly removing a field. All fields that
	 * have a key that matches the specified name, will be removed. When more
	 * advanced management of fields is needed, one can always use
	 * {@link com.lodgon.dali.core.ejb.FieldBean FieldBean} instead.
	 *
	 * @param name the name of the field(s) to be removed
	 */
  public void removeField(String name) {
    if (fields != null) {
      for (Iterator<Field> fieldsIter = fields.iterator(); fieldsIter.hasNext();) {
        Field field = fieldsIter.next();
        if (field.getName().equals(name)) {
          fieldsIter.remove();
        }
      }
    }
  }

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Group other = (Group) obj;
		if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
			return false;
		}
		if ((this.uid == null) ? (other.uid != null) : !this.uid.equals(other.uid)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

  @Override
  public String toString() {
    return "com.lodgon.dali.core.entity.Group[id=" + id + "]";
  }

  @PrePersist
  public void prePersist() {
    if (creationDate == 0) {
      this.creationDate = System.currentTimeMillis();
    }
    this.modificationDate = this.creationDate;

		if (parent != null) {
			path = parent.getPath().isEmpty() ? "/" + parent.getId() + "/" : parent.getPath() + parent.getId() + "/";
			depth = parent.getDepth() + 1;
		} else {
			path = "";
			depth = 0;
		}
  }

  @PreUpdate
  public void preUpdate() {
    this.modificationDate = System.currentTimeMillis();
  }

  @PostLoad
  @PostPersist
  @PostUpdate
  public void postLoad() {
    if (author != null) {
			authorId = author.getId();
		} else {
			authorId = null;
		}
    if (parent != null) {
			parentId = parent.getId();
		} else {
			parentId = null;
		}
  }

  protected void copyFrom (Group src) {
    setAppKey(src.getAppKey());
    setAuthor(src.getAuthor());
    setAuthorId(src.getAuthorId());
    setContentPermissions(src.getContentPermissions());
    setCreationDate(src.getCreationDate());
    setModificationDate(src.getModificationDate());
    setFields(src.getFields());
    setId(src.getId());
    setName(src.getName());
    setParent(src.getParent());
    setParentId(src.getParentId());
    setType(src.getType());
    setTypePermissions(src.getTypePermissions());
  }
}