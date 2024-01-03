package com.blog.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String email;
        private String name;
        private String password;
        private String username;

        @ManyToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),//foreign key
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles ;

        // Constructors, getters, setters, etc.
    }



