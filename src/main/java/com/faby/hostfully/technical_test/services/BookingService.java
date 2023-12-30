package com.faby.hostfully.technical_test.services;

import com.faby.hostfully.technical_test.domain.dtos.requests.CreateBookingRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdateBookingRequest;
import com.faby.hostfully.technical_test.domain.exceptions.HostfullyExceptionCode;
import com.faby.hostfully.technical_test.domain.exceptions.HostfullyTechnicalTestException;
import com.faby.hostfully.technical_test.domain.model.Booking;
import com.faby.hostfully.technical_test.domain.model.Property;
import com.faby.hostfully.technical_test.domain.model.User;
import com.faby.hostfully.technical_test.mappers.BookingMapper;
import com.faby.hostfully.technical_test.repositories.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.faby.hostfully.technical_test.config.AuthenticationUtils.getAuthenticatedUser;


@Service
@Transactional(readOnly = true)
public class BookingService extends CommonRepositoryService<Booking, BookingRepository> {

    private final BookingMapper bookingMapper;
    private final PropertyService propertyService;
    private final AvailabilityService availabilityService;

    public BookingService(BookingRepository repository, BookingMapper bookingMapper, PropertyService propertyService,
                          @Lazy AvailabilityService availabilityService) {
        super(repository);
        this.bookingMapper = bookingMapper;
        this.propertyService = propertyService;
        this.availabilityService = availabilityService;
    }

    @Transactional
    public Booking create(CreateBookingRequest createBookingRequest) {
        User user = getAuthenticatedUser();
        Property property = propertyService.findById(createBookingRequest.getPropertyId()).orElseThrow(EntityNotFoundException::new);

        if (!availabilityService.propertyIsAvailableBetween(createBookingRequest.getPropertyId(), createBookingRequest.getStartDate(),
                createBookingRequest.getEndDate())) {
            throw new HostfullyTechnicalTestException(HostfullyExceptionCode.PROPERTY_UNAVAILABLE);
        }

        Booking booking = bookingMapper.toEntity(createBookingRequest);
        booking.setProperty(property);
        booking.setUser(user);
        return this.repository.save(booking);
    }

    @Transactional
    public Booking update(UpdateBookingRequest updateBookingRequest) {
        User user = getAuthenticatedUser();
        Booking booking =
                this.repository.findByIdAndUserId(updateBookingRequest.getId(), user.getId()).orElseThrow(EntityNotFoundException::new);

        if (!availabilityService.propertyIsAvailableBetweenExcludingBooking(booking.getProperty().getId(),
                updateBookingRequest.getStartDate(), updateBookingRequest.getEndDate(), booking.getId())) {
            throw new HostfullyTechnicalTestException(HostfullyExceptionCode.PROPERTY_UNAVAILABLE);
        }

        //Updating a cancelled booking automatically rebooks
        booking.setCancelled(false);

        bookingMapper.updateFromDTO(updateBookingRequest, booking);
        return this.repository.save(booking);
    }

    @Transactional
    public Booking cancel(Long id) {
        User user = getAuthenticatedUser();
        Booking booking = this.repository.findByIdAndUserId(id, user.getId()).orElseThrow(EntityNotFoundException::new);
        if (booking.isCancelled()) {
            throw new HostfullyTechnicalTestException(HostfullyExceptionCode.BOOKING_ALREADY_CANCELLED);
        }
        booking.setCancelled(true);
        return this.repository.save(booking);
    }

    @Transactional
    public Booking reBook(Long id) {
        User user = getAuthenticatedUser();
        Booking booking = this.repository.findByIdAndUserId(id, user.getId()).orElseThrow(EntityNotFoundException::new);
        if (!booking.isCancelled()) {
            throw new HostfullyTechnicalTestException(HostfullyExceptionCode.BOOKING_NOT_CANCELLED);
        }
        if (!availabilityService.propertyIsAvailableBetween(booking.getProperty().getId(), booking.getStartDate(), booking.getEndDate())) {
            throw new HostfullyTechnicalTestException(HostfullyExceptionCode.PROPERTY_UNAVAILABLE);
        }
        booking.setCancelled(false);
        return this.repository.save(booking);
    }

    public boolean propertyHasBookingBetween(Long propertyId, LocalDate startDate, LocalDate endDate) {
        return this.repository.existsByPropertyIdAndStartDateOrEndDateBetween(propertyId, startDate, endDate);
    }

    public boolean propertyHasBookingBetweenExcluding(Long propertyId, LocalDate startDate, LocalDate endDate, Long bookingIdToExclude) {
        return this.repository.existsByPropertyIdAndStartDateOrEndDateBetweenExcludingBookingId(propertyId, startDate, endDate,
                bookingIdToExclude);
    }


}
