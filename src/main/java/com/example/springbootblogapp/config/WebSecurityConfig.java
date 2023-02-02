package com.example.springbootblogapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig {
        @Bean
        public static PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        private static final String[] WHITELIST = {
                "/register/**",
                "/h2-console/*",
                "/"
        };
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
            http
                    .authorizeHttpRequests()
                    .requestMatchers("/").permitAll()
                    .requestMatchers(WHITELIST).permitAll()
                    .requestMatchers(HttpMethod.GET,"/post/*").permitAll()
                    .anyRequest().authenticated().and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/",true)
                    .failureUrl("/login?error")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout?")
                    .and()
                    .httpBasic();

            // TODO: h2-console ile işin bittiğinde bunu kaldırabilirsin
            http.csrf().disable();
            http.headers().frameOptions().disable();
            //

            return http.build();
        }

}
