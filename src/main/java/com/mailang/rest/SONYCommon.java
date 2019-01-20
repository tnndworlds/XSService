package com.mailang.rest;

import ch.qos.logback.classic.Logger;
import com.mailang.bean.qmodel.RetMessage;
import com.mailang.cons.ERRCode;
import com.mailang.log.XSLogger;
import com.mailang.log.XSLoggerFactory;
import com.mailang.log.XSRecord;
import com.mailang.south.data.RestTemplateCFG;
import com.mailang.utils.Utils;
import com.mailang.xsexception.XSException;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SONYCommon
{
    private static XSLogger LOG = XSLoggerFactory.getLogger(SONYCommon.class);
    /**
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "rest/sony", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public RetMessage getByGet(@RequestParam("param") String param)
    {

    	JSONObject retObj = new JSONObject();
    	retObj.put("name", "chengsongsongaaaaa");
    	retObj.put("sex", "male");
        RetMessage retMessage = new RetMessage();
        try
        {
            retMessage.setCode(ERRCode.SUCCESS);
            retMessage.setData(retObj);
            //RestTemplateCFG.getTemplateRest("testData");
            List<String> testList = new ArrayList<>();
            testList.add("1");
            testList.add("2");
            testList.add("3");
            LOG.debug("Hello {}.", "chengsongsong");
            LOG.info("ScheduleTask", testList);
            return retMessage;
        }
        catch (XSException e)
        {
            retMessage.setCode(e.getErrCode());
            retMessage.setMsg(e.getMessage());
            return retMessage;
        }
        catch (Exception e1)
        {
            retMessage.setCode(ERRCode.UNKNOW_EXCEPTION);
            retMessage.setMsg(Utils.getStackTrace(e1));
            return retMessage;
        }
    }
}
