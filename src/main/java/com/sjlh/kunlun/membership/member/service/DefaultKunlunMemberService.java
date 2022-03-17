package com.sjlh.kunlun.membership.member.service;

import com.sjlh.hotel.common.net.Invoker;
import com.sjlh.hotel.common.net.InvokerFactory;
import com.sjlh.hotel.common.net.InvokerParam;
import com.sjlh.hotel.common.net.InvokerParamBuilder;
import com.sjlh.kunlun.membership.dto.ProfileDto;
import javax.annotation.Resource;
import javax.ws.rs.core.GenericType;

/**
 * @Auther: HR
 * @Date 2022/3/16 11:39
 * @Description:
 */
public class DefaultKunlunMemberService implements KunlunMemberService {

    @Resource
    private InvokerFactory invokerFactory;

    private Invoker getInvoker(){
        return invokerFactory.getInvoker("kunlun.member");
    }


    /**
     * 通过档案通id获取档案信息
     * @param profileId
     * @return
     */
    @Override
    public ProfileDto getProfiles(String profileId) {
        GenericType<ProfileDto> type = new GenericType<>(){};
        InvokerParam<ProfileDto> param = InvokerParamBuilder.newInstance().buildFormGet(type);
        param.setTarget("/api/v1/profiles/" + profileId);
        return getInvoker().invoke(param);
    }
}
