package com.faby.hostfully.technical_test.repositories;

import com.faby.hostfully.technical_test.domain.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByIdAndOwnerId(Long propertyId, Long ownerId);
}
