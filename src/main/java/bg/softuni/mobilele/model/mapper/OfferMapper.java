package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.AddOfferDto;

import bg.softuni.mobilele.model.dto.OfferDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferDtoToOfferEntity(AddOfferDto addOfferDto);


    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    @Mapping(source = "seller.firstName"  , target = "sellerFirstName")
    @Mapping(source = "seller.lastName"  , target = "sellerLastName")
    OfferDTO offerEntityToOfferDto(OfferEntity offerEntity);
}
