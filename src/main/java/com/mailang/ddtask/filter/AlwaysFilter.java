package com.mailang.ddtask.filter;

public class AlwaysFilter implements DataFilter
{
    @Override
    public boolean dataFilter()
    {
        return true;
    }
}
