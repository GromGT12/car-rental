package pl.maks.carrental.security;
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
    @Bean
    public InMemoryUserDetailsManager users() {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("client")
                .password("password")
                .roles("CLIENT")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("chacha")
                .roles("ADMIN")
                .authorities("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.GET, "/clients/**", "/contracts/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/clients", "/contracts/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/clients/**", "/contracts/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/clients/**").hasRole("ADMIN")
                        .requestMatchers("/hello/**").hasRole("HELLO")
                        .requestMatchers("/random-joke").permitAll()
                        .anyRequest().authenticated())
                .build();
    }
}

