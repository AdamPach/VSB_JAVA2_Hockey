package com.adampach.hockey.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MatchDetail {

    private int Id;

    private String Description;

    private String HomeTeamName;

    private int HomeTeamId;

    private int HomeScore;

    private String AwayTeamName;

    private int AwayTeamId;

    private int AwayScore;

    private LocalDate MatchDate;
}
