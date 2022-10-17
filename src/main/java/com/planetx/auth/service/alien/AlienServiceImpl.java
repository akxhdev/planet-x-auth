package com.planetx.auth.service.alien;

import com.planetx.auth.exceptions.AlienNotFoundException;
import com.planetx.auth.model.Alien;
import com.planetx.auth.model.AlienUpdateRequest;
import com.planetx.auth.model.CreateAlienRequest;
import com.planetx.auth.repository.AlienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AlienServiceImpl implements AlienService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AlienRepository alienRepository;

    @Override
    public Alien createAlien(CreateAlienRequest request) {
        String alienId = request.getAlienId();
        String email = request.getEmail();
        String name = request.getName();
        String password = passwordEncoder.encode(request.getPassword());

        return alienRepository.insert(new Alien(alienId, name, email, password));
    }

    @Override
    public Alien updateAlien(AlienUpdateRequest request) throws AlienNotFoundException {
        UserDetails userDetails = (UserDetails) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        String alienId = userDetails.getUsername();

        Optional<Alien> alien = alienRepository.findByAlienId(alienId);

        String newName = request.getName();
        String newPassword = request.getPassword();

        if (alien.isPresent()) {
            if (newName != null) alien.get().setName(newName);
            if (newPassword != null) alien.get().setPassword(newPassword);

            alien.get().setModifiedAt(LocalDateTime.now());

            return alienRepository.save(alien.get());
        }

        throw new AlienNotFoundException();
    }

}
