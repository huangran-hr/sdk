package com.sjlh.kunlun.membership.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: HR
 * @Date 2022/3/16 14:42
 * @Description:
 */
@Getter
@Setter
public class AuthConfiguration {

    private String url; //认证接口地址

    private String client_id;

    private String client_secret;

    private String grant_type; //授权方式，填写固定值 client_credentials

    private String scope; //您要访问的 API 对应的 Scope 名称
}
