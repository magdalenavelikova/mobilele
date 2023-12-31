package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.CreateOrUpdateOfferDto;

import bg.softuni.mobilele.model.dto.OfferDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity createOrUpdateOfferDtoToOfferEntity(CreateOrUpdateOfferDto createOrUpdateOfferDto);
    @Mapping(source = "model.id", target = "modelId")
    CreateOrUpdateOfferDto offerEntityTocreateOrUpdateOfferDto(OfferEntity offerEntity);


    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    @Mapping(source = "seller.firstName"  , target = "sellerFirstName")
    @Mapping(source = "seller.lastName"  , target = "sellerLastName")
    OfferDTO offerEntityToOfferDto(OfferEntity offerEntity);
}
