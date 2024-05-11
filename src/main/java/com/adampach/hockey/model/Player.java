package com.adampach.hockey.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false)
    private String FirstName;

    @Column(nullable = false)
    private String LastName;

    private String Email;

    @Column(nullable = false)
    private LocalDate BirthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    @JsonIgnore
    @JsonBackReference
    private Team Team;
}
