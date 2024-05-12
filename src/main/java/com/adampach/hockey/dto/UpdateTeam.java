package com.adampach.hockey.dto;

import com.adampach.hockey.model.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateTeam {
    @NotBlank
    private String Name;

    @NotBlank
    private String Description;

    private String ImageUrl;

    private String City;

    @NotBlank
    private LocalDate EstablishmentDate;

    @JsonIgnore
    public Team getTeam() {
        Team team = new Team();

        team.setName(this.Name);
        team.setDescription(this.Description);
        team.setImageUrl(this.ImageUrl);
        team.setCity(this.City);
        team.setEstablishmentDate(this.EstablishmentDate);

        return team;
    }
}
