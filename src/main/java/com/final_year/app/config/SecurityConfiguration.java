package com.final_year.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


//To Handle Security Related Configuration

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	
	
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	       
		
		 
		   auth.jdbcAuthentication().dataSource(dataSource)
		   .authoritiesByUsernameQuery("SELECT username, authority_Type FROM authorities WHERE username = ?")
		   .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
		   .passwordEncoder(passwordEncoder());
	    }
	    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http.authorizeRequests()
		
		.antMatchers("/admin/**").access("hasRole('ADMIN')")
		.antMatchers("/result/**").access("hasAnyRole('ADMIN','TEACHER')")
		
		.and().logout().logoutSuccessUrl("/login?logout")
		.and().formLogin().loginPage("/login").successHandler(savedRequestAwareAuthenticationSuccessHandler())
		.loginProcessingUrl( "/j_spring_security_check" )
		.failureUrl("/login?error")
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/").and()
		.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.rememberMe().key("remember-me").rememberMeParameter("remember-me").rememberMeCookieName("remember-me").tokenValiditySeconds(1209600);
		
	}
	
	//ignore resource file,can access
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	
	
	
	
	
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler(){
		
		SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler=new SavedRequestAwareAuthenticationSuccessHandler();
		
		return authenticationSuccessHandler;
	}
	
	 //to encode password
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}




