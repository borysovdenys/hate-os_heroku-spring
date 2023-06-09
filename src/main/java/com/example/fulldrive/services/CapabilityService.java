package com.example.fulldrive.services;

import com.example.fulldrive.entity.Capability;
import com.example.fulldrive.exceptions.CapabilityException;
import com.example.fulldrive.repository.CapabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class CapabilityService {
    private CapabilityRepository capabilityRepository;

    public List<Capability> getAll() {
        return capabilityRepository.findAll();
    }

    public Capability findById(Long id) {
        return capabilityRepository.findById(id).orElseThrow(() -> new CapabilityException("Cap not found with id:" + id));
    }

    public Capability saveCap(Capability capability) {
        return capabilityRepository.save(capability);
    }

    public ResponseEntity<?> errorMap(BindingResult result) {
        var errorMap = new HashMap<>();

        for (FieldError err : result.getFieldErrors()) {
            errorMap.put(err.getField(), err.getDefaultMessage());
        }

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    public Capability updateCap(Long id, Capability capability) {
        return capabilityRepository.findById(id).map(
                c -> {
                    c.setTechStack(capability.getTechStack());
                    c.setDev(capability.getDev());
                    c.setAvDev(capability.getAvDev());
                    return capabilityRepository.save(c);
                }
        ).orElseGet(() -> capabilityRepository.save(capability));
    }

    public void deleteCap(Long id) {
        capabilityRepository.delete(capabilityRepository.findById(id)
                .orElseThrow(() -> new CapabilityException("Cap not found with id: "+ id)));
    }
}
