package com.mailang.south.data.dataadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR c00241496
 * @create 2017-11-28
 **/
public class AdapterBean
{
    private boolean pageEnd = false;
    private List<Map<String, Object>> pageData = new ArrayList<>();
    private List<Map<String, Object>> allData = new ArrayList<>();

    public boolean isPageEnd()
    {
        return pageEnd;
    }

    public void setPageEnd(boolean pageEnd)
    {
        this.pageEnd = pageEnd;
    }

    public List<Map<String, Object>> getPageData()
    {
        return pageData;
    }

    public void setPageData(List<Map<String, Object>> pageData)
    {
        this.pageData = pageData;
    }

    public List<Map<String, Object>> getAllData()
    {
        return allData;
    }

    public void setAllData(List<Map<String, Object>> allData)
    {
        this.allData = allData;
    }
}
