package insper.store.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        // Definindo um servidor de exemplo
        Server serverLocal = new Server().url("http://localhost:8080").description("Ambiente de Desenvolvimento Local");
        
        openAPI.addServersItem(serverLocal);
        
        return openAPI;
    }

}
