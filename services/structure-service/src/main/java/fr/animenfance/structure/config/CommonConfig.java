package fr.animenfance.structure.config;

import fr.animenfance.common.config.CommonSecurityConfig;
import fr.animenfance.common.config.MongoConfig;
import fr.animenfance.common.config.SessionConfig;
import fr.animenfance.common.config.CorsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonSecurityConfig.class, MongoConfig.class, SessionConfig.class, CorsConfiguration.class})
public class CommonConfig {
}
