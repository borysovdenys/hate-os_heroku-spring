package com.example.fulldrive.controllers;

import com.example.fulldrive.assembler.CapabilityResourceAssembler;
import com.example.fulldrive.entity.Capability;
import com.example.fulldrive.services.CapabilityService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin
@AllArgsConstructor
public class CapabilityController {
    private CapabilityService capabilityService;
    private CapabilityResourceAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<Capability>> getAllCapabilities() {
        return CollectionModel.of(capabilityService.getAll().stream()
                        .map(capability -> assembler.toModel(capability)).toList(),
                Link.of("https://hateos-back-spring.herokuapp.com/dashboard", "create_new_cap"));
    }

    @GetMapping("/{id}")
    public EntityModel<?> getCapability(@PathVariable Long id) {
        return assembler.toModel(capabilityService.findById(id));
    }

    @PostMapping
    public Object createCapability(@Valid @RequestBody Capability capability, BindingResult result) {
        if (result.hasErrors()) return capabilityService.errorMap(result);

        return assembler.toModel(capabilityService.saveCap(capability));
    }

    @PutMapping("/{id}")
    public Object updateCapability(@PathVariable Long id, @Valid @RequestBody Capability capability, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return capabilityService.errorMap(bindingResult);

        return assembler.toModel(capabilityService.updateCap(id, capability));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCapability(@PathVariable Long id) {
        capabilityService.deleteCap(id);

        return new ResponseEntity<String>("Cap deleted", HttpStatus.OK);
    }
}
