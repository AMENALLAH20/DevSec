package com.amen.loisir.Controller;


import com.amen.loisir.DTO.ActivityRequest;
import com.amen.loisir.Entities.Activite;
import com.amen.loisir.Entities.User;
import com.amen.loisir.Services.ActivityService;
import com.amen.loisir.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    UserService userService;
    /*@PostMapping("/add")
    public ResponseEntity<Activite> add(@RequestBody Activite activite)
    {
          Activite saveactivite = activityService.add(activite);
          return ResponseEntity.ok(saveactivite);
    }*/

    @GetMapping("/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Activite> getAllActivities() {
        return activityService.getAllActivities();
    }
    @GetMapping("/test")
    public String test() {
        return "API fonctionne correctement !";
    }
    @GetMapping("/restaurant")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Activite>> getRestaurantActivities() {
        List<Activite> restaurantActivities = activityService.getRestaurantActivities();
        return ResponseEntity.ok(restaurantActivities);
    }
    @GetMapping("/sport")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Activite>> getSportActivities() {
        List<Activite> sportActivities = activityService.getSportActivities();
        return ResponseEntity.ok(sportActivities);
    }


    @GetMapping("/{id}")
    public Optional<Activite> getActiviteDetails(@PathVariable Integer id) {
        return activityService.getActiviteById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Activite> addActivity(@RequestBody ActivityRequest activityRequest) {
        Long userId = activityRequest.getUserId();

        // Vérifier si l'utilisateur existe
        Optional<User> optionalUser = userService.getUserById(userId);


        // Récupérer l'utilisateur depuis l'Optional
        User user = optionalUser.get();

        // Créer une nouvelle activité et l'associer à l'utilisateur
        Activite activity = new Activite();
        activity.setTitle(activityRequest.getTitle());
        activity.setDescription(activityRequest.getDescription());
        activity.setUser(user); // Associer l'utilisateur à l'activité

        // Enregistrer l'activité dans la base de données
        activityService.add(activity);

        return ResponseEntity.ok(activity);
    }



}
