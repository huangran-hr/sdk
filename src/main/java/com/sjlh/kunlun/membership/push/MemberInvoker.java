package com.sjlh.kunlun.membership.push;

import com.sjlh.hotel.common.annotation.InvokerMeta;
import com.sjlh.hotel.common.net.InvokerParam;
import com.sjlh.hotel.common.net.SimpleInvoker;
import com.sjlh.kunlun.membership.config.MemberConfiguration;
import com.sjlh.kunlun.membership.member.service.TokenService;
import com.sjlh.kunlun.membership.utils.BuildQueryUtil;
import javax.annotation.Resource;
import javax.ws.rs.HttpMethod;
import java.util.*;

/**
 * @author Administrator
 *
 */
@InvokerMeta(id = "kunlun.member")
public class MemberInvoker extends SimpleInvoker {
	@Resource
	private MemberConfiguration config;

	@Resource
	private TokenService tokenService;

	@Override
	protected <T> void initTarget(InvokerParam<T> param) {
		String target = param.getTarget();
		if(target!=null && !target.startsWith("https")) {
			if(!target.startsWith("/"))target = "/" + target;
			param.setTarget(config.getHost() + target);
		}

		if(HttpMethod.GET.equals(param.getMethod()) && param.getParam() != null){
			param.setTarget(BuildQueryUtil.buildQuery(param.getTarget(),param.getParam()));
		}
	}

	@Override
	public <T> T invoke(InvokerParam<T> param) {
		Map<String, String> header =  param.getHeader();
		if(header == null) header= new HashMap<>();
		if(header.get("Authorization") == null){
			String token = tokenService.getToken();
			header.put("Authorization","Bearer "+token);
			param.setHeader(header);
		}
		return super.invoke(param);
	}


}
