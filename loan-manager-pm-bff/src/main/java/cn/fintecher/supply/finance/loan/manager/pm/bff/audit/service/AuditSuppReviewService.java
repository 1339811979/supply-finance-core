package cn.fintecher.supply.finance.loan.manager.pm.bff.audit.service;

import cn.fintecher.common.utils.basecommon.message.Message;
import cn.fintecher.supply.finance.loan.manager.common.audit.request.AuditSuppReviewRequest;

/**
 * @author wuxiaoxing
 * @time 2018/7/24 14:32
 */
public interface AuditSuppReviewService {

    Message searchSuppReviewList(AuditSuppReviewRequest auditSuppReviewRequest);

    Message searchSuppReviewDetail(Long pid);
}
