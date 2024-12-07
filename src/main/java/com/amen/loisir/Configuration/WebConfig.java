package com.amen.loisir.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Appliquer les règles CORS à toutes les routes sous
                .allowedOrigins("http://localhost:4200") // Autorise les requêtes provenant de http://localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autorise les méthodes GET, POST, PUT et DELETE
                .allowedHeaders("*"); // Autorise tous les en-têtes
    }
}
