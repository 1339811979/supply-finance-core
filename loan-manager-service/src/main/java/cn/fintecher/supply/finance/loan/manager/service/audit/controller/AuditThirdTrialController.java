package cn.fintecher.supply.finance.loan.manager.service.audit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;
import cn.fintecher.supply.finance.loan.manager.service.audit.service.AuditThirdTrialService;
import cn.fintecher.supply.finance.loan.manager.service.common.controller.BaseSysUserController;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-19 13:35:36
 */
@Api(value = "", tags = "")
@RestController
@RequestMapping("/audit/thirdTrial")
public class AuditThirdTrialController  extends BaseSysUserController{

    @Autowired
    private AuditThirdTrialService auditThirdTrialService;
    

    @ApiOperation(value="领取", notes="领取任务")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/receiveTask", method = RequestMethod.GET)
    public Message receiveTask(@RequestParam("userName") String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
        	SysUserAdminEntity user = getSysUser(userName);
            Message message =auditThirdTrialService.receiveTask(user);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="查询列表")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchOrderList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchOrderList(@RequestBody AuditOrderInfoFrom auditOrderInfoFrom){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
        	SysUserAdminEntity user = getSysUser(auditOrderInfoFrom.getUserId());
    		auditOrderInfoFrom.setUserId(user.getId()+"");
            Message message =auditThirdTrialService.searchOrderList(auditOrderInfoFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="查看详情")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchDetail", method = RequestMethod.GET)
    public Message searchDetail(@RequestParam("pid")Long pid, @RequestParam("userName")String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditThirdTrialService.searchDetail(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="提交审批")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitContnet", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message submitContnet(@RequestBody AuditSumbitContentRequest auditSumbitContentRequest){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
        	SysUserAdminEntity user = getSysUser(auditSumbitContentRequest.getUserName());
            Message message =auditThirdTrialService.submitContnet(auditSumbitContentRequest,user);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="", notes="审核通过后查询详情")
	//@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchDetailResult", method = RequestMethod.GET)
    public Message searchDetailResult(@RequestParam("pid")Long pid, @RequestParam("userName")String userName){
        Message msg = new Message(MessageType.MSG_SUCCESS,"audit",null);
        try {
            Message message =auditThirdTrialService.searchDetailResult(pid);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }


   

}
