package se.iths.worldfirstwebshop.webshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static se.iths.worldfirstwebshop.webshop.security.Role.ADMIN;
import static se.iths.worldfirstwebshop.webshop.security.Role.ADMIN_AUTHORITY;

@Configuration
public class SecurityConfig {


    @Bean
    @Order(1)
    public SecurityFilterChain filterChainForRestApi(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .securityMatcher("/api/**")
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/inventory/**").hasAuthority(ADMIN_AUTHORITY)
                .requestMatchers("/api/products/**").hasAuthority(ADMIN_AUTHORITY)
                .requestMatchers("/api/shop/**").hasAuthority(ADMIN_AUTHORITY)
                .requestMatchers("/api/sold").hasAnyAuthority(ADMIN_AUTHORITY)
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf()
                .ignoringRequestMatchers("/register")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/mainSite").permitAll()
                .requestMatchers("/showProducts").authenticated()
                .requestMatchers("/showInventory").authenticated()
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
