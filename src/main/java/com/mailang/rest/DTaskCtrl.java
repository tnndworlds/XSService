package com.mailang.rest;
import com.mailang.bean.qmodel.DTaskModel;
import com.mailang.bean.qmodel.RetMessage;
import com.mailang.cons.ERRCode;
import com.mailang.ddtask.DTaskMgr;
import com.mailang.log.XSLogger;
import com.mailang.utils.Utils;
import com.mailang.xsexception.XSException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "rest/dtask")
public class DTaskCtrl
{
    private XSLogger LOG = XSLogger.getLogger(DTaskCtrl.class);

    private DTaskMgr dTaskMgr = new DTaskMgr();

    @ResponseBody
    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public RetMessage query(@RequestParam("userId") String userId)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
            retMessage.setCode(ERRCode.SUCCESS);
            retMessage.setData(dTaskMgr.getTaskList(userId));
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
    @RequestMapping(value = "/punch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public RetMessage punch(@RequestBody DTaskModel DTaskModel)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
            dTaskMgr.punch(DTaskModel);
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
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public RetMessage addTask(@RequestBody DTaskModel DTaskModel)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
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
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public RetMessage deleteTask(@RequestParam("userId") String userId,@RequestParam("type") String type, @RequestParam("id") String id)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
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
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json;charset=UTF-8")
    public RetMessage updateTask(@RequestBody DTaskModel DTaskModel)
    {
        RetMessage retMessage = new RetMessage();
        try
        {
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
