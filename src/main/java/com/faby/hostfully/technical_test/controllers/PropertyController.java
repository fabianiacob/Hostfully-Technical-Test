package com.faby.hostfully.technical_test.controllers;

import com.faby.hostfully.technical_test.domain.dtos.PropertyDTO;
import com.faby.hostfully.technical_test.domain.dtos.requests.CreatePropertyRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdatePropertyRequest;
import com.faby.hostfully.technical_test.mappers.PropertyMapper;
import com.faby.hostfully.technical_test.services.PropertyService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
@Validated
public class PropertyController {

    private final PropertyService propertyService;
    private final PropertyMapper propertyMapper;

    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper) {
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
    }

    @GetMapping("/{id}")
    public @Valid PropertyDTO get(@PathVariable() Long id) {
        return propertyMapper.toDTO(propertyService.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @PostMapping
    public @Valid PropertyDTO create(@Valid @RequestBody CreatePropertyRequest createPropertyRequest) {
        return propertyMapper.toDTO(propertyService.create(createPropertyRequest));
    }

    @PutMapping
    public @Valid PropertyDTO update(@Valid @RequestBody UpdatePropertyRequest updatePropertyRequest) {
        return propertyMapper.toDTO(propertyService.update(updatePropertyRequest));
    }

}
