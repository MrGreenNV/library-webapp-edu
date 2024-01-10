package ru.miit.webapp.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.miit.webapp.repositories.AuthorRepository;

import java.util.stream.Collectors;

@AllArgsConstructor
public class AuthorDetailsServiceImpl implements UserDetailsService {

    private final AuthorRepository authorRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authorRepository.findByName(username)
                .map(author -> new User(
                        author.getName(),
                        author.getPassword(),
                        author.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                                .collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException("Автор с именем " + username + " не найден!"));
    }
}
