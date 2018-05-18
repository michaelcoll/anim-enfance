package fr.animenfance.creche.config;

import fr.animenfance.common.config.DatasourceConfiguration;
import fr.animenfance.common.config.SchedulerConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@MapperScan("fr.animenfance.creche.dao")
@Import({DatasourceConfiguration.class, SchedulerConfiguration.class})
public class CrecheConfiguration {
}
