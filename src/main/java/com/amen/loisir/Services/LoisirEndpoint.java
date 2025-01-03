package com.amen.loisir.Services;


import com.amen.loisir.Entities.Activite;
import com.amen.loisir.GetAvailableSeatsRequest;
import com.amen.loisir.GetAvailableSeatsResponse;
import com.amen.loisir.Repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class LoisirEndpoint {

    private static final String NAMESPACE_URI = "http://www.amen.com/loisir";

    // Injecter le repository
    @Autowired
    private ActivityRepository activityRepository;
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAvailableSeatsRequest")
    @ResponsePayload
    public GetAvailableSeatsResponse getAvailableSeats(@RequestPayload GetAvailableSeatsRequest request) {
        // Recherche de l'activité dans la base de données par son ID
        Optional<Activite> activite = activityRepository.findById(request.getActivityId());

        // Créer la réponse
        GetAvailableSeatsResponse response = new GetAvailableSeatsResponse();

        // Vérifier si l'activité existe et renvoyer le nombre de places disponibles
        if (activite != null) {
            response.setAvailableSeats(activite.get().getAvailableSeats());
        } else {
            // Si l'activité n'existe pas, définir une valeur par défaut ou gérer l'erreur
            response.setAvailableSeats(0);  // Exemple: Aucune place disponible
        }
        return response;
    }
}
