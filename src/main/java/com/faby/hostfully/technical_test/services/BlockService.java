package com.faby.hostfully.technical_test.services;

import com.faby.hostfully.technical_test.domain.dtos.requests.CreateBlockRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdateBlockRequest;
import com.faby.hostfully.technical_test.domain.exceptions.HostfullyExceptionCode;
import com.faby.hostfully.technical_test.domain.exceptions.HostfullyTechnicalTestException;
import com.faby.hostfully.technical_test.domain.model.Block;
import com.faby.hostfully.technical_test.domain.model.Property;
import com.faby.hostfully.technical_test.domain.model.User;
import com.faby.hostfully.technical_test.mappers.BlockMapper;
import com.faby.hostfully.technical_test.repositories.BlockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.faby.hostfully.technical_test.config.AuthenticationUtils.getAuthenticatedUser;


@Service
@Transactional(readOnly = true)
public class BlockService extends CommonRepositoryService<Block, BlockRepository> {

    private final BlockMapper blockMapper;
    private final PropertyService propertyService;
    private final AvailabilityService availabilityService;

    public BlockService(BlockRepository repository, BlockMapper blockMapper, PropertyService propertyService,
                        @Lazy AvailabilityService availabilityService) {
        super(repository);
        this.blockMapper = blockMapper;
        this.propertyService = propertyService;
        this.availabilityService = availabilityService;
    }

    @Transactional
    public Block create(CreateBlockRequest createBlockRequest) {
        User user = getAuthenticatedUser();
        Property property =
                propertyService.findByIdAndOwnerId(createBlockRequest.getPropertyId(), user.getId()).orElseThrow(EntityNotFoundException::new);

        if (!availabilityService.propertyIsAvailableBetween(property.getId(), createBlockRequest.getStartDate(),
                createBlockRequest.getEndDate())) {
            throw new HostfullyTechnicalTestException(HostfullyExceptionCode.PROPERTY_UNAVAILABLE);
        }

        Block block = blockMapper.toEntity(createBlockRequest);
        block.setProperty(property);
        return this.repository.save(block);
    }

    @Transactional
    public Block update(UpdateBlockRequest updateBlockRequest) {
        User user = getAuthenticatedUser();
        Block block = this.repository.findById(updateBlockRequest.getId()).orElseThrow(EntityNotFoundException::new);
        if (!block.getProperty().getOwner().getId().equals(user.getId())) {
            throw new HostfullyTechnicalTestException(HostfullyExceptionCode.NOT_OWNER_OF_PROPERTY);
        }

        if (!availabilityService.propertyIsAvailableBetweenExcludingBlock(block.getProperty().getId(), updateBlockRequest.getStartDate(),
                updateBlockRequest.getEndDate(), block.getId())) {
            throw new HostfullyTechnicalTestException(HostfullyExceptionCode.PROPERTY_UNAVAILABLE);
        }

        blockMapper.updateFromDTO(updateBlockRequest, block);
        return this.repository.save(block);
    }

    public boolean propertyHasBlockBetween(Long propertyId, LocalDate startDate, LocalDate endDate) {
        return this.repository.existsByPropertyIdAndStartDateOrEndDateBetween(propertyId, startDate, endDate);
    }

    public boolean propertyHasBlockBetweenExcluding(Long propertyId, LocalDate startDate, LocalDate endDate, Long blockIdToExclude) {
        return this.repository.existsByPropertyIdAndStartDateOrEndDateBetweenExcludingBlockId(propertyId, startDate, endDate,
                blockIdToExclude);
    }
}
