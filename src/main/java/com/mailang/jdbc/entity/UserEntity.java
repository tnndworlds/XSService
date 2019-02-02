package com.mailang.jdbc.entity;


import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.Table;

@Table(name="xs_dd_user", dataSource = "xsservice")
public class UserEntity
{
	@Id
	@Column(colName="ID")
	private String id;
	
	@Column(colName="NAME")
	private String name;

    @Column(colName="PASSWORD")
    private String password;

    @Column(colName="EMAIL")
    private String email;

    @Column(colName="TEL_NO")
    private Integer telNo;

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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getTelNo()
    {
        return telNo;
    }

    public void setTelNo(Integer telNo)
    {
        this.telNo = telNo;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
