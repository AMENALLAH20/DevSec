package com.amen.loisir.Services;


import com.amen.loisir.Entities.Activite;
import com.amen.loisir.Entities.Reservation;
import com.amen.loisir.Entities.User;
import com.amen.loisir.Repositories.ActivityRepository;
import com.amen.loisir.Repositories.ReservationRepository;
import com.amen.loisir.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

    /*public Reservation createReservation(Integer userId, Integer activityId, LocalDate date,Integer nbr) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Activite activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setActivity(activity);
        reservation.setDate(date);
        reservation.setNbrper(nbr);

        return reservationRepository.save(reservation);
    }*/

    public Reservation createReservation(Reservation reservation, Long userId, Long activityId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + userId);
        }

        User user = userOptional.get();
        reservation.setUser(user);

        Optional<Activite> activityOptional = activityRepository.findById(Math.toIntExact(activityId));
        if (activityOptional.isEmpty()) {
            throw new RuntimeException("Activity not found with ID: " + activityId);
        }

        Activite activity = activityOptional.get();
        reservation.setActivities(List.of(activity));  // Ajouter l'activité à la réservation

        return reservationRepository.save(reservation);
    }



    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }


}
