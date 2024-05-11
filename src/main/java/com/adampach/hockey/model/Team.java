package com.adampach.hockey.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false, unique = true)
    private String Name;

    @Column(nullable = false)
    private String Description;

    private String ImageUrl;

    private String City;

    @Column(nullable = false)
    private LocalDate EstablishmentDate;
}
