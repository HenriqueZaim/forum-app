package com.br.ng.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    public UserDetailsService userDetailsService;

    private static final String[] PUBLIC_MATCHERS = {
        "/h2-console/**",
        "/"
    };

    private static final String[] PUBLIC_MATCHERS_GET = {
        "/register",
        "/topics/**",
        "/images/**"
    };

    private static final String[] PUBLIC_MATCHERS_POST = {
        "/register",
        "/images/users/**",
        "/images/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
            .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
            .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated()
        .and()
        .formLogin()
            .loginPage("/login").permitAll()
            .loginProcessingUrl("/login").usernameParameter("email").passwordParameter("password")
            .failureUrl("/login?error=true").defaultSuccessUrl("/")
        .and()
            .csrf().disable()
            .logout()
                .logoutSuccessUrl("/")
                .logoutUrl("/logout");
                
        http.headers().frameOptions().disable();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
