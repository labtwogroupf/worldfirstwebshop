package se.iths.worldfirstwebshop.webshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain filterChainForRestApi(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .securityMatcher("/api/**")
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll() // Allow all users to access the /api/products endpoints
                .requestMatchers(HttpMethod.GET, "/api/inventory/**").permitAll()
                .requestMatchers("/api/inventory/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/products/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/shop/**").hasAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .ignoringRequestMatchers("/register")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/mainSite").permitAll()
                .requestMatchers("/showProducts").authenticated()
                .requestMatchers("/showInventory").hasAuthority("ROLE_ADMIN") // Use hasAuthority() with the complete role name
                .anyRequest().denyAll()
                .and()
                .formLogin(); //Used by Browser
        //  .httpBasic(); //Used by Insomnia

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
