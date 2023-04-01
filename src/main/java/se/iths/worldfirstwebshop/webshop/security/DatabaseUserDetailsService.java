package se.iths.worldfirstwebshop.webshop.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

/*** This class is for doing own implementation of UserDetailsService
 * When commented out, the default implementation is used and password is shown in console.
 * ***/

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private UserCredentialsRepository repository;

    public DatabaseUserDetailsService(UserCredentialsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredentials userCredentials = repository.findByName(username);
        if (userCredentials == null)
            throw new UsernameNotFoundException("username not found");
        return new User(userCredentials.getName(),userCredentials.getPassword(), Collections.singleton(userCredentials.getRole()));
    }
}
