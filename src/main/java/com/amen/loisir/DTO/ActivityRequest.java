package com.amen.loisir.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityRequest {

    private String title;
    private String description;
    private String image;
    private String rate;
    private String location;
    private int availableSeats;
    private String openingHours;
    private String closingHours;
    private String activitetype;
    private Long userId; // ID de l'utilisateur



    // Getters et setters
}

