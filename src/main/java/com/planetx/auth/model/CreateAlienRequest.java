package com.planetx.auth.model;

import lombok.Data;

@Data
public class CreateAlienRequest {
    private String alienId;
    private String name;
    private String email;
    private String password;
}
