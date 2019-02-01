package com.mailang.rest;

import com.mailang.bean.qmodel.AUModel;
import com.mailang.bean.qmodel.QModel;
import com.mailang.bean.qmodel.RetMessage;
import com.mailang.cons.ERRCode;
import com.mailang.jdbc.mybatis.AbstractDao;
import com.mailang.jdbc.persist.DBUtils;
import com.mailang.utils.SpringUtils;
import com.mailang.utils.Utils;
import com.mailang.xsexception.XSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "rest/crud")
public class CRUD
{
    private static Logger LOG = LoggerFactory.getLogger(CRUD.class);

    /**
     * @param type daoId
     * @param id   queryId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public RetMessage query(@RequestParam("type") String type, @RequestParam("id") String id)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
            LOG.debug("QyeryById:Request parameter, type: {}, id:{}.", type, id);
            AbstractDao aDao = (AbstractDao)SpringUtils.getBeanById(type);
            retMessage.setCode(ERRCode.SUCCESS);
            retMessage.setData(aDao.queryById(id));
            return retMessage;
        }
        catch (XSException e)
        {
            LOG.error("XSError. Msg: {}.", e.getMessage());
            retMessage.setCode(e.getErrCode());
            retMessage.setMsg(e.getMessage());
            return retMessage;
        }
        catch (Exception e1)
        {
            LOG.error("Error. Msg: {}.", Utils.getStackTrace(e1));
            retMessage.setCode(ERRCode.UNKNOW_EXCEPTION);
            retMessage.setMsg(Utils.getStackTrace(e1));
            return retMessage;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public RetMessage query(@RequestBody QModel qModel)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
            LOG.debug("Query:Request parameter: {}.", qModel.toString());
            AbstractDao aDao = (AbstractDao)SpringUtils.getBeanById(qModel.getType());
            List<Map<String, Object>> retData = aDao.query(qModel.getConList());
            if (!qModel.getIsDBColumn())
            {
                retData = DBUtils.dbDataAdapter(retData, false);
            }
            retMessage.setCode(ERRCode.SUCCESS);
            retMessage.setData(retData);
            return retMessage;
        }
        catch (XSException e)
        {
            LOG.error("XSError. Msg: {}.", e.getMessage());
            retMessage.setCode(e.getErrCode());
            retMessage.setMsg(e.getMessage());
            return retMessage;
        }
        catch (Exception e1)
        {
            LOG.error("Error. Msg: {}.", Utils.getStackTrace(e1));
            retMessage.setCode(ERRCode.UNKNOW_EXCEPTION);
            retMessage.setMsg(Utils.getStackTrace(e1));
            return retMessage;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public RetMessage addOrUpate(@RequestBody AUModel auModel)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
            AbstractDao aDao = (AbstractDao)SpringUtils.getBeanById(auModel.getType());
            Map<String, Object> dataMap = auModel.getData();
            if (!auModel.isDBColumn())
            {
                dataMap = DBUtils.dbDataAdapter(dataMap, false);
            }
            aDao.save(dataMap);
            retMessage.setCode(ERRCode.SUCCESS);
            return retMessage;
        }
        catch (XSException e)
        {
            LOG.error("XSError. Msg: {}.", e.getMessage());
            retMessage.setCode(e.getErrCode());
            retMessage.setMsg(e.getMessage());
            return retMessage;
        }
        catch (Exception e1)
        {
            LOG.error("Error. Msg: {}.", Utils.getStackTrace(e1));
            retMessage.setCode(ERRCode.UNKNOW_EXCEPTION);
            retMessage.setMsg(Utils.getStackTrace(e1));
            return retMessage;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/html;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public String queryById(@RequestBody AUModel auModel)
    {
        return "update";
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public String delete(@RequestBody QModel qModel)
    {
        return "update";
    }

    @ResponseBody
    @RequestMapping(value = "/aubatch", method = RequestMethod.POST, produces = "text/html;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public String auBatch(@RequestBody List<AUModel> auModelList)
    {
        return "update";
    }
}
