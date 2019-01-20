package com.mailang.jdbc.entity;

import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.SequenceGenerator;
import com.mailang.jdbc.persist.annotation.Table;

@Table(name="TBL_XS_BOOK_TYPE")
public class BookTypeEntity
{
    @Id
    @SequenceGenerator
    @Column(colName="ID")
    private String id;

    @Column(colName="NAME")
    private String name;

    @Column(colName="LANGUAGE")
    private String language;

    @Column(colName="DESCRIPTION")
    private String description;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "BookTypeEntity{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", language='" + language + '\'' + ", description='" + description + '\'' + '}';
    }
}
