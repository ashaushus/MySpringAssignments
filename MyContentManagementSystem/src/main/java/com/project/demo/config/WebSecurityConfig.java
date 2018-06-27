package com.project.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//import com.mohsenj.module.user.service.MUserDetailsService;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
@Profile(value = {"dev", "prod"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
    private UserDetailsService userDetailsService;
	
    @Bean
    public UserDetailsService userDetailsService() {
       // return super.userDetailsService();
    	return userDetailsService;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().anyRequest().authenticated().and()
        .formLogin().and().httpBasic().disable();
    }

    @Autowired
	DataSource dataSource;
    
    @Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from user where username=?");
	}
}