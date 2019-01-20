package com.mailang.bean.xml;

import com.mailang.south.data.dataadapter.SouthDataAdapterInterface;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR c00241496
 * @create 2017-11-23
 **/
public class TemplateRest
{
    private String name;
    private String method;
    private String address;
    private String header;
    private String paramPlugin;
    private String startPageNum;
    private String pageSize;
    private String pageTotalKeyPath;
    private String dateFormat;
    private String dataPath;
    private String dataAdapter;
    private SouthDataAdapterInterface southDataAdapterInterface;
    private String primaryKey;
    private String maxStorage;
    private String daoId;
    private String preSql;
    private String postSql;
    private String dataFilter;
    private String requestParam;
    private String response;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMethod()
    {
        return method;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getHeader()
    {
        return header;
    }

    public void setHeader(String header)
    {
        this.header = header;
    }

    public String getParamPlugin()
    {
        return paramPlugin;
    }

    public void setParamPlugin(String paramPlugin)
    {
        this.paramPlugin = paramPlugin;
    }

    public String getDateFormat()
    {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat)
    {
        this.dateFormat = dateFormat;
    }

    public String getDataPath()
    {
        return dataPath;
    }

    public void setDataPath(String dataPath)
    {
        this.dataPath = dataPath;
    }

    //多个请求共用该Adapter，需要考虑并发
    public SouthDataAdapterInterface getDataAdapter()
    {
        if (null == southDataAdapterInterface)
        {
            try
            {
                southDataAdapterInterface = (SouthDataAdapterInterface)Class.forName(this.dataAdapter).newInstance();
            }
            catch (Exception e)
            {
                return null;
            }
        }
        return southDataAdapterInterface;
    }

    public void setDataAdapter(String dataAdapter)
    {
        this.dataAdapter = dataAdapter;
    }

    public String getPrimaryKey()
    {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey)
    {
        this.primaryKey = primaryKey;
    }

    public Boolean getMaxStorage()
    {
        return Boolean.parseBoolean(maxStorage);
    }

    public void setMaxStorage(String maxStorage)
    {
        this.maxStorage = maxStorage;
    }

    public String getDaoId()
    {
        return daoId;
    }

    public void setDaoId(String daoId)
    {
        this.daoId = daoId;
    }

    public String getPreSql()
    {
        return preSql;
    }

    public void setPreSql(String preSql)
    {
        this.preSql = preSql;
    }

    public String getPostSql()
    {
        return postSql;
    }

    public void setPostSql(String postSql)
    {
        this.postSql = postSql;
    }

    public String getDataFilter()
    {
        return dataFilter;
    }

    public void setDataFilter(String dataFilter)
    {
        this.dataFilter = dataFilter;
    }

    public String getRequestParam()
    {
        return requestParam;
    }

    public void setRequestParam(String requestParam)
    {
        this.requestParam = requestParam;
    }

    public String getResponse()
    {
        return response;
    }

    public void setResponse(String response)
    {
        this.response = response;
    }

    public Integer getStartPageNum()
    {
        return Integer.parseInt(startPageNum);
    }

    public void setStartPageNum(String startPageNum)
    {
        this.startPageNum = startPageNum;
    }

    public String getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(String pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getPageTotalKeyPath()
    {
        return pageTotalKeyPath;
    }

    public void setPageTotalKeyPath(String pageTotalKeyPath)
    {
        this.pageTotalKeyPath = pageTotalKeyPath;
    }
}
