package com.learn.spring.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
public class Oauth2ResourceServer {

    private static final String RESOURCE_ID = "belajar";

    @Configuration @Order(10)
    protected static class NonOauthResources extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/api/test").permitAll()
                    .antMatchers("/halo").hasAnyRole("ADMIN")
//                    .antMatchers("/api/halo").hasRole("ADMIN")
                    .and().anonymous();
        }
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            RemoteTokenServices tokenService = new RemoteTokenServices();
//            tokenService.setClientId("jsclient");
//            tokenService.setClientSecret("jspasswd");
            tokenService.setClientId("clientauthcode");
            tokenService.setClientSecret("123456");
            tokenService.setCheckTokenEndpointUrl("http://localhost:10001/oauth/check_token");
            
            resources
                    .resourceId(RESOURCE_ID)
                    .tokenServices(tokenService);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
            		.antMatchers("/api/halo").hasRole("ADMIN")
            		.antMatchers("/api/admin").authenticated();
//                    .antMatchers("/api/staff").hasRole("STAFF")
//                    .antMatchers("/api/client").access("#oauth2.hasScope('trust')")
//                    .antMatchers("/api/admin").hasRole("ADMIN");
        }

    }

}
