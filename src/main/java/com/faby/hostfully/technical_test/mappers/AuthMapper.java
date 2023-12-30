package com.faby.hostfully.technical_test.mappers;

import com.faby.hostfully.technical_test.domain.dtos.UserDTO;
import com.faby.hostfully.technical_test.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public abstract class AuthMapper {
    public abstract UserDTO toDTO(User user);

}
