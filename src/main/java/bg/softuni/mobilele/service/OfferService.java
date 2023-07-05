package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.AddOfferDto;
import bg.softuni.mobilele.model.dto.BrandDTO;
import bg.softuni.mobilele.model.dto.ModelDto;

import java.util.List;

public interface OfferService {

    public void addOffer(AddOfferDto addOfferDto);
    public List<BrandDTO> getAllBrands();
    public ModelDto getModels();
}
