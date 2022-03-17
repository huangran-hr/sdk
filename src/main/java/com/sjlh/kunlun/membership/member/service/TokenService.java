package com.sjlh.kunlun.membership.member.service;

import com.sjlh.hotel.common.net.Invoker;
import com.sjlh.hotel.common.net.InvokerFactory;
import com.sjlh.hotel.common.net.InvokerParam;
import com.sjlh.hotel.common.net.InvokerParamBuilder;
import com.sjlh.kunlun.membership.config.MemberConfiguration;
import com.sjlh.kunlun.membership.core.AuthConfiguration;
import com.sjlh.kunlun.membership.dto.KunlunTokenDto;
import com.sjlh.kunlun.membership.dto.KunlunTokenResponseDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: HR
 * @Date 2022/3/16 14:45
 * @Description:
 */
public class TokenService {

    @Resource(name = "tokenRedisTemplate")
    private RedisTemplate<String, String> redis;
    @Resource
    private MemberConfiguration config;
    @Resource
    private InvokerParamBuilder paramBuilder;
    @Resource
    private InvokerFactory invokerFactory;

    public String getToken() {
        ValueOperations<String, String> option = redis.opsForValue();
        String token = option.get("kunlun_token");
        if(token!=null)return token;
        AuthConfiguration
                authConfiguration = config.getAuth();
        KunlunTokenDto
                request = new KunlunTokenDto();
        request.setClient_secret(authConfiguration.getClient_secret());
        request.setClient_id(authConfiguration.getClient_id());
        request.setScope(authConfiguration.getScope());
        request.setGrant_type(authConfiguration.getGrant_type());
        KunlunTokenResponseDto response = invoke(request, KunlunTokenResponseDto.class);
        if(response.getAccess_token() != null){
            save(response);
            token = response.getAccess_token();
        }
        return token;
    }

    public void save(KunlunTokenResponseDto response) {
        ValueOperations<String, String> option = redis.opsForValue();
        option.set("kunlun_token", response.getAccess_token(),1, TimeUnit.HOURS);
    }

    private <P, R> R invoke(P param, Class<R> clazz) {
        InvokerParam<R>
                invokerParam = paramBuilder.buildPost(clazz);
        invokerParam.setParam(param);
        invokerParam.setTarget(config.getAuth().getUrl());
        Invoker
                invoker = invokerFactory.getInvoker("kunlun.auth");
        R r = (R)invoker.invokeForm(invokerParam);
        return r;
    }


}
