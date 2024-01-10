package ru.miit.webapp.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.miit.webapp.repositories.ReaderRepository;

import java.util.stream.Collectors;

@AllArgsConstructor
public class ReaderDetailsServiceImpl implements UserDetailsService {

    private final ReaderRepository readerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return readerRepository.findByName(username)
                .map(reader -> new User(
                        reader.getName(),
                        reader.getPassword(),
                        reader.getRoles().stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                                .collect(Collectors.toList())
                )).orElseThrow(() -> new UsernameNotFoundException("Читатель с именем " + username + " не найден!"));
    }
}
