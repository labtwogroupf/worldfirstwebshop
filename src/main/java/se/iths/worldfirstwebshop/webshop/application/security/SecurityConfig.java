package se.iths.worldfirstwebshop.webshop.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
                .requestMatchers("/api/inventory/**").hasAuthority(Role.ADMIN_AUTHORITY)
                .requestMatchers("/api/products/**").hasAuthority(Role.ADMIN_AUTHORITY)
                .requestMatchers("/api/shop/checkout").authenticated()
                .requestMatchers("/api/shop/**").permitAll()
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
                .requestMatchers("/error").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/mainSite").permitAll()
                .requestMatchers("/showCart").permitAll()
                .requestMatchers("/showInventory").permitAll()
                .requestMatchers("/addToCart").permitAll()
                .requestMatchers("/showCartForm").permitAll()
                .requestMatchers("/checkout").authenticated()
                .requestMatchers("/addProduct").hasAuthority(Role.ADMIN_AUTHORITY)
                .requestMatchers("/addProductToInventory").hasAuthority(Role.ADMIN_AUTHORITY)
                .requestMatchers("/showProductForm").hasAuthority(Role.ADMIN_AUTHORITY)
                .requestMatchers("/showAddToInventoryForm").hasAuthority(Role.ADMIN_AUTHORITY)
                .requestMatchers("/showProducts").hasAuthority(Role.ADMIN_AUTHORITY)
                .anyRequest().denyAll()
                .and()
                .formLogin()
                .defaultSuccessUrl("/mainSite")
                .and()
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
