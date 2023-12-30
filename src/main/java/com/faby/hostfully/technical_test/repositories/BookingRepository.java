package com.faby.hostfully.technical_test.repositories;

import com.faby.hostfully.technical_test.domain.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByIdAndUserId(Long id, Long userId);

    @Query("select case when count(b) > 0 then true else false end from Booking b where b.property.id = :propertyId and b.cancelled = " +
            "false and (b.startDate between :startDate and :endDate or b.endDate between :startDate and :endDate)")
    boolean existsByPropertyIdAndStartDateOrEndDateBetween(Long propertyId, LocalDate startDate, LocalDate endDate);

    @Query("select case when count(b) > 0 then true else false end from Booking b where b.property.id = :propertyId and b.cancelled = " +
            "false and (b.startDate between :startDate and :endDate or b.endDate between :startDate and :endDate) and b.id != :bookingIdToExclude")
    boolean existsByPropertyIdAndStartDateOrEndDateBetweenExcludingBookingId(Long propertyId, LocalDate startDate,
                                                                             LocalDate endDate, Long bookingIdToExclude);
}
