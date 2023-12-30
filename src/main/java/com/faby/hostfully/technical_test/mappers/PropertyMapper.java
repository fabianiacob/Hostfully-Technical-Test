package com.faby.hostfully.technical_test.mappers;

import com.faby.hostfully.technical_test.domain.dtos.PropertyDTO;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdateBlockRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdatePropertyRequest;
import com.faby.hostfully.technical_test.domain.model.Block;
import com.faby.hostfully.technical_test.domain.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class PropertyMapper {
    public abstract PropertyDTO toDTO(Property property);

    public abstract void updateFromDTO(UpdatePropertyRequest updatePropertyRequest, @MappingTarget Property property);


}
