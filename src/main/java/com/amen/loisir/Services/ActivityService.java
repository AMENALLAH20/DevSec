package com.amen.loisir.Services;


import com.amen.loisir.Entities.Activite;
import com.amen.loisir.Entities.ActiviteType;
import com.amen.loisir.Entities.User;
import com.amen.loisir.Repositories.ActivityRepository;
import com.amen.loisir.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    public Activite add(Activite activite)
    {
        return activityRepository.save(activite);
    }

    public void addActivityToUser(Long userId, Integer activityId) {
        // Récupérer l'utilisateur
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Récupérer l'activité
        Activite activity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activité non trouvée"));

        // Ajouter l'activité à l'utilisateur
        user.getActivities().add(activity);

        // Sauvegarder l'utilisateur pour mettre à jour la table intermédiaire
        userRepository.save(user);
    }


    public List<Activite> getAllActivities()
    {
        return activityRepository.findAll();
    }

    public List<Activite> getRestaurantActivities()
    {
        return activityRepository.findByActiviteType(ActiviteType.restaurant);
    }

    public List<Activite> getSportActivities()
    {
        return activityRepository.findByActiviteType(ActiviteType.sport);
    }

    public Optional<Activite> getActiviteById(Integer id) {
        return activityRepository.findById(id);
    }
}
