package com.blogapp.apis.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogapp.apis.security.CustomUserDetailService;
import com.blogapp.apis.security.JWTAuthenticationEntryPoint;
import com.blogapp.apis.security.JWTAuthenticationFilter;
import com.blogapp.apis.services.UserService;

@Configuration
@EnableMethodSecurity
//@EnableWebMvc 
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	private final String allPublicPermitUrl[] = {
			"/auth/login", 
			"/auth/register",
			"/v2/api-docs", 
			"/v3/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**", 
			"/webjars/**",
			"/v3/api-docs/**",
			"/swagger-ui/**"
	};
	
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;


	// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		/*
		 * AuthenticationManagerBuilder authenticationManagerBuilder = http
		 * .getSharedObject(AuthenticationManagerBuilder.class);
		 * authenticationManagerBuilder.userDetailsService(customUserDetailService).
		 * passwordEncoder(getPasswordEncoder());
		 * 
		 * //authenticationManager = authenticationManagerBuilder.build();
		 * 
		 * http.authorizeHttpRequests((authz) -> authz .anyRequest() .authenticated()
		 * .requestMatchers("/auth/login").permitAll()) .csrf().disable()
		 * .exceptionHandling(ex ->
		 * ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
		 * .sessionManagement(session ->
		 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		 * 
		 * http.addFilterBefore(jwtAuthenticationFilter,
		 * UsernamePasswordAuthenticationFilter.class); return http.build();
		 */
		
		
		 http.csrf(csrf -> csrf.disable())
         .authorizeRequests()
         .requestMatchers("/test").authenticated()
         //.requestMatchers("/auth/login").permitAll()
         .requestMatchers(allPublicPermitUrl).permitAll()
         .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
         .requestMatchers(HttpMethod.GET, "/**").permitAll()
         .anyRequest()
         .authenticated()
         .and().exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
 
		 http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		 return http.build();
		
		
	}

	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService() { UserDetails
	 * user1 =
	 * User.withDefaultPasswordEncoder().username("satish").password("satish").roles
	 * ("ADMIN") .build(); UserDetails user2 =
	 * User.withDefaultPasswordEncoder().username("warkad").password("warkad").roles
	 * ("ADMIN") .build(); UserDetails user3 =
	 * User.builder().username("user3").password(getPasswordEncoder().encode("user3"
	 * )) .roles("ADMIN").build(); return new InMemoryUserDetailsManager(user1,
	 * user2, user3); }
	 */

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

}
