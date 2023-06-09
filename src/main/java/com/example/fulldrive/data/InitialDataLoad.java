package com.example.fulldrive.data;

import com.example.fulldrive.entity.Capability;
import com.example.fulldrive.repository.CapabilityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDataLoad {

    @Bean
    CommandLineRunner LoadDB(CapabilityRepository capabilityRepository) {
        return args -> {
            capabilityRepository.save(new Capability("Java", 200, 150));
            capabilityRepository.save(new Capability("Pit", 2200, 1590));
            capabilityRepository.save(new Capability("Disco", 10, 8));
        };
    }
}
