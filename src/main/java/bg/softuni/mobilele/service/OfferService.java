package bg.softuni.mobilele.service;


import bg.softuni.mobilele.model.dto.AddOfferDto;
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


    public void addOffer(AddOfferDto addOfferDto, UserDetails userDetails) {
        OfferEntity newOffer = offerMapper.addOfferDtoToOfferEntity(addOfferDto);
        newOffer.setModel(modelService.findById(addOfferDto.getModelId()));
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

    public boolean isOwner(String username, Long id) {
        boolean isOwner = offerRepository.findById(id)
                .filter(offer -> offer.getSeller().getEmail().equals(username))
                .isPresent();
        if(isOwner){
            return true;
        }
        return  isAdmin(userRepository.findByEmail(username).orElseThrow());

    }

    private boolean isAdmin(UserEntity user) {
        return user.getRoles().stream().anyMatch(r -> r.getRole().equals(Role.ADMIN));
    }

    public void deleteOfferById(Long id) {
        offerRepository.deleteById(id);
    }
}
