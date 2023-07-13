package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.CardListingOfferDTO;
import bg.softuni.mobilele.model.dto.UserDto;
import bg.softuni.mobilele.model.dto.UserRegisterDto;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "isActive", constant = "true")
    UserEntity userDtoToUserEntity(UserRegisterDto registerDto);
    UserDto userEntityToUserDto(UserEntity user);

}

