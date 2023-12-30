package com.faby.hostfully.technical_test.mappers;

import com.faby.hostfully.technical_test.domain.dtos.BookingDTO;
import com.faby.hostfully.technical_test.domain.dtos.requests.CreateBookingRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdateBookingRequest;
import com.faby.hostfully.technical_test.domain.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class BookingMapper {
    public abstract BookingDTO toDTO(Booking booking);

    public abstract Booking toEntity(CreateBookingRequest createBookingRequest);

    public abstract void updateFromDTO(UpdateBookingRequest updateBookingRequest, @MappingTarget Booking booking);
}
