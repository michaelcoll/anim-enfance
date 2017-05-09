package fr.animenfance.partenaire.controller;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("indexer")
public interface PartenaireIndexerFeignService extends PartenaireIndexerService {
}
