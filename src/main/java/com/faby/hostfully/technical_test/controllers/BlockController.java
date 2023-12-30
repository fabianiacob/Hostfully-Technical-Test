package com.faby.hostfully.technical_test.controllers;

import com.faby.hostfully.technical_test.domain.dtos.BlockDTO;
import com.faby.hostfully.technical_test.domain.dtos.PropertyDTO;
import com.faby.hostfully.technical_test.domain.dtos.requests.CreateBlockRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.CreatePropertyRequest;
import com.faby.hostfully.technical_test.domain.dtos.requests.UpdateBlockRequest;
import com.faby.hostfully.technical_test.mappers.BlockMapper;
import com.faby.hostfully.technical_test.services.BlockService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/block")
@Validated
public class BlockController {

    private final BlockService blockService;
    private final BlockMapper blockMapper;

    public BlockController(BlockService blockService, BlockMapper blockMapper) {
        this.blockService = blockService;
        this.blockMapper = blockMapper;
    }

    @GetMapping("/{id}")
    public @Valid BlockDTO get(@PathVariable() Long id) {
        return blockMapper.toDTO(blockService.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @PostMapping
    public @Valid BlockDTO create(@Valid @RequestBody CreateBlockRequest createBlockRequest) {
        return blockMapper.toDTO(blockService.create(createBlockRequest));
    }

    @PutMapping
    public @Valid BlockDTO update(@Valid @RequestBody UpdateBlockRequest updateBlockRequest) {
        return blockMapper.toDTO(blockService.update(updateBlockRequest));
    }

}
