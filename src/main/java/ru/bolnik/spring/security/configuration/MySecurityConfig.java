package ru.bolnik.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public MySecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
//        UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser(userBuilder.username("zaur")
//                        .password("zaur")
//                        .roles("EMPLOYEE"))
//                .withUser(userBuilder.username("dima")
//                        .password("dima")
//                        .roles("MANAGER", "HR"))
//                .withUser(userBuilder.username("elena")
//                        .password("elena")
//                        .roles("HR"));

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/")
                .hasAnyRole("EMPLOYEE", "MANAGER", "HR")
                .antMatchers("/hr_info").hasRole("HR")
                .antMatchers("/manager_info/**").hasAnyRole("MANAGER")
                .and().formLogin().permitAll();
    }
}
