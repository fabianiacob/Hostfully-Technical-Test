package com.faby.hostfully.technical_test.services;

import com.faby.hostfully.technical_test.domain.dtos.requests.CreatePropertyRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdatePropertyRequest;
import com.faby.hostfully.technical_test.domain.model.Property;
import com.faby.hostfully.technical_test.domain.model.User;
import com.faby.hostfully.technical_test.mappers.PropertyMapper;
import com.faby.hostfully.technical_test.repositories.PropertyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.faby.hostfully.technical_test.config.AuthenticationUtils.getAuthenticatedUser;


@Service
@Transactional(readOnly = true)
public class PropertyService extends CommonRepositoryService<Property, PropertyRepository> {
    private final PropertyMapper propertyMapper;

    public PropertyService(PropertyRepository repository, PropertyMapper propertyMapper) {
        super(repository);
        this.propertyMapper = propertyMapper;
    }

    @Transactional
    public Property create(CreatePropertyRequest createPropertyRequest) {
        User user = getAuthenticatedUser();
        Property property = new Property(createPropertyRequest.getName());
        property.setOwner(user);
        return this.repository.save(property);
    }

    @Transactional
    public Property update(UpdatePropertyRequest updatePropertyRequest) {
        User user = getAuthenticatedUser();
        Property property =
                this.repository.findByIdAndOwnerId(updatePropertyRequest.getId(), user.getId()).orElseThrow(EntityNotFoundException::new);

        propertyMapper.updateFromDTO(updatePropertyRequest, property);
        return this.repository.save(property);
    }

    public Optional<Property> findByIdAndOwnerId(Long propertyId, Long ownerId) {
        return this.repository.findByIdAndOwnerId(propertyId, ownerId);
    }
}
