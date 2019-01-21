package com.mailang.jdbc.mybatis.sqlprovider;

import com.mailang.jdbc.mybatis.bean.DBean;
import com.mailang.jdbc.mybatis.bean.QCondition;
import com.mailang.jdbc.persist.AutoIncreaseSequenceGenerator;
import com.mailang.jdbc.persist.DBUtils;
import com.mailang.jdbc.persist.SQLUtils;
import com.mailang.jdbc.persist.TableFactory;
import com.mailang.jdbc.persist.meta.ColumnMeta;
import com.mailang.jdbc.persist.meta.TableMeta;
import com.mailang.log.XSLogger;
import com.mailang.log.XSLoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 注解SQL提供类
 * 【作者】： 成松松
 * 【版本】：[V01]
 * 【日期】：2017年6月13日
 */
public class SQLProvider
{
    private static XSLogger LOG = XSLoggerFactory.getLogger(SQLProvider.class);
    /**
     * 原生SQL执行提供方法，作SQL透传
     *
     * @param reqBean
     * @return
     */
    public String getSql(DBean reqBean)
    {
        return reqBean.getSql();
    }

    public String save(final DBean reqBean)
    {
        String className = reqBean.getClazz().getName();
        final String tblName = TableFactory.getInstance().getTableNameByClassName(className);

        final TableMeta tableMeta = TableFactory.getInstance().getMetaByClazz(className);

        final Map<String, Object> dataMap = reqBean.getDataMap();
        String sql = new SQL()
        {{
            INSERT_INTO(tblName);
            Set<Entry<String, Object>> set = dataMap.entrySet();

            for (ColumnMeta columnMeta : tableMeta.getColumns())
            {
                Object colValueObj = dataMap.get(columnMeta.getColumnName());
                if (null == colValueObj && !columnMeta.isAutoIncrease())
                {
                    continue;
                }
                //Set value to Auto increase attribute
                String colValue = String.valueOf(colValueObj);
                if (columnMeta.isAutoIncrease())
                {
                    colValue = AutoIncreaseSequenceGenerator.getSerialNum();
                }

                if (tableMeta.getPrimaryKeys().contains(columnMeta))
                {
                    reqBean.setPrimaryValue(colValue);
                }

                VALUES(columnMeta.getColumnName(), "'" + colValue + "'");
            }

        }}.toString();
        LOG.debug("Save Sql: {}.", sql.toString());
        return sql;
    }

    public String deleteById(DBean reqBean)
    {
        String className = reqBean.getClazz().getName();
        StringBuilder tName = new StringBuilder();
        tName.append(TableFactory.getInstance().getTableNameByClassName(className));
        final String tblName = tName.toString();
        String id = String.valueOf(reqBean.getId());
        String sql = "DELETE FROM " + tblName + " WHERE ID = '" + id + "'";
        LOG.debug("queryById Sql: {}.", sql);
        return sql;
    }

    public String queryById(DBean reqBean)
    {
        String className = reqBean.getClazz().getName();
        final String tblName = TableFactory.getInstance().getTableNameByClassName(className);
        String id = String.valueOf(reqBean.getId());
        String sql = "SELECT * FROM " + tblName + " WHERE ID = '" + id + "'";
        LOG.debug("queryById Sql: {}.", sql);
        return sql;
    }

    public String queryByConList(DBean reqBean)
    {
        String className = reqBean.getClazz().getName();
        final String tblName =  TableFactory.getInstance().getTableNameByClassName(className);
        final String subSqlClause = SQLUtils.getWhereSql(reqBean.getConList());

        String sql = new SQL(){{
            SELECT("*");
            FROM(tblName);
            if (null != subSqlClause && !subSqlClause.trim().isEmpty())
            {
               WHERE(subSqlClause);
            }
        }}.toString();
        LOG.debug("QueryByConList Sql: {}.", sql);
        return sql;
    }
    public String update(DBean reqBean)
    {
        String className = reqBean.getClazz().getName();
        final String tblName = TableFactory.getInstance().getTableNameByClassName(className);

        final Map<String, Object> dataMap = reqBean.getDataMap();
        final Map<String, Object> primaryMap = new HashMap<String, Object>();
        TableMeta metaTable = TableFactory.getInstance().getMetaByClazz(className);
        List<ColumnMeta> primaryColList = metaTable.getPrimaryKeys();
        for (ColumnMeta col : primaryColList)
        {
            Object primaryObj = dataMap.get(col.getColumnName());
            primaryMap.put(col.getColumnName(), primaryObj);
            if (null == primaryObj)
            {
                return "";
            }
            dataMap.remove(col.getColumnName());
        }

        List<QCondition> idConList = DBUtils.getQListFromMap(primaryMap);
        final String sqlSub = SQLUtils.getWhereSql(idConList);
        String sql = updateBySql(tblName, sqlSub, dataMap);
        return sql;
    }

    public String updateBySql(final String tblName,final String sqlSub,final Map<String, Object> dataMap)
    {
        String sql = new SQL(){{
            UPDATE(tblName);
            Set<Entry<String, Object>> set = dataMap.entrySet();
            for (Entry<String, Object> entry : set)
            {
                if (null == entry.getValue())
                {
                    continue;
                }
                SET(entry.getKey() + "='" + String.valueOf(entry.getValue()) + "'");
            }

            WHERE(sqlSub);
        }}.toString();
        return sql;
    }

}
