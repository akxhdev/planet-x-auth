package com.planetx.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alien {
    @Id
    private String alienId;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Boolean isEnabled;
    private List<Role> authorities;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Alien(String alienId, String name, String email, String password) {
        this.alienId = alienId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = new ArrayList<>(List.of(new Role("ROLE_ALIEN")));
        this.isEnabled = true;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = this.createdAt;
    }
}
