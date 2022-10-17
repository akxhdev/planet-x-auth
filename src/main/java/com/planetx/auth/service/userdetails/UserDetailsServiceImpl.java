package com.planetx.auth.service.userdetails;

import com.planetx.auth.model.Alien;
import com.planetx.auth.model.Role;
import com.planetx.auth.repository.AlienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AlienRepository alienRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // find alien in the database
        Optional<Alien> alien = alienRepository.findByAlienId(username);

        // if not found then throw error
        alien.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));

        // create user object
        String alienId = alien.get().getAlienId();
        String password = alien.get().getPassword();
        List<Role> authorities = alien.get().getAuthorities();

        return new User(alienId, password, authorities);
    }
}
