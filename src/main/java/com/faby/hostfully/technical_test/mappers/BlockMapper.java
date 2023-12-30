package com.faby.hostfully.technical_test.mappers;

import com.faby.hostfully.technical_test.domain.dtos.BlockDTO;
import com.faby.hostfully.technical_test.domain.dtos.requests.CreateBlockRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdateBlockRequest;
import com.faby.hostfully.technical_test.domain.model.Block;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class BlockMapper {
    public abstract BlockDTO toDTO(Block block);

    public abstract Block toEntity(CreateBlockRequest createBlockRequest);

    public abstract void updateFromDTO(UpdateBlockRequest updateBlockRequest, @MappingTarget Block block);


}
