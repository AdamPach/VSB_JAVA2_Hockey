package com.adampach.hockey.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class UpdateMatch {

    private String Description;

    @NotBlank
    private int HomeTeamId;

    @NotBlank
    private int HomeScore;

    @NotBlank
    private int AwayTeamId;

    @NotBlank
    private int AwayScore;

    @NotBlank
    private LocalDate MatchDate;
}
