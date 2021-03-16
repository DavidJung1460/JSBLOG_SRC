/*
 * package com.cos.blog.configuration;
 * 
 * import javax.annotation.PostConstruct;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.session.data.redis.RedisOperationsSessionRepository;
 * import org.springframework.session.data.redis.config.annotation.web.http.
 * EnableRedisHttpSession;
 * 
 * @EnableRedisHttpSession public class HttpSessionConfiguration {
 * 
 * @Value("${server.session.timeout}") private Integer
 * maxInactiveIntervalInMinutes;
 * 
 * @Inject private RedisOperationsSessionRepository sessionRepository;
 * 
 * @PostConstruct private void afterPropertiesSet() {
 * sessionRepository.setDefaultMaxInactiveInterval(maxInactiveIntervalInMinutes
 * 60); } }
 */