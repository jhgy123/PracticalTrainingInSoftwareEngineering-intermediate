package com.example.elmserver.security.config;

import com.example.elmserver.security.JWT.JwtAuthenticationFilter;
import com.example.elmserver.security.JWT.JwtUtils;
import com.example.elmserver.security.JWT.hanlder.AuthenticationFailureHandlerImpl;
import com.example.elmserver.security.JWT.hanlder.AuthenticationSuccessHandlerImpl;
import com.example.elmserver.security.JWT.manager.AuthenticationManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableConfigurationProperties({ LoginSecurityProperties.class, SecurityProperties.class })
public class DefaultSecurityConfig implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private LoginSecurityProperties loginSecurityProperties;

    @Bean
    public JwtUtils jwtUtils() {
        JwtUtils utils = new JwtUtils();
        utils.setLoginSecurityProperties(loginSecurityProperties);
        return utils;
    }


    /**
     * @param providers
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(org.springframework.security.authentication.AuthenticationManager.class)
    public org.springframework.security.authentication.AuthenticationManager providerManager(
            List<AuthenticationProvider> providers) {
        return new ProviderManager(providers);
    }

    @ConditionalOnMissingBean(PermissionEvaluator.class)
    public PermissionEvaluator allowPermissionEvaluatorImpl() {
        return new AllowPermissionEvaluatorImpl();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(
            ApplicationEventPublisher applicationEventPublisher) {
        return new AuthenticationSuccessHandlerImpl(jwtUtils(), authenticationManager, applicationEventPublisher);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandlerImpl();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Bean
    public SecurityFilterChain sysSecurityFilterChain(HttpSecurity http,
                                                      JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

//        var getIgnoreToken = new String[]{"/orderdetailet/**","/orders/**","/cart/**","/user/**","/doc.html", "/pub/**", "/swagger-ui/**", "/webjars/**", "/v3/**"};
        var getIgnoreToken = new String[]{"/doc.html", "/pub/**", "/swagger-ui/**", "/webjars/**", "/v3/**"};
        // @formatter:off
        http.requestMatcher(new AntPathRequestMatcher( "/**"))
                .authorizeRequests()
                .antMatchers(getIgnoreToken).permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);
        // @formatter:on
        return http.build();
    }

}
