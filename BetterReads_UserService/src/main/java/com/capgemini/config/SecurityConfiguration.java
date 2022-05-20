package com.capgemini.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		    .csrf().disable()
		    .authorizeRequests()
		   // .antMatchers("/public/**").hasRole("NORMAL")
		    .antMatchers("/api/u1/**").hasRole("ADMIN")
		    .antMatchers("/api/auth/**").permitAll()
		    .anyRequest()
		    .authenticated()
		    .and()
		    .httpBasic();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
  	  auth.inMemoryAuthentication().withUser("Akshay").password(this.passwordEncoder().encode("akki")).roles("NORMAL");
  	  auth.inMemoryAuthentication().withUser("Manya").password(this.passwordEncoder().encode("manu")).roles("ADMIN");
    }
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder(10);
	}
}
