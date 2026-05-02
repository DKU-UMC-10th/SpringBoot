package com.example.mission.domain.store;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Store entities.  Exposes endpoints to list all stores,
 * retrieve a single store by ID, list stores by region and create new
 * stores.  Requests are handled by the StoreService which contains the
 * business logic for working with stores.
 */
@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<Store> getStores() {
        return storeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStore(@PathVariable Long id) {
        return storeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/region/{regionId}")
    public List<Store> getStoresByRegion(@PathVariable Long regionId) {
        return storeService.findByRegion(regionId);
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreDto dto) {
        Store created = storeService.createStore(
                dto.getRegionId(),
                dto.getName(),
                dto.getAddress(),
                dto.getDescription(),
                dto.getCategory(),
                dto.getScore()
        );
        return ResponseEntity.ok(created);
    }

    /**
     * DTO for creating a store.  Separate from the domain entity to keep
     * validation and API contract distinct.
     */
    public static class StoreDto {
        private Long regionId;
        private String name;
        private String address;
        private String description;
        private String category;
        private Float score;

        public Long getRegionId() {
            return regionId;
        }

        public void setRegionId(Long regionId) {
            this.regionId = regionId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Float getScore() {
            return score;
        }

        public void setScore(Float score) {
            this.score = score;
        }
    }
}