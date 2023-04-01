package se.iths.worldfirstwebshop.webshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    //TODO Configure and fix the issue below.
    /*** This configuration is incorrect but is afaik working as expected other than one bug. When you log in with the
     * wrong user in the webapp from Chrome guest mode you have to restart Chrome in order to be able to log in
     * with the correct user. But it works as expected when you log in with the correct user from start.
     */

    @Bean
    @Order(1)
    public SecurityFilterChain filterChainForRestApi(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .securityMatcher("/api/**")
                .csrf().disable()
                .authorizeHttpRequests()
            //    .requestMatchers("/api/**").permitAll()
           //     .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
           //     .requestMatchers(HttpMethod.GET, "/api/inventory/**").permitAll()
                .requestMatchers("/api/inventory/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/products/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/shop/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
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
                .formLogin(); //used by Browser
        //  .httpBasic(); //used by Insomnia

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
