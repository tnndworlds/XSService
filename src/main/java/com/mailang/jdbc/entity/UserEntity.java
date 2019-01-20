package com.mailang.jdbc.entity;


import com.mailang.jdbc.persist.annotation.Column;
import com.mailang.jdbc.persist.annotation.Id;
import com.mailang.jdbc.persist.annotation.Table;

@Table(name="tbl_xs_users", dataSource = "xsbook")
public class UserEntity
{
	@Id
	@Column(colName="ID")
	private String id;
	
	@Column(colName="NAME")
	private String name;

    @Column(colName="PASSWORD")
    private String password;
	
	@Column(colName="SEX")
	private String sex;

	@Column(colName="PHOTO")
	private String photo;
	
	@Column(colName="PERSONAL_SIGN")
	private String personalSign;

    @Column(colName="COLLEGE")
    private String college;

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

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getPhoto()
    {
        return photo;
    }

    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public String getPersonalSign()
    {
        return personalSign;
    }

    public void setPersonalSign(String personalSign)
    {
        this.personalSign = personalSign;
    }

    public String getCollege()
    {
        return college;
    }

    public void setCollege(String college)
    {
        this.college = college;
    }
}
