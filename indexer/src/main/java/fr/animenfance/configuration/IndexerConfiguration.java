package fr.animenfance.configuration;

import org.apache.lucene.store.RAMDirectory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IndexerConfiguration {

  @Bean
  public RAMDirectory index() {
    return new RAMDirectory();
  }
}
