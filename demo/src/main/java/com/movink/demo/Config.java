package com.movink.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.movink.Services.USerDetailsServiceImpl;

@Configuration
public class Config {

    // @Autowired
    // private UserDetailsService userDetailsService;

    @Bean
    public UserDetailsService getUserDetailsService() {
        return new USerDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {

                    auth.requestMatchers("/register").permitAll();
                    auth.requestMatchers("/signin").permitAll();
                    auth.requestMatchers("/Add_movie").permitAll();
                    auth.requestMatchers("/Add_song").permitAll();
                    auth.requestMatchers("/findall").permitAll();
                    auth.requestMatchers("/Recommend_Movie").permitAll();
                    auth.requestMatchers("/delete_movie").permitAll();
                    auth.requestMatchers("All_movie").permitAll();
                    auth.requestMatchers("/delete_song").permitAll();
                    auth.requestMatchers("/All_songs").permitAll();
                    auth.requestMatchers("/Add_watch_movie").permitAll();
                    auth.requestMatchers("/getMovie_by_id").permitAll();
                    auth.requestMatchers("/delete_recommended_movie").permitAll();
                    auth.requestMatchers("/delete_watch_movie").permitAll();
                }

                ).httpBasic();

        return httpSecurity.build();

    }

}
