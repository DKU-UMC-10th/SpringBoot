package com.example.mission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Mission application.  This class bootstraps the Spring
 * context and launches the embedded servlet container.  The application is
 * structured following a domain‑centric architecture: each feature domain
 * (user, region, store, mission, user mission) contains its own entity,
 * repository, service and controller classes.  Such organization helps keep
 * related code together while isolating domain logic from infrastructure
 * concerns【293873159914343†L72-L94】.
 */
@SpringBootApplication
public class MissionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MissionApplication.class, args);
    }
}