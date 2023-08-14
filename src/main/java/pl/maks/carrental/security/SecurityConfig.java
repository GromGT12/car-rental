package pl.maks.carrental.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
        return  new InMemoryUserDetailsManager(user, admin);

    }
}
