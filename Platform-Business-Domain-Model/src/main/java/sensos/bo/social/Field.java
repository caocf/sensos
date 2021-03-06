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

import sensos.bo.social.Content;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sensos.bo.user.PlatformUser;

/**
 * Fields can be used in association with a Content, Group or PlatformUser to
 * extend their properties with custom defined properties. This is a convenient
 * way for developers to extends the DaliCore entities with fields that are not
 * available by default. A field consists of a string name and a string value.
 * Conversion from ie an integer field should therefor be done within the
 * developer's application.
 *
 * @author joeri
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Field.getByContent",
            query = "SELECT f FROM Field f WHERE f.content = :content ORDER BY f.name, f.id"
    ),
    @NamedQuery(
            name = "Field.getByContentAndName",
            query = "SELECT f FROM Field f WHERE f.content = :content AND f.name = :name ORDER BY f.id"
    ),
    @NamedQuery(
            name = "Field.getByGroup",
            query = "SELECT f FROM Field f WHERE f.group = :group ORDER BY f.name, f.id"
    ),
    @NamedQuery(
            name = "Field.getByGroupAndName",
            query = "SELECT f FROM Field f WHERE f.group = :group AND f.name = :name ORDER BY f.id"
    ),
    @NamedQuery(
            name = "Field.getByUser",
            query = "SELECT f FROM Field f WHERE f.user = :user ORDER BY f.name, f.id"
    ),
    @NamedQuery(
            name = "Field.getByUserAndName",
            query = "SELECT f FROM Field f WHERE f.user = :user AND f.name = :name ORDER BY f.id"
    )
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long creationDate;

    private String name;
    @Lob
    @Column(name = "VAL")
    private String value;

    @ManyToOne
    private PlatformUser user;
    @ManyToOne
    private Group group;
    @ManyToOne
    private Content content;

    /**
     * Constructs a field with an empty name and value.
     */
    public Field() {
    }

    /**
     * Constructs a field with the specified name and value.
     *
     * @param name the name of the field
     * @param value the value of the field
     */
    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * The unique identifier automatically generated by the persistence
     * provider.
     *
     * @return unique identifier of the field
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the field. Note that this method should
     * never be called by developers as it will be automatically set by the
     * underlying persistence provider.
     *
     * @param id the unique identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * The time in milliseconds when the field was created.
     *
     * @return the time in milliseconds since midnight, January 1, 1970 UTC
     */
    public long getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the time when the field was created. Note that the creation date
     * will be automatically set if it was not provided when persisting the
     * field.
     *
     * @param creationDate the time in milliseconds when the field was created
     */
    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * The content that is associated with the field. When the field is not
     * associated with a content, then the content will be <code>null</code>.
     *
     * @return the content associated with the field
     */
    public Content getContent() {
        return content;
    }

    /**
     * Sets the content that will use this field. A field should only be used by
     * one DaliCore entity at a time (either a content, a group or a user).
     *
     * @param content the content to be associated with the field
     */
    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * The group that is associated with the field. When the field is not
     * associated with a group, then the group will be <code>null</code>.
     *
     * @return the group associated with the field
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Sets the group that will use this field. A field should only be used by
     * one DaliCore entity at a time (either a content, a group or a user).
     *
     * @param group the group to be associated with the field
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * The name of the field.
     *
     * @return the field's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name for this field.
     *
     * @param name the name of the field
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The user that is associated with the field. When the field is not
     * associated with a user, then the user will be <code>null</code>.
     *
     * @return the user associated with the field
     */
    public PlatformUser getUser() {
        return user;
    }

    /**
     * Sets the user that will use this field. A field should only be used by
     * one DaliCore entity at a time (either a content, a group or a user).
     *
     * @param user the user to be associated with the field
     */
    public void setUser(PlatformUser user) {
        this.user = user;
    }

    /**
     * The value of the field.
     *
     * @return the field's value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value for this field.
     *
     * @param value the value of the field
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Field other = (Field) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Field[id=" + id + " name=" + name + " value=" + value + "]";
    }

    @PrePersist
    public void prePersist() {
        if (creationDate == 0) {
            this.creationDate = System.currentTimeMillis();
        }
    }
}
