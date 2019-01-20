package com.mailang.jdbc.entity;

import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.SequenceGenerator;
import com.mailang.jdbc.persist.annotation.Table;

@Table(name="TBL_XS_BOOKS")
public class BookEntity
{
    @Id
    @SequenceGenerator
    @Column(colName="ID")
    private String id;

    @Column(colName="NAME")
    private String name;

    @Column(colName="AUTHOR")
    private String author;

    @Column(colName="PUBLISHING")
    private String publishing;

    @Column(colName="OWNER")
    private String owner;

    @Column(colName="TAGS")
    private String tags;

    @Column(colName="PRICE")
    private String price;

    @Column(colName="DISCOUNT_PRICE")
    private String discountPrice;

    @Column(colName="IMG")
    private String img;

    @Column(colName="NEW_DEGREE")
    private String newDegree;

    @Column(colName="ISBN")
    private String isbn;

    @Column(colName="PUBLISH_TIME")
    private String publishTime;

    @Column(colName="PERMISSION")
    private String PERMISSION;

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

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getPublishing()
    {
        return publishing;
    }

    public void setPublishing(String publishing)
    {
        this.publishing = publishing;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getTags()
    {
        return tags;
    }

    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getDiscountPrice()
    {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice)
    {
        this.discountPrice = discountPrice;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public String getNewDegree()
    {
        return newDegree;
    }

    public void setNewDegree(String newDegree)
    {
        this.newDegree = newDegree;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getPublishTime()
    {
        return publishTime;
    }

    public void setPublishTime(String publishTime)
    {
        this.publishTime = publishTime;
    }

    public String getPERMISSION()
    {
        return PERMISSION;
    }

    public void setPERMISSION(String PERMISSION)
    {
        this.PERMISSION = PERMISSION;
    }
}
