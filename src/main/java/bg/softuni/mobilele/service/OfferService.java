package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.*;

import java.util.List;

public interface OfferService {

    public void addOffer(AddOfferDto addOfferDto);

   public List<OfferDto> allOffers();
}
