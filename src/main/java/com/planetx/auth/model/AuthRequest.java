package com.planetx.auth.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String alienId;
    private String password;
}
