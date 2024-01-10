package ru.miit.webapp.configs;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import ru.miit.webapp.models.UserRoles;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, SecurityContextRepository repository) throws Exception {
        httpSecurity
                .authorizeHttpRequests(
                        authorizeHttpRequests ->
                                authorizeHttpRequests
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                        .permitAll()
                                        .requestMatchers("/", "/users/login", "/users/login/", "/users/register", "/users/register/author", "/users/register/reader", "/users/login-error", "/books/all")
                                        .permitAll()
                                        .requestMatchers("/readers/profile", "/readers/reader-details/").hasRole(UserRoles.READER.name())
                                        .requestMatchers("/authors/profile", "/authors/author-details/", "/books/add").hasRole(UserRoles.AUTHOR.name())
                                        .requestMatchers("/authors/author-delete/", "/readers/reader-delete/", "/books/book-delete/").hasRole(UserRoles.ADMIN.name())
                                        .anyRequest().authenticated()
                )
                .formLogin(
                        (formLogin) ->
                                formLogin
                                        .loginPage("/users/login")
                                        .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                        .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                        .defaultSuccessUrl("/")
                                        .failureForwardUrl("/users/login-error")
                )
                .logout(
                        (logout) ->
                                logout
                                        .logoutUrl("/users/logout")
                                        .logoutSuccessUrl("/")
                                        .invalidateHttpSession(true)
                )
                .securityContext(
                        securityContext ->
                                securityContext
                                        .securityContextRepository(repository)
                );

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityContextRepository getSecurityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }
}
