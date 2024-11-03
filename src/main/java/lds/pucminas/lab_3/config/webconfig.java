package lds.pucminas.lab_3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Habilitar CORS para os endpoints que começam com /api/
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500", "http://localhost:8081") // Inclui localhost:8081 como origem permitida
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Permitir todos os cabeçalhos
    }
}
