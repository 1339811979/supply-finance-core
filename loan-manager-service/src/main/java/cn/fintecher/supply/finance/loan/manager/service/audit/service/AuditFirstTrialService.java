package cn.fintecher.supply.finance.loan.manager.service.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditOrderInfoFrom;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSumbitContentRequest;
import cn.fintecher.supply.finance.loan.manager.common.sys.SysUserAdminEntity;

/**
 * 
 * @author whojinbao
 * @email jinbao.hu@fintecher.cn
 * @date 2018-07-18 11:47:56
 */
public interface AuditFirstTrialService {    
	
    public Message receiveTask(SysUserAdminEntity user);
	
    public Message searchOrderList(AuditOrderInfoFrom auditOrderInfoFrom);
	
    public Message searchDetail(Long pid);
	
    public Message submitContnet(AuditSumbitContentRequest auditSumbitContentRequest, SysUserAdminEntity user);
	
    public Message searchDetailResult(Long pid);
   

}
