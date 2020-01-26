package config;

import config.PersistenceJPAConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import repository.AuthenticateDao;
import service.AuthenticateService;

@Configuration
@Import(PersistenceJPAConfig.class)
@ComponentScan("java")
public class RootConfig {

    @Bean
    public AuthenticateService authenticateService(){
        return new AuthenticateService();
    }
}
