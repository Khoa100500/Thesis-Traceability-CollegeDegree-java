package com.iupv.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email")
    private String email;
}
