package com.faby.hostfully.technical_test.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    @Length(min = 3, max = 40)
    private String name;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate creationDate = LocalDate.now();

    @ManyToOne
    private User owner;

    @OneToMany(mappedBy = "property")
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "property")
    private Set<Block> blocks = new HashSet<>();

    public Property(String name) {
        this.name = name;
    }
}
