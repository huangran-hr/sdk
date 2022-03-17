package com.sjlh.kunlun.membership.member.service;

import com.sjlh.kunlun.membership.dto.ProfileDto;

public interface KunlunMemberService {

    ProfileDto getProfiles(String profileId);
}
