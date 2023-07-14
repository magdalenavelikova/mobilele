package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDto;
import bg.softuni.mobilele.model.dto.CardListingOfferDTO;
import bg.softuni.mobilele.model.dto.OfferDto;
import bg.softuni.mobilele.model.entity.OfferEntity;

import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.BrandRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final BrandRepository brandRepository;
    private final ModelService modelService;
    private final OfferMapper offerMapper;

    private final UserRepository userRepository;

    public OfferService(OfferRepository offerRepository, BrandRepository brandRepository, ModelService modelService, OfferMapper offerMapper, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.brandRepository = brandRepository;
        this.modelService = modelService;
        this.offerMapper = offerMapper;

        this.userRepository = userRepository;
    }


    public void addOffer(AddOfferDto addOfferDto, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper.addOfferDtoToOfferEntity(addOfferDto);
        newOffer.setModel(modelService.findById(addOfferDto.getModelId()));
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        newOffer.setSeller(user);
        newOffer.setCreated(LocalDateTime.now());
        offerRepository.save(newOffer);

    }


    public List<OfferDto> allOffers() {
        List<OfferEntity> offers = offerRepository.findAll();
        return offers.stream()
                .sorted(Comparator.comparing(OfferEntity::getCreated).reversed())
                .map(offer -> {
                    OfferDto offerDto = offerMapper.offerEntityToOfferDto(offer);
                    String brandName = offer.getModel().getBrand().getName();
                    offerDto.setBrandName(brandName);
                    return offerDto;
                })
                .collect(Collectors.toList());


    }

    public List<CardListingOfferDTO> findOfferByOfferName(String query) {
        return this.offerRepository
                .findAllByModel_NameContains(query)
                .stream()
                .map(offer -> offerMapper.offerEntityToCardListingOfferDto(offer))
                .toList();
    }


}
