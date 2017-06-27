package com.github.hippoom.wechat.mp.autoconfigure.security.web;

import static org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED;

import com.github.hippoom.wechat.mp.security.web.RestAuthenticationEntryPoint;
import com.github.hippoom.wechat.mp.security.web.authentication.WeChatMpOAuth2AuthenticationProcessingFilter;
import com.github.hippoom.wechat.mp.security.web.authentication.WeChatMpOAuth2AuthenticationSuccessHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
public class WeChatMpWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Getter
    @Autowired
    private WxMpService wxMpService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception {
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        configureAuthorizeRequests(defaultHttp(http));
    }

    protected void configureAuthorizeRequests(HttpSecurity httpSecurity)
        throws Exception {
        // @formatter:off
        httpSecurity
            .antMatcher("/**").authorizeRequests()
                .antMatchers("/rel/**/me").authenticated()
                .anyRequest().permitAll();
        // @formatter:on
    }

    protected HttpSecurity defaultHttp(HttpSecurity http) throws Exception {
        // @formatter:off
        return http.sessionManagement().sessionCreationPolicy(IF_REQUIRED)
            .and()
                .csrf().requireCsrfProtectionMatcher(requireCsrfProtectionMatcher())
                    .csrfTokenRepository(csrfTokenRepository())
            .and()
                .addFilterAfter(weChatMpOAuth2AuthenticationProcessingFilter(wxMpService),
                    CsrfFilter.class)
                .exceptionHandling()
                    .authenticationEntryPoint(restAuthenticationEntryPoint())
            .and();
        // @formatter:on
    }

    private AntPathRequestMatcher requireCsrfProtectionMatcher() {
        return new AntPathRequestMatcher("/rel/**/me");
    }

    @Bean
    protected CsrfTokenRepository csrfTokenRepository() {
        return CookieCsrfTokenRepository.withHttpOnlyFalse();
    }

    @Bean
    protected CsrfAuthenticationStrategy sessionAuthenticationStrategy() {
        return new CsrfAuthenticationStrategy(csrfTokenRepository());
    }


    @Bean
    protected RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    protected WeChatMpOAuth2AuthenticationProcessingFilter
        // @formatter:off
        weChatMpOAuth2AuthenticationProcessingFilter(WxMpService wxMpService) {

        WeChatMpOAuth2AuthenticationProcessingFilter filter =
            new WeChatMpOAuth2AuthenticationProcessingFilter("/wechat/oauth/token");
        filter.setWxMpService(wxMpService);
        filter
            .setAuthenticationSuccessHandler(new WeChatMpOAuth2AuthenticationSuccessHandler());
        filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        return filter;
    }
    // @formatter:on
}
