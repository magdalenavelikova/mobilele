package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.AddOfferDto;
import bg.softuni.mobilele.model.dto.BrandDTO;
import bg.softuni.mobilele.model.dto.ModelDto;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;

    @Override
    public void addOffer(AddOfferDto addOfferDto) {

    }

    @Override
    public List<BrandDTO> getAllBrands() {
        return null;
    }

    @Override
    public ModelDto getModels() {
        return null;
    }
}
