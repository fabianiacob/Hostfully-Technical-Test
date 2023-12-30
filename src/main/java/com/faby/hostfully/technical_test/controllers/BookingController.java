package com.faby.hostfully.technical_test.controllers;

import com.faby.hostfully.technical_test.domain.dtos.BookingDTO;
import com.faby.hostfully.technical_test.domain.dtos.requests.CreateBookingRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdateBookingRequest;
import com.faby.hostfully.technical_test.mappers.BookingMapper;
import com.faby.hostfully.technical_test.services.BookingService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@Validated
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;

    public BookingController(BookingService bookingService, BookingMapper bookingMapper) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
    }

    @GetMapping("/{id}")
    public @Valid BookingDTO get(@PathVariable Long id) {
        return bookingMapper.toDTO(bookingService.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @PostMapping
    public @Valid BookingDTO create(@Valid @RequestBody CreateBookingRequest createBookingRequest) {
        return bookingMapper.toDTO(bookingService.create(createBookingRequest));
    }

    @PutMapping
    public @Valid BookingDTO update(@Valid @RequestBody UpdateBookingRequest updateBookingRequest) {
        return bookingMapper.toDTO(bookingService.update(updateBookingRequest));
    }

    @GetMapping("/{id}/cancel")
    public @Valid BookingDTO cancel(@PathVariable Long id) {
        return bookingMapper.toDTO(bookingService.cancel(id));
    }

    @GetMapping("/{id}/book")
    public @Valid BookingDTO reBook(@PathVariable Long id) {
        return bookingMapper.toDTO(bookingService.reBook(id));
    }

}
