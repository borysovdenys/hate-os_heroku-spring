package com.example.fulldrive.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Capability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "techStack can't be blank")
    @NotNull(message = "techStack can't be null")
    private String techStack;
    private Integer dev = 0;
    private Integer avDev = 0;

    public Capability(String techStack, Integer dev, Integer avDev) {
        this.techStack = techStack;
        this.dev = dev;
        this.avDev = avDev;
    }
}
