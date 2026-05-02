package com.example.mission.domain.region;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing CRUD endpoints for Region entities.  The URL
 * mappings follow a RESTful convention: GET /regions returns all regions,
 * GET /regions/{id} returns a single region, and POST /regions accepts a
 * JSON body with a name to create a new region.  This controller
 * delegates business logic to the RegionService and returns appropriate
 * HTTP responses.
 */
@RestController
@RequestMapping("/regions")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        return regionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody RegionDto dto) {
        Region created = regionService.createRegion(dto.getName());
        return ResponseEntity.ok(created);
    }

    /**
     * Simple DTO for creating regions.  Having a separate request object allows
     * us to decouple the API contract from the domain entity.
     */
    public static class RegionDto {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}