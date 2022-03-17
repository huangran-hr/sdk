/**
 *
 */
package com.sjlh.kunlun.membership.config;

import com.sjlh.kunlun.membership.core.AuthConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Administrator
 *
 */
@Setter
@Getter
@ConfigurationProperties("kunlun.member")
public class MemberConfiguration {
	/**
	 * 接口host
	 */
	private String host;

	private AuthConfiguration auth;
}
