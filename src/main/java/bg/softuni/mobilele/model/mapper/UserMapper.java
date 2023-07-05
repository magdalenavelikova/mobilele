package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "isActive", constant = "true")

    UserEntity userDtoToUserEntity(UserRegisterDto registerDto);
}
