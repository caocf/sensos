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
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
 * The Content class is a very generic entity to store all kind of contents and
 * provides the fields that are common to most of these kinds. A content can
 * furthermore have the following relations with other DaliCore entities:
 *
 * <ul>
 * <li>parent: a content can be specified as the parent of another Content</li>
 * <li>child: a content can therefore also be the child of another Content</li>
 * <li>permission target: a group can be the target of a ContentPermission</li>
 * </ul>
 *
 * @author joeri
 */
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"appKey", "uid"}
        )
)

@NamedQueries({
    @NamedQuery(
            name = "Content.findByAppKey",
            query = "SELECT c FROM Content c WHERE c.appKey = :appKey"
    ),
    @NamedQuery(
            name = "Content.findByUid",
            query = "SELECT c FROM Content c WHERE c.uid = :uid and c.appKey = :appKey"
    ),
    @NamedQuery(
            name = "Content.count",
            query = "SELECT COUNT(c) FROM Content c WHERE c.appKey = :appKey"
    ),
    @NamedQuery(
            name = "Content.clearPosition",
            query = "UPDATE Content c SET c.weight = c.weight - 1 WHERE c.appKey = :appKey AND c.parent IS NULL AND c.weight > :oldPosition"
    ),
    @NamedQuery(
            name = "Content.lowerPosition",
            query = "UPDATE Content c SET c.weight = c.weight + 1 WHERE c.appKey = :appKey AND c.parent IS NULL AND c.weight < :oldPosition AND c.weight >= :newPosition"
    ),
    @NamedQuery(
            name = "Content.higherPosition",
            query = "UPDATE Content c SET c.weight = c.weight - 1 WHERE c.appKey = :appKey AND c.parent IS NULL AND c.weight > :oldPosition AND c.weight <= :newPosition"
    ),
    @NamedQuery(
            name = "Content.clearPositionByParent",
            query = "UPDATE Content c SET c.weight = c.weight - 1 WHERE c.appKey = :appKey AND c.parent = :parent AND c.weight > :oldPosition"
    ),
    @NamedQuery(
            name = "Content.lowerPositionByParent",
            query = "UPDATE Content c SET c.weight = c.weight + 1 WHERE c.appKey = :appKey AND c.parent = :parent AND c.weight < :oldPosition AND c.weight >= :newPosition"
    ),
    @NamedQuery(
            name = "Content.higherPositionByParent",
            query = "UPDATE Content c SET c.weight = c.weight - 1 WHERE c.appKey = :appKey AND c.parent = :parent AND c.weight > :oldPosition AND c.weight <= :newPosition"
    )
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uid;
    private String title;
    private long creationDate;
    private long modificationDate;
    private int type;
    @Lob
    private String content;
    @Lob
    private String summary;
    private int weight;

    @ManyToOne
    private PlatformUser author;
    @Transient
    private Long authorId;

    @ManyToOne
    private Group group;
    @Transient
    private Long groupId;

    @ManyToOne
    private Content parent;
    @Transient
    private Long parentId;
    @Column(name = "PARENTPATH")
    private String path;
    @Column(name = "PARENTDEPTH")
    private int depth;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> tags;

    private String appKey;

    @OneToMany(mappedBy = "content", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Field> fields;

    @OneToMany(mappedBy = "content", orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ContentPermission> contentPermissions;

    /**
     * The unique identifier automatically generated by the persistence
     * provider.
     *
     * @return unique identifier of the content
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the content. Note that this method should
     * never be called by developers as it will be automatically set by the
     * underlying persistence provider.
     *
     * @param id the unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The unique identifier for this content.
     *
     * @return the content's unique identifier
     */
    public String getUid() {
        return uid;
    }

    /**
     * A unique identifier that can optionally be set by an application. In
     * contrast to the {@link #setId(java.lang.Long) id}, this value can be
     * custom provided by an application. The only requirement is that the value
     * needs to be unique over all contents. When the <code>uid</code> is not
     * provided when persisting a content, then it will be set automatically to
     * a {@link java.util.UUID#randomUUID() UUID}.
     *
     * @param uid the unique String identifier of the content
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * The author of the content.
     *
     * @return the content's author
     */
    public PlatformUser getAuthor() {
        return author;
    }

    /**
     * Sets the author for this content.
     *
     * @param author the author of the content
     */
    public void setAuthor(PlatformUser author) {
        this.author = author;
    }

    /**
     * The id of the author of the content.
     *
     * @return the id of the content's author
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * Sets the id of the author of the content. This method should not be
     * called directly by developers as the field will be set automatically by
     * taking the id of the author field.
     *
     * @param authorId the id of the author of the content
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * The actual textual content of the content.
     *
     * @return the actual content of the content
     */
    public String getContent() {
        return content;
    }

    /**
     * Defines the actual textual content of the content. In contrast to the
     * summary field, this will most likely contain the complete content.
     *
     * @param content the actual content of the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * The time in milliseconds when the content was created.
     *
     * @return the time in milliseconds since midnight, January 1, 1970 UTC
     */
    public long getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the time when the content was created. Note that the creation date
     * will be automatically set if it was not provided when persisting the
     * content.
     *
     * @param creationDate the time in milliseconds when the content was created
     */
    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * The group of the content.
     *
     * @return the content's group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets the group for this content.
     *
     * @param group the group of the content
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * The id of the group of the content.
     *
     * @return the id of the content's group
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Sets the id of the group of the content. This method should not be called
     * directly by developers as the field will be set automatically by taking
     * the id of the group field.
     *
     * @param groupId the id of the group of the content
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    /**
     * The time in milliseconds when the content was last updated.
     *
     * @return the time in milliseconds since midnight, January 1, 1970 UTC
     */
    public long getModificationDate() {
        return modificationDate;
    }

    /**
     * Sets the time when the content was last updated. This method has no
     * meaning for developers as the modification date will be automatically set
     * by the persistence provider.
     *
     * @param modificationDate the time in milliseconds when the content was
     * last updated
     */
    public void setModificationDate(long modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * The parent of the content.
     *
     * @return the content's parent
     */
    public Content getParent() {
        return parent;
    }

    /**
     * Sets the parent for this content.
     *
     * @param parent the parent of the content
     */
    public void setParent(Content parent) {
        this.parent = parent;
    }

    /**
     * The id of the parent of the content.
     *
     * @return the id of the content's parent
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Sets the id of the parent of the content. This method should not be
     * called directly by developers as the field will be set automatically by
     * taking the id of the parent field.
     *
     * @param parentId the id of the parent of the content
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * The complete path of the content.
     *
     * @return the content's path
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the complete path of the content. A path consists of the path of the
     * parent content extended with a / character and the id of the parent. A
     * content without a parent will have an empty string as path. The path can
     * thus be used to reconstruct the entire parent structure of the content.
     * Please note that the path of a content and the entire subtree of that
     * content will be set automatically when creating, updating or removing the
     * content.
     *
     * @param path the path of the content
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * The depth of the content in relation to it's parent tree.
     *
     * @return the number of parents above the content
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Sets the depth of the content. The depth is the number of parents that
     * exist from the parent of this content till the root parent. As such, a
     * content without a parent will have a depth of 0. Please note that the
     * depth of a content and the entire subtree of that content will be set
     * automatically when creating, updating or removing the content.
     *
     * @param depth the depth of the content
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * The textual summary of the content.
     *
     * @return the summary of the content
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Defines the textual summary of the content. In contrast to the content
     * field, this will most likely contain a small portion of the actual
     * content.
     *
     * @param summary the actual content of the content
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * A list of tags on the content.
     *
     * @return a list of all tags of the content
     */
    public Set<String> getTags() {
        return tags;
    }

    /**
     * Defines the list of tags on the content. A tag is a string keyword that
     * further describes the content. The content can be searched by these tags.
     *
     * @param tags the list of tags that define this content
     */
    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    /**
     * Adds a single tag to the list of existing tags.
     *
     * @param tag the new tag keyword to be added
     */
    public void addTag(String tag) {
        if (tags == null) {
            tags = new HashSet();
        }
        tags.add(tag);
    }

    /**
     * Removes a single tag from the list of existing tags.
     *
     * @param tag the tag keyword to be removed
     */
    public void removeTag(String tag) {
        if (tags != null) {
            tags.remove(tag);
        }
    }

    /**
     * The title of the content.
     *
     * @return the content's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Defines the title for the content.
     *
     * @param title the title of the content
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The type of the content.
     *
     * @return the content's type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets the type of the content. A type can be used to categorize a list of
     * content that define the same logical entity.
     *
     * @param type the type of the content
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * The weight of the content.
     *
     * @return the content's weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the content. The weight can be used to specify the
     * importance of the content, for instance as the position field.
     *
     * @param weight the weight of the content
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * The application key that is associated with this content.
     *
     * @return the content's application key
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * Sets the application key associated with the content. The application key
     * defines the application scope of the content object. By setting an
     * application key, this content can only interact with other entities
     * within the same application scope.
     *
     * @param appKey the application key to set
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    /**
     * A list of content permissions that were set on this content.
     *
     * @return all content permissions set on this content
     */
    public List<ContentPermission> getContentPermissions() {
        return contentPermissions;
    }

    /**
     * Sets the list of content permissions of which this content is the target.
     *
     * @param contentPermissions a list of content permissions set on this
     * content
     */
    public void setContentPermissions(List<ContentPermission> contentPermissions) {
        this.contentPermissions = contentPermissions;
    }

    /**
     * A list of custom fields defined for this content.
     *
     * @return the custom fields of the content
     */
    public List<Field> getFields() {
        return fields;
    }

    /**
     * Sets the list of custom fields to be defined for this content.
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
            fields = new LinkedList();
        }
        fields.add(field);
    }

    /**
     * Convenience method for quickly updating existing fields. All fields that
     * have a key that matches the specified name, will be updated by storing
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
     * Convenience method for quickly removing a field. All fields that have a
     * key that matches the specified name, will be removed. When more advanced
     * management of fields is needed, one can always use
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
        final Content other = (Content) obj;
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
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.lodgon.dali.core.entity.Content[id=" + id + "]";
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
        if (group != null) {
            groupId = group.getId();
        } else {
            groupId = null;
        }
        if (parent != null) {
            parentId = parent.getId();
        } else {
            parentId = null;
        }
    }

    protected void copyFrom(Content src) {
        setAppKey(src.getAppKey());
        setAuthor(src.getAuthor());
        setAuthorId(src.getAuthorId());
        setContent(src.getContent());
        setContentPermissions(src.getContentPermissions());
        setCreationDate(src.getCreationDate());
        setFields(src.getFields());
        setGroup(src.getGroup());
        setGroupId(src.getGroupId());
        setId(src.getId());
        setModificationDate(src.getModificationDate());
        setParent(src.getParent());
        setParentId(src.getParentId());
        setSummary(src.getSummary());
        setTags(src.getTags());
        setTitle(src.getTitle());
        setType(src.getType());
        setUid(src.getUid());
    }
}
