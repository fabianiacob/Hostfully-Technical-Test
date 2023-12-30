package com.faby.hostfully.technical_test.repositories;

import com.faby.hostfully.technical_test.domain.model.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {

    @Query("select case when count(b) > 0 then true else false end from Block b where b.property.id = :propertyId and" +
            " (b.startDate between :startDate and :endDate or b.endDate between :startDate and :endDate)")
    boolean existsByPropertyIdAndStartDateOrEndDateBetween(Long propertyId, LocalDate startDate, LocalDate endDate);

    @Query("select case when count(b) > 0 then true else false end from Block b where b.property.id = :propertyId and" +
            " (b.startDate between :startDate and :endDate or b.endDate between :startDate and :endDate) and b.id != :blockIdToExclude")
    boolean existsByPropertyIdAndStartDateOrEndDateBetweenExcludingBlockId(Long propertyId, LocalDate startDate,
                                                                          LocalDate endDate, Long blockIdToExclude);
}
