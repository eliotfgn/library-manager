package com.eliotfgn.librarymanagerapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @NotBlank(message = "Username required")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Password required")
    private String password;
    @Email
    @NotBlank(message = "Email required")
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private Boolean enabled;
    private Instant createdDate;
    private Boolean canReserve;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
