package com.example.cuoiky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private final UserDetailsServiceImpl userDetailsServiceImpl;

	public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }
	
	@Bean
	public BCryptPasswordEncoder byBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/* .csrf().disable() */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        
        
        		/* .csrf().disable() */
				/*
				 * .authorizeRequests().antMatchers("/").permitAll()
				 * .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				 */
				 .authorizeRequests() 
                 .antMatchers("/book-photos/**", "/css/**", "/js/**", 
                		 "/lib/**", "/mail/**", "/scss/**", "/test/**", "/img/**", "/books", "/registrantion", "/shop/detail", "/shop", "/", "/login").permitAll()
				/* .antMatchers("/admin/**").hasRole("admin") */
                 .anyRequest().authenticated() //Tất cả các request khác đều cần phải xác thực mới được truy cập
                 .and()
                .formLogin()
                 .loginPage("/login")// dùng trang login đc custom
                 .permitAll()// tất cả đều đc truy cập vào địa chỉ này để login 
                 .and()
                .logout().logoutUrl("/logout") // cho phép log out mặc định tạo ra 1 trang logout vs địa chỉ logout
                .logoutSuccessUrl("/")
                .permitAll();
       http.exceptionHandling().accessDeniedPage("/login?accessDenied");
        
    }
	
	@Bean
	public AuthenticationManager customeAuthenticationManager() throws Exception{
		return authenticationManager();
	}
	
//	@Autowired
//	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(byBCryptPasswordEncoder());
//    }
}

