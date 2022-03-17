package com.sjlh.kunlun.membership.dto;

import lombok.Data;

/**
 * @Auther: HR
 * @Date 2022/3/16 17:02
 * @Description:
 */
@Data
public class ProfileDto {

    private String id;
    private String firstName;
    private String lastName;
    private String altFirstName;
    private String altLastName;
    private GenderDto gender;


}
