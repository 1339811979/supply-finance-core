package cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.controller;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.common.utils.basecommon.message.MessageType;
import cn.fintecher.supply.finance.loan.manager.common.business.entity.BusinessFileEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.entity.ConfirmingStockInfoEntity;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoForm;
import cn.fintecher.supply.finance.loan.manager.common.confirming.request.ConfirmingStockInfoResquest;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoDetailResponse;
import cn.fintecher.supply.finance.loan.manager.common.confirming.response.ConfirmingStockInfoListResponse;
import cn.fintecher.supply.finance.loan.manager.common.util.PageResponse;
import cn.fintecher.supply.finance.loan.manager.pm.bff.business.service.BusinessFileService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockApplyService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.confirming.service.ConfirmingStockCoreService;
import cn.fintecher.supply.finance.loan.manager.pm.bff.util.DownLoadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;

/**
 * @author wuxiaoxing
 * @time 2018/9/5 17:24
 */
@RestController
@RequestMapping("/confirming/core")
@Api(tags = "保兑仓--核心企业确认")
public class ConfirmingStockCoreController {

    @Autowired
    private ConfirmingStockCoreService confirmingStockCoreService;

    @Autowired
    private ConfirmingStockApplyService confirmingStockApplyService;

    @Autowired
    private BusinessFileService businessFileService;

    @ApiOperation(value="融资拒绝", notes="融资拒绝")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/refuseFinancing", method = RequestMethod.GET)
    public Message refuseFinancing(@RequestParam("pid") Long pid){
        try {
            return  confirmingStockCoreService.refuseFinancing(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_bff",e.getMessage());
        }
    }

    @ApiOperation(value="融资确认 ", notes="融资确认")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/confirmFinancing", method = RequestMethod.GET)
    public Message confirmFinancing(@RequestParam("pid") Long pid){
        try {
            return  confirmingStockCoreService.confirmFinancing(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_bff",e.getMessage());
        }
    }


    @ApiOperation(value="预付类业务列表 ", notes="预付类业务列表")
    @ApiImplicitParam(name = "Authorization", required = true, dataType = "string", paramType = "header")
    @RequestMapping(value ="/confirmingStockPageList", method = RequestMethod.POST)
    public Message<PageResponse<List<ConfirmingStockInfoListResponse>>> confirmingStockPageList(@RequestBody ConfirmingStockInfoResquest confirmingStockInfoResquest, Principal principal){
        try {
            confirmingStockInfoResquest.setCompanyUserName(principal.getName());
            return  confirmingStockCoreService.confirmingStockPageList(confirmingStockInfoResquest);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_bff",e.getMessage());
        }
    }

    @ApiOperation(value="融资申办详情", notes="融资申办详情")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/getDetailById", method = RequestMethod.GET)
    public Message<ConfirmingStockInfoDetailResponse> getDetailById(@RequestParam(value = "pid") Long pid){
        try {
            return  confirmingStockApplyService.getDetailById(pid);
        }catch (Exception e){
            return new Message(MessageType.MSG_ERROR,"confirming_bff",e.getMessage());
        }
    }

    @ApiOperation(value="文件上传", notes="文件上传")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/upload", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message upload(@RequestPart("file") MultipartFile file,@RequestParam(value = "type",required = false) String type,@RequestParam(value = "code",required = false) String code){
        Message msg = new Message(MessageType.MSG_SUCCESS,"confirming_bff",null);
        if (code == null) {
            return new Message(MessageType.MSG_ERROR,"confirming_bff","拥有者不能为空");
        }
        try {
            Message message =confirmingStockApplyService.upload(file,type,code);
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }

    @ApiOperation(value="文件删除", notes="文件删除")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/delete", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Message delete(@RequestParam(value = "pid",required=true) Integer pid, Principal principal){
        Message msg = new Message(MessageType.MSG_SUCCESS,"business",null);
        try {
            Message message =businessFileService.delete(pid,principal.getName());
            msg.setCode(message.getCode());
            msg.setMessage(message.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            msg.setCode(MessageType.MSG_ERROR);
        }
        return msg;

    }


    @ApiOperation(value="文件下载查看", notes="文件下载查看")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/search", method = RequestMethod.GET)
    public ResponseEntity<byte[]> search(@RequestParam(value = "pid",required=true) Integer pid){
        return businessFileService.search(pid);

    }

    @ApiOperation(value="文件下载", notes="文件下载")
    @ApiImplicitParam(name = "Authorization", required = false, dataType = "string", paramType = "header")
    @RequestMapping(value ="/download", method = RequestMethod.GET)
    public void download(@RequestParam(value = "pid",required=true) Integer pid, Principal principa,
                         HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException {
        ResponseEntity<byte[]> resp = businessFileService.search(pid);
        InputStream inStream = new ByteArrayInputStream(resp.getBody());
        BusinessFileEntity file = businessFileService.queryFileByPid(pid);
        DownLoadUtil.downLoadByInputStream(response, request, file.getFileName(), inStream);

    }
}
