package com.adampach.hockey.dto;

import com.adampach.hockey.model.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdatePlayer {

    @NotBlank
    private String FirstName;

    @NotBlank
    private String LastName;

    private String Email;

    @NotBlank
    private LocalDate BirthDate;

    @JsonIgnore
    public Player getPlayer() {
        Player player = new Player();

        player.setFirstName(FirstName);
        player.setLastName(LastName);
        player.setEmail(Email);
        player.setBirthDate(BirthDate);

        return player;
    }
}
