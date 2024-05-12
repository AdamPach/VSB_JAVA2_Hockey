package com.adampach.hockey.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {"home_team_id", "away_team_id"})})
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String Description;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    @JsonIgnore
    private Team HomeTeam;

    @Column(name = "home_team_id", nullable = false, updatable = false, insertable = false)
    private int HomeTeamId;

    @Column(nullable = false)
    private int HomeTeamScore = 0;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    @JsonIgnore
    private Team AwayTeam;

    @Column(name = "away_team_id", nullable = false, updatable = false, insertable = false)
    private int AwayTeamId;

    @Column(nullable = false)
    private int AwayTeamScore = 0;
}
