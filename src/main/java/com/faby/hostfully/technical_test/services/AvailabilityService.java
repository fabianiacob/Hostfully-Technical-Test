package com.faby.hostfully.technical_test.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
public class AvailabilityService {
    private final BookingService bookingService;
    private final BlockService blockService;

    public AvailabilityService(BookingService bookingService, BlockService blockService) {
        this.bookingService = bookingService;
        this.blockService = blockService;
    }

    public boolean propertyIsAvailableBetween(Long propertyId, LocalDate start, LocalDate end) {
        return !blockService.propertyHasBlockBetween(propertyId, start, end) && !bookingService.propertyHasBookingBetween(propertyId,
                start, end);
    }

    public boolean propertyIsAvailableBetweenExcludingBlock(Long propertyId, LocalDate start, LocalDate end, Long blockIdToExclude) {
        return !blockService.propertyHasBlockBetweenExcluding(propertyId, start, end, blockIdToExclude) && !bookingService.propertyHasBookingBetween(propertyId, start, end);
    }

    public boolean propertyIsAvailableBetweenExcludingBooking(Long propertyId, LocalDate start, LocalDate end, Long bookingIdToExclude) {
        return !blockService.propertyHasBlockBetween(propertyId, start, end) && !bookingService.propertyHasBookingBetweenExcluding(propertyId, start, end, bookingIdToExclude);
    }
}
