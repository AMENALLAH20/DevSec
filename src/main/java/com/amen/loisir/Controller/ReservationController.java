    package com.amen.loisir.Controller;


    import com.amen.loisir.DTO.ReservationRequest;
    import com.amen.loisir.Entities.Activite;
    import com.amen.loisir.Entities.Reservation;
    import com.amen.loisir.Entities.User;
    import com.amen.loisir.Repositories.ActivityRepository;
    import com.amen.loisir.Repositories.ReservationRepository;
    import com.amen.loisir.Repositories.UserRepository;
    import com.amen.loisir.Services.ReservationService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;

    import java.time.LocalDate;
    import java.util.List;
    import java.util.Locale;
    import java.util.Optional;

    @RestController
    @RequestMapping("/reservation")
    public class ReservationController {

        @Autowired
        private ReservationService reservationService;
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private ActivityRepository activityRepository;

        @Autowired
        ReservationRepository reservationRepository;

       /* public ResponseEntity<Reservation> createReservation(@RequestParam Integer userId,
                                                             @RequestParam Integer activityId,
                                                             @RequestParam LocalDate date,
                                                             @RequestParam Integer nbr
                                                             ) {
            Reservation reservation = reservationService.createReservation(userId, activityId,date,nbr);
            return ResponseEntity.ok(reservation);
        }*/

        @PostMapping("/add")
        public ResponseEntity<Reservation> createReservation(
                @RequestBody ReservationRequest reservationRequest
        ) {
            try {
                // Récupérer l'ID de l'utilisateur à partir du token
                Long userId = reservationRequest.getUserId();

                // Vérifier si l'utilisateur existe
                Optional<User> userOptional = userRepository.findById(userId);
                if (userOptional.isEmpty()) {
                    throw new RuntimeException("User not found with ID: " + userId);
                }

                // Récupérer l'utilisateur
                User user = userOptional.get();

                // Vérifier si l'activité existe
                Optional<Activite> activityOptional = activityRepository.findById(Math.toIntExact(reservationRequest.getActivityId()));
                if (activityOptional.isEmpty()) {
                    throw new RuntimeException("Activity not found with ID: " + reservationRequest.getActivityId());
                }

                // Récupérer l'activité
                Activite activity = activityOptional.get();

                // Créer la réservation
                Reservation reservation = new Reservation();
                reservation.setDate(reservationRequest.getDate());
                reservation.setNbrper(reservationRequest.getNbrper());
                reservation.setUser(user);
                reservation.setActivities(List.of(activity));

                Reservation savedReservation = reservationRepository.save(reservation);
                return ResponseEntity.ok(savedReservation);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(null);
            }
        }



        //@PostMapping("/add")
        @CrossOrigin(origins = "http://localhost:4200")
        public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
            Reservation savedReservation = reservationService.saveReservation(reservation);
            return ResponseEntity.ok(savedReservation); // Retourner la réservation enregistrée
        }
    }
