package cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.controller;

import java.security.Principal;

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
import cn.fintecher.supply.finance.loan.manager.common.pledge.request.PledgeStockTrialFrom;
import cn.fintecher.supply.finance.loan.manager.common.pledge.response.PledgeSumbitTrialRequest;
import cn.fintecher.supply.finance.loan.manager.pm.bff.pledge.service.PledgeSecondTrialService;

/**
 * 仓单质押复审审批相关接口
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-08-24 11:12:09
 */
@Api(value = "仓单质押复审审批相关接口", tags = "仓单质押复审审批相关接口")
@RestController
@RequestMapping("/pledge/secondTrial")
public class PledgeSecondTrialController {

    @Autowired
    private PledgeSecondTrialService pledgeSecondTrialService;
    

	@ApiOperation(value="领取任务", notes="领取任务")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/receiveTask", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public synchronized  Message receiveTask(Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
            Message message =pledgeSecondTrialService.receiveTask(principa.getName());
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="审批列表", notes="审批列表")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchPledgeList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchPledgeList(@RequestBody PledgeStockTrialFrom pledgeStockTrialFrom,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        pledgeStockTrialFrom.setUserId(principa.getName());
        try {
            Message message =pledgeSecondTrialService.searchPledgeList(pledgeStockTrialFrom);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="处理详情", notes="处理详情")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/searchDetail", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message searchDetail(@RequestParam("id") Long id,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
            Message message =pledgeSecondTrialService.searchDetail(id,principa.getName());
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    

	@ApiOperation(value="提交审批", notes="提交审批")
	@ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/submitContnet", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public synchronized  Message submitContnet(@RequestBody PledgeSumbitTrialRequest pledgeApplyInfoResponse,Principal principa){
        Message msg = new Message(MessageType.MSG_SUCCESS,"pledge",null);
        try {
        	pledgeApplyInfoResponse.setUserName(principa.getName());
            Message message =pledgeSecondTrialService.submitContnet(pledgeApplyInfoResponse);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }
    
   


   

}
