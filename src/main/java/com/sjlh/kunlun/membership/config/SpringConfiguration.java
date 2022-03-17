/**
 *
 */
package com.sjlh.kunlun.membership.config;

import com.sjlh.kunlun.membership.member.service.DefaultKunlunMemberService;
import com.sjlh.kunlun.membership.member.service.KunlunMemberService;
import com.sjlh.kunlun.membership.member.service.TokenService;
import com.sjlh.kunlun.membership.push.KunlunAuthInvoker;
import com.sjlh.kunlun.membership.push.MemberInvoker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sjlh.hotel.common.annotation.EnableRPCConfigure;
import com.sjlh.hotel.common.net.Invoker;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Administrator
 *
 */
@Configuration
@EnableConfigurationProperties(MemberConfiguration.class)
@EnableRPCConfigure
public class SpringConfiguration {
    @Bean
    @ConditionalOnMissingBean(KunlunMemberService.class)
    public KunlunMemberService getKunlunMemberService(){
        return new DefaultKunlunMemberService();
    }

    @Bean
    @ConditionalOnMissingBean(TokenService.class)
    public TokenService getTokenService(){
        return new TokenService();
    }

    @Bean
    public Invoker getKunlunInvoker(){
        return new MemberInvoker();
    }

    @Bean
    public Invoker getKunlunAuthInvoker(){
        return new KunlunAuthInvoker();
    }

    @Bean("tokenRedisTemplate")
    @ConditionalOnMissingBean(RedisTemplate.class)
    public RedisTemplate<String, String> getRateSwitchRedisTemplate(@Qualifier("tokenRedisConnectionFactory") RedisConnectionFactory tokenRedisConnectionFactory) {
        RedisTemplate<String, String> redis = new RedisTemplate<>();
        redis.setKeySerializer(new StringRedisSerializer());
        redis.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redis.setConnectionFactory(tokenRedisConnectionFactory);
        return redis;
    }

    @Bean("tokenRedisConnectionFactory")
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public RedisConnectionFactory createRedisConnectionFactory(
            @Value("${kunlun.redis.host}") String hostName, @Value("${kunlun.redis.password}") String password,
            @Value("${kunlun.redis.port}") Integer port, @Value("${kunlun.redis.database:1}") Integer database) {
        return lettuceConnectionFactory(hostName, password, port, database);
    }

    private LettuceConnectionFactory lettuceConnectionFactory(String hostName, String password, Integer port,
                                                              Integer database) {
        RedisStandaloneConfiguration
                server = new RedisStandaloneConfiguration();
        server.setHostName(hostName);
        RedisPassword
                redisPassword = RedisPassword.of(password);
        server.setPassword(redisPassword);
        server.setDatabase(database);
        server.setPort(port);
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(server);
        lettuceConnectionFactory.afterPropertiesSet();
        return lettuceConnectionFactory;
    }

}
