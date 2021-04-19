package com.ece356.demographics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/data/**").permitAll()
                .antMatchers("/read/**", "/create/**", "/update/**", "/delete/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/read/users/**", "/create/users/**", "/update/users/**", "/delete/users/**").hasRole("ADMIN")
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource);
//                .withUser(User.withUsername("root").password(passwordEncoder().encode("rtxon"))
//                        .roles("ADMIN"))
//                .withUser(User.withUsername("bob").password(passwordEncoder().encode("rtxoff"))
//                        .roles("USER"));
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}