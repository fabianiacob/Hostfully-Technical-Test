package com.faby.hostfully.technical_test.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "guests")
@Data
@NoArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    @Length(min = 3, max = 40)
    private String firstName;

    @Column(nullable = false, length = 40)
    @Length(min = 3, max = 40)
    private String lastName;


}
