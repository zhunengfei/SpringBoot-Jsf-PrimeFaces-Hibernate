package org.ryi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		http
         .authorizeRequests()
             .antMatchers("/", "/home").permitAll()
             .anyRequest().authenticated()
             .and()
         .formLogin()
             .loginPage("/login")
             .permitAll()
             .and()
         .logout()
             .permitAll();
		*/
	  
		/******
		 http.authorizeRequests().antMatchers("/","/product/**","/user/**")
		 
		.access("hasRole('ADMIN')").and().formLogin()
		.loginPage("/login.xhtml").failureUrl("/login.xhtml")
		.usernameParameter("username")
		.passwordParameter("password")
		.and().logout().logoutSuccessUrl("/login.xhtml")
		.and().csrf()
		.and().exceptionHandling().accessDeniedPage("/errors.xhtml");
	    
	    */
	 // Have to disable it for POST methods:
        // http://stackoverflow.com/a/20608149/1199132
        //http.csrf().disable();

        // Logout and redirection:
        // http://stackoverflow.com/a/24987207/1199132
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login/login.xhtml");

        http.authorizeRequests()
                // Some filters enabling url regex:
                // http://stackoverflow.com/a/8911284/1199132
                /*****
                .regexMatchers( "\\A/page1.xhtml\\?param1=true\\Z", "\\A/page2.xhtml.*")
                .permitAll()
                //Permit access for all to error and denied views
                .antMatchers("/500.xhtml", "/denied.xhtml")
                .permitAll()
                */
                // Only access with admin role
                .antMatchers("/","/product/**","/user/**") .hasRole("ADMIN")
                /*****
                //Permit access only for some roles
                .antMatchers("/page3.xhtml")
                .hasAnyRole("ADMIN", "MANAGEMENT")
                */
                //If user doesn't have permission, forward him to login page
                .and()
                .formLogin()
                .loginPage("/login/login.xhtml")
                .loginProcessingUrl("/login/login")
                .defaultSuccessUrl("/main.xhtml")
                .and().exceptionHandling().accessDeniedPage("/denied.xhtml");
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}