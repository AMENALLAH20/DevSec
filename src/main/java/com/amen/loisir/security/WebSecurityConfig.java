package com.amen.loisir.security;



import com.amen.loisir.security.Jwt.AuthEntryPointJwt;
import com.amen.loisir.security.Jwt.AuthTokenFilter;
import com.amen.loisir.security.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(

        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/ws/**").permitAll()  // Permet l'accès sans authentification à l'API SOAP

                .antMatchers("/signin").permitAll()

                .antMatchers("/user/**").permitAll()
                .antMatchers("/activity/id").permitAll()  // Permet l'accès sans authentification
                .antMatchers("/activity/**").permitAll()
                .antMatchers("/activity/sport").permitAll()
                .antMatchers("/activity/restaurant").permitAll()  // Permet l'accès sans authentification
                .antMatchers("/auth/**").permitAll()  // Permet l'accès sans authentification
                .antMatchers("/role/add**").permitAll()
                .antMatchers("/signup**").permitAll()
                .antMatchers("/signin**").permitAll()
                .antMatchers("/resetPassword").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/reservation/**").permitAll()  // Nécessite une authentification pour la réservation
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
