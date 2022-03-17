package com.sjlh.kunlun.membership.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: HR
 * @Date 2022/3/16 14:51
 * @Description:
 */
@Setter
@Getter
public class KunlunTokenResponseDto {

    private String access_token;

    private Integer expires_in;

    private String token_type;
}
