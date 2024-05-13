package com.adampach.hockey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "Team", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Player> Players;

    @OneToMany(mappedBy = "HomeTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Match> HomeMatches;

    @OneToMany(mappedBy = "AwayTeam", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Match> AwayMatches;
}
