package bg.softuni.mobilele.service;


import bg.softuni.mobilele.model.dto.CreateOrUpdateOfferDto;
import bg.softuni.mobilele.model.dto.OfferDTO;
import bg.softuni.mobilele.model.dto.SearchOfferDTO;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.model.entity.UserEntity;
import bg.softuni.mobilele.model.enums.Role;
import bg.softuni.mobilele.model.mapper.OfferMapper;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.OfferSpecification;
import bg.softuni.mobilele.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final ModelService modelService;
    private final OfferMapper offerMapper;

    private final UserRepository userRepository;

    public OfferService(OfferRepository offerRepository, ModelService modelService, OfferMapper offerMapper, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelService = modelService;
        this.offerMapper = offerMapper;
        this.userRepository = userRepository;
    }


    public void addOffer(CreateOrUpdateOfferDto createOrUpdateOfferDto, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper.createOrUpdateOfferDtoToOfferEntity(createOrUpdateOfferDto);
        newOffer.setModel(modelService.findById(createOrUpdateOfferDto.getModelId()));
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        newOffer.setSeller(user);
        newOffer.setCreated(LocalDateTime.now());
        offerRepository.save(newOffer);

    }


    public Page<OfferDTO> allOffers(Pageable pageable) {
        return offerRepository.findAll(pageable)
                .map(offerMapper::offerEntityToOfferDto);


    }


    public List<OfferDTO> searchOffer(SearchOfferDTO searchOfferDTO) {
        return offerRepository.findAll(new OfferSpecification(searchOfferDTO)).stream()
                .map(offerMapper::offerEntityToOfferDto)
                .toList();
    }

    public Optional<OfferDTO> getOfferDetails(Long id) {
        return offerRepository.findById(id).map(offerMapper::offerEntityToOfferDto);
    }

    public Optional<CreateOrUpdateOfferDto> getOfferDetailsForUpdate(Long id) {
        Optional<CreateOrUpdateOfferDto> createOrUpdateOfferDto = offerRepository.findById(id).map(offerMapper::offerEntityTocreateOrUpdateOfferDto);

        return offerRepository.findById(id).map(offerMapper::offerEntityTocreateOrUpdateOfferDto);
    }

    public boolean isOwner(String userName, Long offerId) {

        boolean isOwner = offerRepository.
                findById(offerId).
                filter(o -> o.getSeller().getEmail().equals(userName)).
                isPresent();

        if (isOwner) {
            return true;
        }

        return userRepository.
                findByEmail(userName).
                filter(this::isAdmin).
                isPresent();
    }

    private boolean isAdmin(UserEntity user) {

        return user.getRoles().
                stream().
                anyMatch(r -> r.getRole() == Role.ADMIN);
    }

    public void deleteOfferById(Long id) {
        offerRepository.deleteById(id);
    }


    public void updateOfferById(CreateOrUpdateOfferDto createOrUpdateOfferDto, Long id, UserDetails userDetails) {
        OfferEntity updateOffer = offerMapper.createOrUpdateOfferDtoToOfferEntity(createOrUpdateOfferDto);
        UserEntity user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        updateOffer.setModel(modelService.findById(createOrUpdateOfferDto.getModelId()));
        updateOffer.setCreated(LocalDateTime.now());
        updateOffer.setSeller(user);
        offerRepository.save(updateOffer);
    }


}
