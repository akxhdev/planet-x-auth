package com.planetx.auth.service.auth;

import com.planetx.auth.exceptions.AuthException;

public interface AuthService {
    String authenticate(String username, String password) throws AuthException;
}
