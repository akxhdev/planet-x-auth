package com.planetx.auth.service.alien;

import com.planetx.auth.exceptions.AlienNotFoundException;
import com.planetx.auth.model.Alien;
import com.planetx.auth.model.AlienUpdateRequest;
import com.planetx.auth.model.CreateAlienRequest;

public interface AlienService {
    Alien createAlien(CreateAlienRequest request);

    Alien updateAlien(AlienUpdateRequest request) throws AlienNotFoundException;
}
