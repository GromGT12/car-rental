package pl.maks.carrental.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String ADMIN = "ADMIN";
    private static final String CLIENT = "CLIENT";

    @Bean
    public InMemoryUserDetailsManager users() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("client")
                .password("password")
                .roles(CLIENT)
                .build();
        UserDetails admin = users
                .username("admin")
                .password("adminPassword")
                .roles(ADMIN, CLIENT)
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/cars/", "/parkings/").hasRole(CLIENT)
                        .requestMatchers(HttpMethod.POST, "/clients/", "/cars/", "/parkings/", "/contracts/").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.PUT, "/clients/", "/contracts/").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/clients/").hasRole(ADMIN)
                        .anyRequest().authenticated())
                .build();
    }
}

