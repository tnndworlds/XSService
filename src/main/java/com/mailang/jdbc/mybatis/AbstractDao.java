package com.mailang.jdbc.mybatis;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mailang.cons.ERRCode;
import com.mailang.jdbc.mybatis.bean.DBean;
import com.mailang.jdbc.mybatis.bean.QCondition;
import com.mailang.jdbc.mybatis.mapper.AbstractDaoMapper;
import com.mailang.jdbc.persist.DBUtils;
import com.mailang.log.XSLogger;
import com.mailang.log.XSLoggerFactory;
import com.mailang.xsexception.XSException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 实体类数据库访问公共抽象方法
 * 【作者】： 成松松
 * 【版本】：[V01]
 * 【日期】：2017年6月13日
 */
public abstract class AbstractDao<T>
{
	private static XSLogger LOG = XSLoggerFactory.getLogger(AbstractDao.class);
	
	@Autowired
	protected AbstractDaoMapper abstractDaoMapper;

	protected DBean dBean = null;

	private Class<T> entityClass;

	public AbstractDao()
	{
		this.entityClass = null;
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType)
		{
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
		dBean = new DBean();
		dBean.setClazz(this.entityClass);
	}

	public AbstractDaoMapper getAbstractDaoMapper()
	{
		return abstractDaoMapper;
	}

	public void setAbstractDaoMapper(AbstractDaoMapper abstractDaoMapper)
	{
		this.abstractDaoMapper = abstractDaoMapper;
	}
	
	public Map<String, Object> queryById(String id)
	{
		dBean.setId(id);
		try
		{
			return this.abstractDaoMapper.queryById(dBean);
		}
		catch (Exception e)
		{
			throw new XSException(ERRCode.DB_QUERY_ERROR, this.entityClass.getName(), id);
		}
	}

	public List<Map<String, Object>> query(List<QCondition> qConditionsList)
	{
		dBean.setConList(qConditionsList);
		try
		{
			return this.abstractDaoMapper.queryByConList(dBean);
		}
		catch (Exception e)
		{
			throw new XSException(ERRCode.DB_QUERY_ERROR, this.entityClass.getName(), qConditionsList.toString());
		}
	}

	/**
	 * Save Data Map
	 * @param dataMap data
	 * @return 0:保存成功， -1：保存失败
	 */
	public int save(Map<String, Object> dataMap)
	{
		DBean dbean = new DBean();
		dbean.setClazz(entityClass);
		dbean.setDataMap(dataMap);
		try
		{
			this.abstractDaoMapper.save(dbean);
			return 0;
		}
		catch (Exception e)
		{
			throw new XSException(ERRCode.DB_SAVE_ERROR, this.entityClass.getName(), dataMap.toString());
		}
	}

	public int update(Map<String, Object> dataMap)
    {
        DBean dbean = new DBean();
        dbean.setClazz(entityClass);
        dbean.setDataMap(dataMap);
        try
        {
            this.abstractDaoMapper.update(dbean);
            return 0;
        }
        catch (Exception e)
        {
            throw new XSException(ERRCode.DB_UPDATE_ERROR, this.entityClass.getName(), dataMap.toString());
        }
    }

	public boolean saveOrUpdate(Map<String, Object> dataMap, String[]primaryKey)
    {
        Map<String, Object> conMap = new HashMap<String, Object>();
        for (String key : primaryKey)
        {
            if (null == dataMap.get(key))
            {
                return false;
            }
            conMap.put(key, dataMap.get(key));
        }

        List<Map<String, Object>> retList = this.query(DBUtils.getQListFromMap(conMap));
        if (null == retList || retList.isEmpty())
        {
            this.save(dataMap);
        }
        else
        {
            this.update(dataMap);
        }

        return true;
    }
}
