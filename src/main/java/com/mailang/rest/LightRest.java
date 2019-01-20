package com.mailang.rest;

import com.mailang.bean.qmodel.RTemplateModel;
import com.mailang.bean.qmodel.RetMessage;
import com.mailang.cons.ERRCode;
import com.mailang.cons.XSCons;
import com.mailang.jdbc.mybatis.SQLDao;
import com.mailang.log.XSLogger;
import com.mailang.log.XSLoggerFactory;
import com.mailang.tquery.TemplateDataProvider;
import com.mailang.utils.JSONUtils;
import com.mailang.utils.SpringUtils;
import com.mailang.utils.Utils;
import com.mailang.xsexception.XSException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class LightRest
{
    @Autowired
    private static SQLDao sqlDao;

    @ResponseBody
    @RequestMapping(value = "rest/light/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public RetMessage saveData(@RequestBody String lightData)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
            JSONObject reqJson = JSONObject.fromObject(lightData);
            JSONObject basicInfo = JSONUtils.getJSONByKeyPath(reqJson, "basicInfo");
            JSONArray specifications = JSONUtils.getArrayByKeyPath(reqJson, "specifications");
            JSONArray venders = JSONUtils.getArrayByKeyPath(reqJson, "venders");
            JSONArray vendersSpec = JSONUtils.getArrayByKeyPath(reqJson, "vendersSpec");
            //save basicInfo


            retMessage.setCode(ERRCode.SUCCESS);
            retMessage.setMsg("");
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
