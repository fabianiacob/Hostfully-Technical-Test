package com.faby.hostfully.technical_test.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Property property;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    private Set<Guest> guests = new HashSet<>();

    @Column(nullable = false, length = 40)
    @Length(min = 3, max = 40)
    private String name;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate creationDate = LocalDate.now();

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate endDate;

    @Column(nullable = false)
    private boolean cancelled = false;
}
