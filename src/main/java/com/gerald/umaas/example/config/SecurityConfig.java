package com.gerald.umaas.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

import com.gerald.umaas.example.security.AppAuthenticationProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private AppAuthenticationProvider provider;
	private static final String APP_KEY = "helloUMAAS";
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
          .authorizeRequests()
          .antMatchers("/home/**")
          .authenticated()
          .antMatchers("/regular/**")
          .hasAnyRole("REGULAR")
          .antMatchers("/admin/**")
          .hasAnyRole("ADMIN")
          .anyRequest().permitAll()
          .and()
          .formLogin()
          .loginPage("/login")
          .permitAll()
          .and()
          .httpBasic()
          .and()
          .logout()
          .logoutSuccessUrl("/login")
          .and()
          .rememberMe()
          .tokenValiditySeconds(30)
          .key(APP_KEY);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(provider)
		.userDetailsService(provider);
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
