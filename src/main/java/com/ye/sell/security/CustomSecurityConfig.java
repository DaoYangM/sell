package com.ye.sell.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/seller/login").failureUrl("/seller/login?error=true").loginProcessingUrl("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/seller/logout"))
                    .logoutSuccessUrl("/seller/login")
                .and()
                .authorizeRequests()
                .antMatchers("/seller/*").hasRole("ADMIN")
                .antMatchers("/buyer/**").permitAll()
                .antMatchers("/pay/**").permitAll()
                .antMatchers("/wechat/**").permitAll()
                .antMatchers("/webSocket/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/mp3/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }

}
