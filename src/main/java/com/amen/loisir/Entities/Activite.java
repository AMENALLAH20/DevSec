package com.amen.loisir.Entities;



import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String image;

    private String title;
    private String rate;

    private String location;

    private Integer availableSeats;
    private String openingHours;
    private String closingHours;
    private String description;




    @Enumerated(EnumType.STRING)
    private ActiviteType activitetype;



    /*@ManyToMany(mappedBy = "activities",cascade = CascadeType.REMOVE)
    private List<User> users;*/

    @ManyToOne
    @JoinColumn(name = "user_id")  // Lien avec la table User
    private User user;

}