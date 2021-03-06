package com.std.forum.api.impl;

import com.std.forum.ao.ISubsystemAO;
import com.std.forum.api.AProcessor;
import com.std.forum.common.JsonUtil;
import com.std.forum.core.StringValidater;
import com.std.forum.dto.req.XN610091Req;
import com.std.forum.dto.res.BooleanRes;
import com.std.forum.exception.BizException;
import com.std.forum.exception.ParaException;
import com.std.forum.spring.SpringContextHolder;

/**
 * 地方修改子系统配置
 * @author: asus 
 * @since: 2017年3月21日 下午5:56:01 
 * @history:
 */
public class XN610091 extends AProcessor {
    private ISubsystemAO subsystemAO = SpringContextHolder
        .getBean(ISubsystemAO.class);

    private XN610091Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        subsystemAO.editSubsystemByLocal(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN610091Req.class);
        StringValidater.validateBlank(req.getCode(), req.getName(),
            req.getPic(), req.getLocation(), req.getOrderNo(), req.getBelong());
    }
}
