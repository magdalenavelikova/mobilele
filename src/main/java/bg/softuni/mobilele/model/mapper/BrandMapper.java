package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.BrandDTO;
import bg.softuni.mobilele.model.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandEntity brandDtoToBrandEntity(BrandDTO brandDTO);
    BrandDTO brandEntityToBrandDto(BrandEntity brandEntity);
}
