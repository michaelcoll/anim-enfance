package fr.animenfance.partenaire.controller;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "indexer",
  fallback = PartenaireIndexerFeignServiceFallback.class)
public interface PartenaireIndexerFeignService extends PartenaireIndexerService {
}
