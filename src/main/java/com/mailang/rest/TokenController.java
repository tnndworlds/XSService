package com.mailang.rest;

import com.mailang.bean.qmodel.Authorization;
import com.mailang.bean.qmodel.RetMessage;
import com.mailang.bean.qmodel.TokenModel;
import com.mailang.cons.ERRCode;
import com.mailang.jdbc.entity.UserEntity;
import com.mailang.log.XSLogger;
import com.mailang.log.XSLoggerFactory;
import com.mailang.user.MapTokenManager;
import com.mailang.utils.AuthUtils;
import com.mailang.utils.Utils;
import com.mailang.xsexception.XSException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "rest/tokens")
public class TokenController
{
    private static XSLogger LOG = XSLoggerFactory.getLogger(TokenController.class);

    @ResponseBody
    @RequestMapping(method= RequestMethod.POST, produces="application/json;charset=UTF-8")
    public RetMessage login(@RequestParam("userName")String userName, @RequestParam("password")String password)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(password))
            {
                throw new XSException(ERRCode.USERNAME_PASSOWR_EMPTY);
            }

            //鉴权操作
            UserEntity userEntity = AuthUtils.validUser(userName, password);
            if (null == userEntity)
            {
                throw new XSException(ERRCode.USERNAME_PASSOWR_ERROR);
            }
            TokenModel tokenModel = MapTokenManager.getInstance().createToken(userName);
            tokenModel.setUserEntity(userEntity);
            retMessage.setCode(ERRCode.SUCCESS);
            retMessage.setData(tokenModel);
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
    @Authorization
    @RequestMapping(method= RequestMethod.DELETE, produces="application/json;charset=UTF-8")
    public RetMessage logout(@RequestParam("userName")String userName)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
            MapTokenManager.getInstance().deleteToken(userName);
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
}
