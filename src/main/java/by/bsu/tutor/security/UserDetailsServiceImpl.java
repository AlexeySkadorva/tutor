package by.bsu.tutor.security;

import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.administration.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDetails userDetails;
        User user = userService.findByLogin(login);
        if (user != null) {
            Set<GrantedAuthority> roles = Stream.of(user.getRole()).map(r -> new SimpleGrantedAuthority(r.getCode().name())).collect(Collectors.toSet());
            userDetails = new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, roles);
        } else {
            userDetails = new org.springframework.security.core.userdetails.User("none", "none", new HashSet<>());
        }
        return userDetails;
    }

}
