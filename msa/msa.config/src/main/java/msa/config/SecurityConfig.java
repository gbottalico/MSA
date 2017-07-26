package msa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import msa.config.filters.SecurityFilter;
import msa.config.interceptors.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityFilter filter;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//		http
//		.csrf().disable().authorizeRequests()
//				.antMatchers("/*", "/403.html", "/api/login/user", "/api/v2/api-docs", "/metrics/*", "/env" , "/img/*").permitAll()
//				.anyRequest().authenticated()
//				.and()
////				.addFilterBefore(corsFilter, ChannelProcessingFilter.class)
//                .addFilterBefore(filter, BasicAuthenticationFilter.class)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
    }

}
