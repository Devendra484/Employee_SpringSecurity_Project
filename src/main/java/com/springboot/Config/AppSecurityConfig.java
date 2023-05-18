package com.springboot.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.springboot.service.UsersService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppSecurityConfig {

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new MySimpleUrlAuthenticationSuccessHandler();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsersService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/home","/signup","/reg","/signin").permitAll()
				.and()
				.authorizeHttpRequests()
				.requestMatchers("/user", "/admin", "/**").authenticated()
				.and()
				.exceptionHandling()
				.accessDeniedPage("/unauthorized")
				.and()
				.oauth2Login()
				.defaultSuccessUrl("/user",true)
				.failureUrl("/signin?failed")
				.and().formLogin(
						form -> form
								 //.defaultSuccessUrl("/user ", true)
								.loginPage("/signin")
									.successHandler(myAuthenticationSuccessHandler())
								.failureUrl("/signin?failed")
								.permitAll())
				.logout(form -> form
						.logoutSuccessUrl("/signin?logout").permitAll())

				.build();

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authenticationProvider;

	}

}
