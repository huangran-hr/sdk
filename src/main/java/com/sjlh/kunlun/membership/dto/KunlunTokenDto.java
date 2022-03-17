package com.sjlh.kunlun.membership.dto;

import lombok.Data;

/**
 * @Auther: HR
 * @Date 2022/3/17 14:42
 * @Description:
 */
@Data
public class KunlunTokenDto {

    private String client_id;

    private String client_secret;

    private String grant_type; //授权方式，填写固定值 client_credentials

    private String scope; //您要访问的 API 对应的 Scope 名称
}
