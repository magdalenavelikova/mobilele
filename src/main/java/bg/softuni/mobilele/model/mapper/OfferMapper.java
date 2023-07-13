package bg.softuni.mobilele.model.mapper;

import bg.softuni.mobilele.model.dto.AddOfferDto;
import bg.softuni.mobilele.model.dto.CardListingOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDto;
import bg.softuni.mobilele.model.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    OfferEntity addOfferDtoToOfferEntity(AddOfferDto addOfferDto);

    OfferDto offerEntityToOfferDto(OfferEntity offerEntity);
    @Mapping(source = "model.name", target = "model")
    @Mapping(source = "model.brand.name", target = "brand")
    CardListingOfferDTO offerEntityToCardListingOfferDto(OfferEntity offerEntity);
}
