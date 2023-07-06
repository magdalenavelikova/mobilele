package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.AddOfferDto;
import bg.softuni.mobilele.model.dto.OfferDto;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.BrandRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.service.ModelService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.user.CurrentUser;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final BrandRepository brandRepository;
    private final ModelService modelService;
    private final OfferMapper offerMapper;
    private final CurrentUser currentUser;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, BrandRepository brandRepository, ModelService modelService, OfferMapper offerMapper, CurrentUser currentUser, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.brandRepository = brandRepository;
        this.modelService = modelService;
        this.offerMapper = offerMapper;
        this.currentUser = currentUser;
        this.userRepository = userRepository;
    }

    @Override
    public void addOffer(AddOfferDto addOfferDto) {
        OfferEntity newOffer = offerMapper.addOfferDtoToOfferEntity(addOfferDto);
        newOffer.setModel(modelService.findById(addOfferDto.getModelId()));
        UserEntity user = userRepository.findByEmail(currentUser.getEmail()).orElseThrow();
        newOffer.setSeller(user);
        newOffer.setCreated(LocalDateTime.now());
        offerRepository.save(newOffer);

    }

    @Override
    public List<OfferDto> allOffers() {
        List<OfferEntity> offers = offerRepository.findAll();
       return  offers.stream()
               .sorted(Comparator.comparing(OfferEntity::getCreated).reversed())
                .map(offer -> {
                    OfferDto offerDto = offerMapper.offerEntityToOfferDto(offer);
                    String brandName = offer.getModel().getBrand().getName();
                    offerDto.setBrandName(brandName);
                    return offerDto;
                })
                .collect(Collectors.toList());


    }


}
