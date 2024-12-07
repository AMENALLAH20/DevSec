package com.amen.loisir.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequest {
    private Integer nbrper;
    private LocalDate date;
    private Long userId;
    private Long activityId;

    // Getters and Setters
}
