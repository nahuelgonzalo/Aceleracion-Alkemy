package com.alkemy.java.mapper;
import com.alkemy.java.dto.IDTOQuery.IUserDTO;
import com.alkemy.java.dto.UserAuthDTO;
import com.alkemy.java.dto.UserDTO;
import com.alkemy.java.dto.UserResponseDTO;
import com.alkemy.java.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "lastName", target = "surname")
    UserDTO toUserDTO(User user);
    @Mapping(source = "firstName", target = "name")
    @Mapping(source = "lastName", target = "surname")
    UserResponseDTO toUserResponseDTO(User user);
    UserAuthDTO toUserAuthDTO(User user);
    @Mapping(source = "first_Name", target = "name")
    @Mapping(source = "last_Name", target = "surname")
    UserResponseDTO toUserResponseDTO(IUserDTO user);
}